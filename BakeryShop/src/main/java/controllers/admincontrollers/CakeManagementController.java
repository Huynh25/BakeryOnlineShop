/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers.admincontrollers;

import daos.CakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Cake;

/**
 *
 * @author ACER PC
 */
public class CakeManagementController extends HttpServlet {
   
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
            out.println("<title>Servlet CakeManagementController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CakeManagementController at " + request.getContextPath () + "</h1>");
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
        
        
        CakeDAO cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.readAll();
        List<String> cakeTypes = cakeDAO.getAllTypes();
        
        
        request.setAttribute("cakeTypes", cakeTypes);
        request.setAttribute("isEmpty", cakeList.isEmpty());
        request.setAttribute("cakeList", cakeList);
        request.getRequestDispatcher("views/adminviews/Cake-management.jsp").forward(request, response);
    } 

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
        String searchTerm = request.getParameter("searchTerm");
        CakeDAO cakeDAO = new CakeDAO();
        
        List<Cake> searchList = cakeDAO.searchAllInCakes(searchTerm);
        List<String> cakeTypes = cakeDAO.getAllTypes();
        
        
        request.setAttribute("cakeList", searchList);
        request.setAttribute("cakeTypes", cakeTypes);
        request.setAttribute("isEmpty", searchList.isEmpty());
        request.getRequestDispatcher("views/adminviews/Cake-management.jsp").forward(request, response);
        processRequest(request, response);
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
