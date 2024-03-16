/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import models.Cake;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

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
        String cakeType = request.getParameter("cakeType");
        String sortBy = request.getParameter("sort");

        request.setAttribute("title", cakeType);

        CakeDAO cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.getAllCakeWithType(cakeType);

        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    Collections.sort(cakeList, new Comparator<Cake>() {
                        @Override
                        public int compare(Cake c1, Cake c2) {
                            return c1.getCakeName().compareTo(c2.getCakeName());
                        }
                    });
                    break;
                case "price":
                    Collections.sort(cakeList, new Comparator<Cake>() {
                        @Override
                        public int compare(Cake c1, Cake c2) {
                            if (c1.getCakePrice() < c2.getCakePrice()) {
                                return 1;
                            } else {
                                if (c1.getCakePrice() == c2.getCakePrice()) {
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        }
                    });
                    break;
                default:
            }
        }

        request.setAttribute("cakeList", cakeList);

        request.getRequestDispatcher("views/homeviews/Category.jsp").forward(request, response);
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
        processRequest(request, response);
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
