/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import daos.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.User;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Tran Nguyen Nam Thuan CE171497
 */
public class LoginGoogleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginGoogleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginGoogleController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        Customer customer = getUserInfo(accessToken);
        CustomerDAO customerDAO = new CustomerDAO();
       
        if (!customerDAO.isExistByEmail(customer.getEmail())) {
            customerDAO.createByGoogle(customer);
        }
        System.out.println(customer.getEmail());
         Customer c = customerDAO.readByEmail(customer.getEmail());
        HttpSession session = request.getSession();
        System.out.println(c);
        session.setAttribute("user", new User(c.getUsername(), null, "customer", c.getUserID()));

        response.sendRedirect("/home");
    }

    public String getToken(String code) throws ClientProtocolException, IOException {

        String GOOGLE_CLIENT_ID = "594653780192-pmf6uuqpi6tdacsjvp1s7dvbefqtnd55.apps.googleusercontent.com";

        String GOOGLE_CLIENT_SECRET = "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";

        String GOOGLE_REDIRECT_URI = "http://localhost:8080/BakeryShop/loginGoogle";

        String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

        String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

        String GOOGLE_GRANT_TYPE = "authorization_code";
        // call api to get token
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public Customer getUserInfo( String accessToken) throws ClientProtocolException, IOException {
        String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        System.out.println(response);
         JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        // Lấy từng giá trị từ JsonObject
        String id = jsonObject.get("id").getAsString();
        String email = jsonObject.get("email").getAsString();
        boolean verifiedEmail = jsonObject.get("verified_email").getAsBoolean();
        String name = jsonObject.get("name").getAsString();
        String givenName = jsonObject.get("given_name").getAsString();
        String familyName = jsonObject.get("family_name").getAsString();
        String picture = jsonObject.get("picture").getAsString();
        System.out.println(name);
        Customer customer = new Customer();
        customer.setAccessToken(accessToken);
        customer.setEmail(email);
        customer.setGoogleID(id);
        customer.setFullname(name);
        customer.setUserAvatar(picture);
        customer.setUsername(email.substring(0, email.indexOf("@")));
        return customer;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
