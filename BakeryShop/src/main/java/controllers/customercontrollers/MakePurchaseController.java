/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import daos.CakeDAO;
import daos.CakeInOrderDAO;
import daos.CustomerDAO;
import daos.OrderDAO;
import daos.ToppingDAO;
import daos.ToppingInCakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.CakeInOrder;
import models.Cart;
import models.Customer;
import models.Item;
import models.Order;
import models.Topping;
import models.ToppingInCake;
import models.User;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class MakePurchaseController extends HttpServlet {

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
        String description = request.getParameter("description");
        String payment = request.getParameter("payment");
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

        text = URLDecoder.decode(text, "UTF-8");
        CakeDAO cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.readAll();

        ToppingDAO toppingDAO = new ToppingDAO();
        List<Topping> toppingList = toppingDAO.readAll();

        Cart cart = new Cart(text, cakeList, toppingList);

        HttpSession session = request.getSession();
        User userFromSession = (User) session.getAttribute("user");

        if (userFromSession == null || !userFromSession.getRole().equalsIgnoreCase("customer")) {
            response.sendRedirect("login");
            return;
        }

        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setOrderID(-1);
        order.setUserID(userFromSession.getId());
        order.setTotalPrice(cart.getTotalPrice());
        order.setWasPaid(false);
        order.setOrderDescription(description);

        LocalDate date = LocalDate.now();
        Date sqlDate = new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth());

        order.setOrderDate(sqlDate);
        order.setStatus("Waiting");

        orderDAO.create(order);
        List<Item> items = cart.getItems();
        CakeInOrderDAO cioDAO = new CakeInOrderDAO();
        ToppingInCakeDAO ticDAO = new ToppingInCakeDAO();
        for (Item item : items) {
            CakeInOrder cio = new CakeInOrder();
            cio.setCioID(-1);
            int buyQuantity = item.getBuyQuantity();
            cio.setCioQuantity(buyQuantity);
            cio.setCake(item.getCake());
            cio.setOrder(order);
            cioDAO.create(cio);

            for (Topping topping : item.getToppings()) {
                ToppingInCake tic = new ToppingInCake();
                tic.setCakeInOrder(cio);
                tic.setTicQuantity(buyQuantity);
                tic.setTopping(topping);
                ticDAO.create(tic);

                topping.setToppingQuantity(topping.getToppingQuantity() - buyQuantity);
                toppingDAO.updateQuantity(topping);
            }

            item.getCake().setCakeQuantity(item.getCake().getCakeQuantity() - buyQuantity);
            cakeDAO.updateQuantity(item.getCake());
        }

        session.removeAttribute("cart");
        response.sendRedirect("catalog");
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
