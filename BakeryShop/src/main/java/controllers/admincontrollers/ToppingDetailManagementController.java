/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import daos.ToppingDAO;
import daos.GoWithDAO;
import daos.ProductHistoryDAO;
import daos.StaffDAO;
import daos.ToppingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import models.ProductHistory;
import models.Topping;
import models.Topping;
import models.User;

/**
 *
 * @author ACER PC
 */
public class ToppingDetailManagementController extends HttpServlet {

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
            out.println("<title>Servlet ToppingDetailManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ToppingDetailManagementController at " + request.getContextPath() + "</h1>");
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
        int toppingID = Integer.parseInt(request.getParameter("toppingID"));

        ToppingDAO toppingDAO = new ToppingDAO();
        Topping topping = toppingDAO.findByID(toppingID);

        request.setAttribute("topping", topping);
        request.getRequestDispatcher("views/adminviews/topping-detail-management.jsp").forward(request, response);
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
        ToppingDAO toppingDAO = new ToppingDAO();

        // Read the JSON data from the request body
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }
        System.out.println("JSON Data: " + jsonInput);

        // Parse JSON data into JsonElement
        JsonElement jsonElement = JsonParser.parseString(jsonInput.toString());

        // Extract the toppingList array directly
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Extract form fields
            int toppingId = Integer.parseInt(jsonObject.getAsJsonPrimitive("topping-id").getAsString());
            String toppingImageUrl = jsonObject.getAsJsonPrimitive("topping-image-url").getAsString();
            String toppingName = jsonObject.getAsJsonPrimitive("topping-name").getAsString();
            int toppingQuantity = Integer.parseInt(jsonObject.getAsJsonPrimitive("topping-quantity").getAsString());
            int toppingPrice = Integer.parseInt(jsonObject.getAsJsonPrimitive("topping-price").getAsString());
            String toppingDescription = jsonObject.getAsJsonPrimitive("topping-description").getAsString();

            Topping topping = new Topping(toppingId, toppingName, toppingQuantity, toppingPrice, toppingImageUrl, toppingDescription);
            toppingDAO.update(topping);
            StaffDAO staffDAO = new StaffDAO();
            ProductHistoryDAO phDAO = new ProductHistoryDAO();
            HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Date date = new Date(System.currentTimeMillis());
            System.out.println(user);
            System.out.println(staffDAO.findByID(user.getId()));
            ProductHistory ph = new ProductHistory(0, toppingQuantity, date, null, staffDAO.findByID(user.getId()), null, topping, null);
            System.out.println(ph.toString());
            phDAO.createPH(ph,"update");
        }
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
