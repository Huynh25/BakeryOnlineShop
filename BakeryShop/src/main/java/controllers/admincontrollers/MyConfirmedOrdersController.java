/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import com.google.gson.Gson;
import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import daos.OrderDAO;
import daos.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.Order;
import models.Staff;
import models.User;

/**
 *
 * @author Nguyen Truong An
 */
@WebServlet(name = "MyConfirmedOrdersController", urlPatterns = {"/my-confirmed-orders"})

public class MyConfirmedOrdersController extends HttpServlet {

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
            out.println("<title>Servlet MyConfirmedOrdersController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyConfirmedOrdersController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    StaffDAO staffDAO = new StaffDAO();

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

// Đọc danh sách đơn hàng từ cơ sở dữ liệu
        String searchTerm1 = request.getParameter("searchTerm");
        searchTerm = searchTerm1;
        System.out.println(searchTerm1);
        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderList = orderDAO.readAll();

        // Chuyển đến trang JSP và truyền danh sách đơn hàng
        request.setAttribute("myOrderList", orderList);
        request.getRequestDispatcher("views/adminviews/my-confirmed-orders.jsp").forward(request, response);
    }

    String searchTerm = null;

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
        // Lấy thông tin tìm kiếm từ request
//        String searchTerm = request.getParameter("searchTerm");
        System.out.println(searchTerm);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        int staffID = user.getId();

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = new ArrayList<>();
        List<Order> myOrderList = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            System.out.println("hello");
            orderList = orderDAO.searchOrders(searchTerm);
        } else {
            orderList = orderDAO.readAll();
        }
        if (role.equalsIgnoreCase("staff")) {
            for (Order order : orderList) {
                if (order.getStaffID() == staffID) {
                    myOrderList.add(order);
                }
            }
        } else {
            for (Order order : orderList) {
                    myOrderList.add(order);
            }
        }
        System.out.println(role);

        Gson gson = new Gson();
        String json = gson.toJson(myOrderList);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

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
