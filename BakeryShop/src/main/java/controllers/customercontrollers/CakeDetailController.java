/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CakeDAO;
import daos.GoWithDAO;
import daos.RatingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.GoWith;
import models.Rating;
import models.Topping;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class CakeDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String cakeIDString = request.getParameter("cakeID");
        if (cakeIDString == null) {
            response.sendRedirect("/catalog");
            return;
        }
        
        int cakeID = -1;
        
        try {
            cakeID = Integer.parseInt(cakeIDString);
        } catch (NumberFormatException e) {
            response.sendRedirect("/catalog");
            return;
        }

        CakeDAO cakeDAO = new CakeDAO();
        Cake cake = cakeDAO.findByID(cakeID);
        if (cake == null) {
            response.sendRedirect("/catalog");
            return;
        }

        request.setAttribute("cake", cake);

        // Get list of ratings that is in the cake, then calculate average of ratings
        RatingDAO ratingDAO = new RatingDAO();

        List<Rating> ratingList = ratingDAO.getAllRatingsByCakeID(cakeID);
        int averageRating = 0;

        for (Rating rating : ratingList) {
            averageRating += rating.getRatingValue();
        }

        if (!ratingList.isEmpty()) {
            averageRating /= ratingList.size();
        }

        request.setAttribute("cakeRating", averageRating);

        //Get list of toppings that go with the cake
        GoWithDAO goWithDAO = new GoWithDAO();
        List<GoWith> goWithList = goWithDAO.findAllByCakeID(cakeID);
        List<Topping> toppingList = new ArrayList<>();
        for (GoWith goWith : goWithList) {
            toppingList.add(goWith.getTopping());
        }

        request.setAttribute("toppings", toppingList);

        request.getRequestDispatcher("views/homeviews/CakeDetail.jsp").forward(request, response);
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
        processRequest(request, response);
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
