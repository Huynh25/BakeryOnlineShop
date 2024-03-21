/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import daos.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Staff;
import models.User;

/**
 *
 * @author acer
 */
public class editProfileStaff extends HttpServlet {

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
            out.println("<title>Servlet editProfileStaff</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfileStaff at " + request.getContextPath() + "</h1>");
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
            StaffDAO staffDAO = new StaffDAO();
            Staff staff = staffDAO.findByFullname(username);
            request.setAttribute("staff", staff);
            if (staff != null) {
                request.setAttribute("staff", staff);
                request.getRequestDispatcher("views/adminviews/editProfileStaff.jsp").forward(request, response);
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
       HttpSession session = (HttpSession) request.getSession();
        User user = (User) session.getAttribute("user");
                    StaffDAO stDAO = new StaffDAO();
         Staff thisStaff = stDAO.findByID(user.getId());
        System.out.println(user);
        if (user != null) {
//            String staffID = request.getParameter("staffID");
            String staffAvatar = request.getParameter("avatar");
            String staffName = request.getParameter("staffName");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            System.out.println("StaffName:" +staffName);

            Staff updateStaff = new Staff(user.getId(), user.getUsername(), user.getPassword(), fullname, email, staffAvatar, address, phoneNumber, thisStaff.getManagerID());


            boolean isStaffUpdate = stDAO.updateProfile(updateStaff);
            System.out.println(isStaffUpdate);
            if (isStaffUpdate) {
                session.setAttribute("staff", updateStaff);
                request.setAttribute("successMessage", "Update success");
                request.getRequestDispatcher("views/adminviews/editProfileStaff.jsp").forward(request, response);

            } else {
                request.setAttribute("errorMessage", "An error occurres while update. Please try again!");
                request.getRequestDispatcher("views/adminviews/editProfileStaff.jsp").forward(request, response);
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
