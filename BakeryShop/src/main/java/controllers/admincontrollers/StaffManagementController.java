/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import daos.StaffDAO;
import daos.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Staff;

/**
 *
 * @author Tran Nguyen Nam Thuan CE171497
 */
public class StaffManagementController extends HttpServlet {

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
            out.println("<title>Servlet StaffManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffManagementController at " + request.getContextPath() + "</h1>");
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
        String staffID = request.getParameter("staffID");
        StaffDAO sd = new StaffDAO();
        if (staffID != null) {
            Staff s = sd.findByID(Integer.parseInt(staffID));
            request.setAttribute("staff", s);
            request.getRequestDispatcher("views/adminviews/update-staff.jsp").forward(request, response);
        } else {
            ArrayList<Staff> staffList = (ArrayList<Staff>) sd.readAll();
            request.setAttribute("staffList", staffList);
            request.getRequestDispatcher("views/adminviews/staff-management.jsp").forward(request, response);
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
        String staffID = request.getParameter("staff-id");
        StaffDAO sd = new StaffDAO();
        if (staffID != null) {
            String staffName = request.getParameter("staff-name");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String avatar = request.getParameter("avatar");
            Staff s=new Staff(Integer.parseInt(staffID), staffName, password, fullname, email, avatar, address, phoneNumber, 0);
            sd.update(s);
            request.setAttribute("staff", s);
            request.setAttribute("message", "Update Staff successfully");
            request.getRequestDispatcher("views/adminviews/update-staff.jsp").forward(request, response);
        } else {
            String staffName = request.getParameter("staff-name");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String avatar = request.getParameter("avatar");
            UserDAO ud = new UserDAO();
            if (ud.isValidUserName(staffName)) {
                Staff s = new Staff(0, staffName, password, fullname, email, avatar, address, phoneNumber, 0);
                sd.create(s);
                request.setAttribute("message", "Create Staff successfully");
            } else {
                request.setAttribute("message", "Staff Name already exists in the database");
            }
            request.getRequestDispatcher("views/adminviews/add-staff.jsp").forward(request, response);
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
