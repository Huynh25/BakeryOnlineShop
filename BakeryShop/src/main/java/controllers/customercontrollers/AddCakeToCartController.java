/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CakeDAO;
import daos.ToppingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import models.Cake;
import models.Cart;
import models.Item;
import models.Topping;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class AddCakeToCartController extends HttpServlet {

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
        String cakeID = request.getParameter("cakeID");
        String cakeType = request.getParameter("cakeType");
        String[] toppingsID = request.getParameterValues("topping");
        String buyQuantity = request.getParameter("buy-quantity");

        cakeID = (cakeID == null ? "" : cakeID);
        cakeType = (cakeType == null ? "" : cakeType);
        toppingsID = (toppingsID == null ? new String[0] : toppingsID);
        buyQuantity = (buyQuantity == null ? "" : buyQuantity);

        String text = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    text += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        String newItemText = "";
        newItemText += cakeID + ":" + buyQuantity + ":";
        for (String toppingID : toppingsID) {
            newItemText += toppingID + ",";
        }

        if (!newItemText.isEmpty()) {
            newItemText = newItemText.substring(0, newItemText.length() - 1) + ";";
            newItemText = URLEncoder.encode(newItemText, "UTF-8");
        }

        text += newItemText;

        Cookie cartCookie = new Cookie("cart", text);
        cartCookie.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(cartCookie);

        text = URLDecoder.decode(text, "UTF-8");
        CakeDAO cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.readAll();

        ToppingDAO toppingDAO = new ToppingDAO();
        List<Topping> toppingList = toppingDAO.readAll();

        Cart cart = new Cart(text, cakeList, toppingList);
        
        HttpSession session = request.getSession();
        session.setAttribute("cart", cart);

        request.getRequestDispatcher("/category?cakeType="+cakeType.replace(" ", "+")).forward(request, response);

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
