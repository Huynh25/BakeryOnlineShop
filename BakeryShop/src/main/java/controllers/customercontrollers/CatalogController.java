package controllers.customercontrollers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import daos.CakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Cake;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */

@WebServlet(name = "CatalogController", urlPatterns = {"/catalog"})
public class CatalogController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        CakeDAO cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.getAllCakeWithUniqueType();
        request.setAttribute("cakeList", cakeList);
        
        String currentPageString = request.getParameter("currentPage");
        if (currentPageString == null) {
            currentPageString = "1";
        }
        
        int currentPage = Integer.parseInt(currentPageString);
        request.setAttribute("currentPage", currentPage);
        
        int indicatorsLength = cakeList.size() / 6;
        List<Integer> indicators = new ArrayList<>();
        for (int i = 1; i <= indicatorsLength; ++i) {
            indicators.add(i);
        }
        request.setAttribute("indicators", indicators);
        
        request.getRequestDispatcher("views/homeviews/Catalog.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
