/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CustomerDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Customer;
import models.User;

/**
 *
 * @author HuynhLNCE171797
 */
public class ChangePasswordController extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = (HttpSession) request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String username = user.getUsername();
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.findByUsername(username);
            request.setAttribute("customer", customer);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("views/customerviews/changePassword.jsp").forward(request, response);
            }
        }
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
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                String userID = request.getParameter("userID");
                String currentPassword = request.getParameter("currentpassword");
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("password2");

                CustomerDAO cusDAO = new CustomerDAO();
                boolean passwordVerified = cusDAO.verifyPassword(Integer.parseInt(userID), currentPassword);

                if (passwordVerified && newPassword.equals(confirmPassword)) {
                    boolean passwordChanged = cusDAO.changePassword(Integer.parseInt(userID), newPassword);

                    if (passwordChanged) {
                        request.setAttribute("successMessage", "Password changed successfully!");
                        request.getRequestDispatcher("views/customerviews/changePassword.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "An error occurred while changing password. Please try again!");
                        request.getRequestDispatcher("views/customerviews/changePassword.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("errorMessage", "Current password is incorrect or new passwords do not match.");
                    request.getRequestDispatcher("views/customerviews/changePassword.jsp").forward(request, response);
                }
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
