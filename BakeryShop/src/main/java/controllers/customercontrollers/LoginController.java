/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CustomerDAO;
import daos.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.Staff;
import models.User;

/**
 *
 * @author HuynhLNCE171797
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("views/guestview/loginView.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isCustomer = isCustomer(username, password, request, response);
        boolean isStaff = isStaff(username, password, request, response);
        if (isCustomer) {
            response.sendRedirect("home");
        } else if (isStaff) {
            response.sendRedirect("home");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("views/guestview/loginView.jsp").forward(request, response);
        }
    }

    public boolean isCustomer(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        CustomerDAO cd = new CustomerDAO();
        List<Customer> list = cd.readAll();
        HttpSession session = request.getSession();
        User u = new User(username, password, "customer");
        for (Customer c : list) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(3 * 24 * 60 * 60);
                response.addCookie(cookie);
//                session.setAttribute("user", u);
                session.setAttribute(username, "customer");
                return true;
            }
        }
        return false;
    }

    public boolean isStaff(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        StaffDAO sd = new StaffDAO();
        List<Staff> list = sd.readAll();
        HttpSession session = request.getSession();
        for (Staff s : list) {
            if (s.getStaffName().equals(username) && s.getPassword().equals(password)) {
                User u;
                if (s.getManagerID() == s.getStaffID()) {
                    u = new User(username, password, "manager");
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(3 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    session.setAttribute(username, "manager");
                } else {
                    u = new User(username, password, "staff");
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(3 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    session.setAttribute(username, "staff");
                }
                return true;
            }
        }
        return false;
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
