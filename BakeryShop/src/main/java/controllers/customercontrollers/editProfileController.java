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
public class editProfileController extends HttpServlet {

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
            out.println("<title>Servlet editProfileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfileController at " + request.getContextPath() + "</h1>");
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
       String currentUser = (String) session.getAttribute("username");
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String username = user.getUsername();
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.findByUsername(username);
            request.setAttribute("fullname", customer.getFullname());
            request.setAttribute("email", customer.getEmail());
            request.setAttribute("userAvatar", customer.getUserAvatar());
            request.setAttribute("address", customer.getAddress());
            request.setAttribute("phoneNumber", customer.getPhoneNumber());
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("views/customerviews/editProfile.jsp").forward(request, response);
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
            String userAvatar = request.getParameter("userAvatar");
            String fullname = request.getParameter("fullName");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");

            Customer updatedCustomer = new Customer();
            updatedCustomer.setEmail(userAvatar);
            updatedCustomer.setFullname(fullname);
            updatedCustomer.setEmail(email);
            updatedCustomer.setEmail(address);
            updatedCustomer.setEmail(phoneNumber);

            CustomerDAO cusDAO = new CustomerDAO();
            boolean isProfileUpdated = cusDAO.updateProfile(updatedCustomer);
            if (isProfileUpdated) {
                session.setAttribute("customer", updatedCustomer);
                request.setAttribute("successMessage", "Update your profile successfully!");
                request.getRequestDispatcher("views/customerviews/editProfile.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "An error occurred while updating the profile. Please try again!");
                request.getRequestDispatcher("views/customerviews/editProfile.jsp").forward(request, response);
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
