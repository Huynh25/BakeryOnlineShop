/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.admincontrollers;

import com.google.gson.Gson;
import daos.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Order;

/**
 *
 * @author ACER PC
 */
public class UnconfirmedOrdersController extends HttpServlet {
   
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
            out.println("<title>Servlet UnconfirmedOrdersController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnconfirmedOrdersController at " + request.getContextPath () + "</h1>");
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
        OrderDAO orderDAO = new OrderDAO();
        String searchTerm1 = request.getParameter("searchTerm");
        searchTerm = searchTerm1;
        System.out.println(searchTerm1);
        
        List<Order> orderList = orderDAO.readAll();
        List<Order> myOrderList = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getStatus().equalsIgnoreCase("Waiting")) {
                myOrderList.add(order);
            }
        }
        request.setAttribute("myOrderList", myOrderList);
        request.getRequestDispatcher("views/adminviews/UnconfirmedOrder.jsp").forward(request, response);
    } 
    
    String searchTerm = null;

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
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.readAll();
        List<Order> uncomfirmedOrderList = new ArrayList<>();
        
        // Kiểm tra xem có thông tin tìm kiếm hay không
        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Nếu có, thực hiện tìm kiếm
            System.out.println("hello");
            orderList = orderDAO.searchOrders(searchTerm);
        } else {
            // Nếu không, lấy toàn bộ danh sách
            orderList = orderDAO.readAll();
        }
        
        for (Order order : orderList) {
            if (order.getStatus().equalsIgnoreCase("Waiting")) {
                uncomfirmedOrderList.add(order);
            }
        }

        // Convert danh sách đơn hàng thành JSON
        Gson gson = new Gson();
        String json = gson.toJson(uncomfirmedOrderList);

        // Thiết lập kiểu nội dung và gửi phản hồi JSON
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
