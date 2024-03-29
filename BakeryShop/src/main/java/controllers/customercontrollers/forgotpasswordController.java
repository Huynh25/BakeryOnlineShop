/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CustomerDAO;
import daos.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Customer;
import models.Email;
import models.Staff;
import models.User;

/**
 *
 * @author acer
 */
public class forgotpasswordController extends HttpServlet {

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
            out.println("<title>Servlet forgotpasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forgotpasswordController at " + request.getContextPath() + "</h1>");
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
        String password1 = request.getParameter("password1");
        System.out.println(password1);
        if (password1 == null) {
            HttpSession session = request.getSession();
            if (session.getAttribute("OTPCode") != null) {
                String otpInput = "";
                for (int i = 1; i <= 6; i++) {
                    otpInput += request.getParameter("opt-" + i);
                }
                String otpcode = (String) session.getAttribute("OTPCode");
                if (otpInput.equals(otpcode)) {
                    User u = (User) session.getAttribute("userOTP");
                    User user = new User(u.getUsername(), u.getPassword(), u.getRole(), u.getId());
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("views/guestview/resetPassword.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "OTP is incorrect");
                    request.getRequestDispatcher("views/guestview/forgotpassword.jsp").forward(request, response);
                }

            } else {
                System.out.println("aaa");
                request.getRequestDispatcher("views/guestview/forgotpassword.jsp").forward(request, response);
            }
        } else {
            String password2 = request.getParameter("password2");
            if (password1.equals(password2)) {
                CustomerDAO cd = new CustomerDAO();
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("userOTP");
                if (u.getRole().equals("customer")) {
                    cd.updatePassword(password1, u.getId());
                } else {
                    StaffDAO sd = new StaffDAO();
                    sd.updatePassworD(password1, u.getId());
                }
                request.setAttribute("message", "Password has been changed");
            } else {
                request.setAttribute("message", "Confirm password is not the same");
            }
            request.getRequestDispatcher("views/guestview/resetPassword.jsp").forward(request, response);
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
        Email e = new Email();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        if (username != null) {
            session.setAttribute("username", username);
            session.setAttribute("email", email);
        } else {
            username = (String) session.getAttribute("username");
            email = (String) session.getAttribute("email");
        }
        CustomerDAO cd = new CustomerDAO();
        Customer customer = cd.findByUsernameAndEmail(username, email);
        StaffDAO sd = new StaffDAO();
        Staff staff = sd.findByUsernameAndEmail(username, email);
        if (customer != null) {
            String OTPCode = e.getRandom();
            boolean isSend = e.sendEmail(customer.getEmail(), OTPCode);
            if (isSend) {

                User userOTP = new User(username, customer.getPassword(), "customer", customer.getUserID());
                session.setAttribute("OTPCode", OTPCode);
                session.setAttribute("userOTP", userOTP);
                request.getRequestDispatcher("views/guestview/OTPview.jsp").forward(request, response);
            }
        } else if (staff != null) {
            String OTPCode = e.getRandom();
            boolean isSend = e.sendEmail(staff.getEmail(), OTPCode);
            if (isSend) {
                User userOTP;
                if (staff.getStaffID() == staff.getManagerID()) {
                    userOTP = new User(username, staff.getPassword(), "manager", staff.getStaffID());
                } else {
                    userOTP = new User(username, staff.getPassword(), "staff", staff.getStaffID());
                }

                session.setAttribute("OTPCode", OTPCode);
                session.setAttribute("userOTP", userOTP);
                request.getRequestDispatcher("views/guestview/OTPview.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Username or email is incorrect");
            request.getRequestDispatcher("views/guestview/forgotpassword.jsp").forward(request, response);
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
