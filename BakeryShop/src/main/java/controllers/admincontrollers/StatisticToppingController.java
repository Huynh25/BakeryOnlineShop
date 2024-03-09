/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import daos.OrderDAO;
import daos.ToppingDAO;
import daos.ToppingInCakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Order;
import models.Topping;
import models.ToppingInCake;

/**
 *
 * @author Tran Nguyen Nam Thuan CE171497
 */
public class StatisticToppingController extends HttpServlet {

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
            out.println("<title>Servlet StatisticToppingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticToppingController at " + request.getContextPath() + "</h1>");
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
        ToppingInCakeDAO ticd = new ToppingInCakeDAO();
        ToppingDAO td = new ToppingDAO();
        OrderDAO od = new OrderDAO();
        ArrayList<ToppingInCake> toppingInCakeList = (ArrayList< ToppingInCake>) ticd.readAll();
        ArrayList<Topping> toppingList = (ArrayList<Topping>) td.readAll();
        ArrayList<Order> orderList = (ArrayList<Order>) od.readAll();
        response.setContentType("application/json");
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("toppingInCakeList", gson.toJsonTree(toppingInCakeList));
        jsonObject.add("toppingList", gson.toJsonTree(toppingList));
        jsonObject.add("orderList", gson.toJsonTree(orderList));
        String jsonData = gson.toJson(jsonObject);
        response.getWriter().println(jsonData);
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
