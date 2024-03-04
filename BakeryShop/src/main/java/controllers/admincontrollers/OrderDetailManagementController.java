/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.admincontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import daos.CakeDAO;
import daos.CakeInOrderDAO;
import daos.CustomerDAO;
import daos.OrderDAO;
import daos.RatingDAO;
import daos.ToppingDAO;
import daos.ToppingInCakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.Customer;
import models.Order;
import models.Rating;
import models.ToppingInCake;

/**
 *
 * @author Nguyen Truong An
 */
public class OrderDetailManagementController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderDetailManagementController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailManagementController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String prePage = request.getParameter("prePage");
        String preURL = request.getParameter("preURL");
        System.out.println("prepage:");
        System.out.println(prePage);
        System.out.println(preURL);
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        Order thisOrder = orderDAO.findByID(orderID);
        Customer thisCustomer = customerDAO.findByID(thisOrder.getUserID());
        
        CakeDAO cakeDAO = new CakeDAO();
        ToppingDAO topping = new ToppingDAO();
        CakeInOrderDAO cioDAO = new CakeInOrderDAO();
        ToppingInCakeDAO ticDAO = new ToppingInCakeDAO();
        
        RatingDAO ratingDAO = new RatingDAO();
        List<Rating> ratingList = ratingDAO.readAll();
        List<Rating> thisCustomerRatingList = new ArrayList<>();
        System.out.println(thisCustomer.toString());
        for (Rating rating : ratingList) {
            if (rating.getCustomer().getUserID() == thisCustomer.getUserID()) {
                thisCustomerRatingList.add(rating);
            }
        }
        
        List<Cake> cakeList = cioDAO.findCakesInOrder(orderID);
        List<ToppingInCake> toppingInCakeList = ticDAO.getToppingsInCakesWithOrder(orderID);
        for (ToppingInCake toppingInCake : toppingInCakeList) {
            System.out.println(toppingInCake.toString());
            System.out.println(toppingInCake.getTicQuantity());
            System.out.println(toppingInCake.getTopping().toString());
            System.out.println(toppingInCake.getTicQuantity());
            System.out.println(toppingInCake.getCakeInOrder().getCake().toString());
            System.out.println(toppingInCake.getCakeInOrder().getOrder().toString());
        }
        
        request.setAttribute("user", thisCustomer);
        request.setAttribute("prePage", prePage);
        request.setAttribute("preURL", preURL);
        request.setAttribute("order", thisOrder);
        request.setAttribute("ratingList", thisCustomerRatingList);
        request.setAttribute("toppingInCakeList", toppingInCakeList);
        request.getRequestDispatcher("views/adminviews/Order-detail-management.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
int orderID = Integer.parseInt(request.getParameter("orderID"));
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        Order thisOrder = orderDAO.findByID(orderID);
        Customer thisCustomer = customerDAO.findByID(thisOrder.getUserID());
        
        CakeDAO cakeDAO = new CakeDAO();
        ToppingDAO topping = new ToppingDAO();
        CakeInOrderDAO cioDAO = new CakeInOrderDAO();
        ToppingInCakeDAO ticDAO = new ToppingInCakeDAO();
        
        RatingDAO ratingDAO = new RatingDAO();
        List<Rating> ratingList = ratingDAO.readAll();
        List<Rating> thisCustomerRatingList = new ArrayList<>();
        System.out.println(thisCustomer.toString());
        for (Rating rating : ratingList) {
            if (rating.getCustomer().getUserID() == thisCustomer.getUserID()) {
                thisCustomerRatingList.add(rating);
            }
        }
        
        List<Cake> cakeList = cioDAO.findCakesInOrder(orderID);
        List<ToppingInCake> toppingInCakeList = ticDAO.getToppingsInCakesWithOrder(orderID);
        for (ToppingInCake toppingInCake : toppingInCakeList) {
            System.out.println(toppingInCake.toString());
            System.out.println(toppingInCake.getTicQuantity());
            System.out.println(toppingInCake.getTopping().toString());
            System.out.println(toppingInCake.getTicQuantity());
            System.out.println(toppingInCake.getCakeInOrder().getCake().toString());
            System.out.println(toppingInCake.getCakeInOrder().getOrder().toString());
        }
        


    Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();

// Chuyển đổi danh sách toppingInCakeList thành JSON
String toppingInCakeJson = gson.toJson(toppingInCakeList);
jsonObject.addProperty("toppingInCakeList", toppingInCakeJson);

// Chuyển đổi danh sách ratingList thành JSON
String ratingListJson = gson.toJson(thisCustomerRatingList);
jsonObject.addProperty("ratingList", ratingListJson);

// Chuyển đổi JsonObject thành chuỗi JSON
String json = jsonObject.toString();

response.setContentType("application/json");
PrintWriter out = response.getWriter();
out.print(json);
out.flush();
}

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
