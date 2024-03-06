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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import models.Customer;
import models.Staff;
import models.User;

/**
 *
 * @author HuynhLNCE171797
 */
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/guestview/loginView.jsp").forward(request, response);
    }

    public static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array to a string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

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
        User u = new User(username, password, "customer",-1);
        for (Customer c : list) {
            if (c.getUsername().equals(username) && c.getPassword().equalsIgnoreCase(getMD5Hash(password))) {
                session.setAttribute("user", u);
                u.setId(c.getUserID());
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
            if (s.getStaffName().equals(username) && s.getPassword().equalsIgnoreCase(getMD5Hash(password))) {
                User u;
                if (s.getManagerID() == s.getStaffID()) {
                    u = new User(username, password, "manager",s.getStaffID());
                    session.setAttribute("user", u);
                } else {
                    u = new User(username, password, "staff",s.getStaffID());
                    session.setAttribute("user", u);
                }
                System.out.println(session.getAttribute("user"));
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
