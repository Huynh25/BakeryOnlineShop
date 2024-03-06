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
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.Cart;
import models.Item;
import models.Topping;

/**
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class EditCartController extends HttpServlet {

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
        String type = request.getParameter("type");
        String[] toppingsID = request.getParameterValues("toppingID");
        String cakeID = request.getParameter("cakeID");

        List<Topping> listTopping = new ArrayList<>();
        for (String toppingID : toppingsID) {
            Topping topping = new Topping();
            topping.setToppingID(Integer.parseInt(toppingID));
            listTopping.add(topping);
        }
        
        Cake cake = new Cake();
        cake.setCakeID(Integer.parseInt(cakeID));
        
        Item inputItem = new Item();
        inputItem.setCake(cake);
        inputItem.setToppings(listTopping);
        
        String valueString = request.getParameter("value");
        int value = 1;
        try {
            value = Integer.parseInt(valueString);
            if (value <= 0) {
                value = 1;
            }
        } catch (Exception e) {
            value = 1;
        }

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
        List<Item> items = cart.getItems();
        Item item = cart.getItemInCart(inputItem);
        
        int maxQuantityCouldBeSet = 0;
        int totalCakeIDInCart = 0;
        for (Item i : items) {
            if (item.getCake().getCakeID() == i.getCake().getCakeID()) {
                totalCakeIDInCart += i.getBuyQuantity();
            }
        }

        maxQuantityCouldBeSet = item.getCake().getCakeQuantity() - totalCakeIDInCart;
        int maxQuantityTopping = 0;
        List<Topping> toppings = item.getToppings();
        int length = toppings.size();
        for (int i = 0; i < length; ++i) {
            if (i == 0) {
                maxQuantityTopping = toppings.get(0).getToppingQuantity();
            } else if (maxQuantityTopping > toppings.get(i).getToppingQuantity()) {
                maxQuantityTopping = toppings.get(i).getToppingQuantity();
            }
        }

        maxQuantityTopping = maxQuantityTopping - totalCakeIDInCart;

        if (maxQuantityCouldBeSet > maxQuantityTopping) {
            maxQuantityCouldBeSet = maxQuantityTopping;
        }

        maxQuantityCouldBeSet += item.getBuyQuantity();

        switch (type) {
            case "increase":
                if (item.getBuyQuantity() < maxQuantityCouldBeSet) {
                    item.setBuyQuantity(item.getBuyQuantity() + 1);
                }
                break;
            case "decrease":
                if (item.getBuyQuantity() > 1) {
                    item.setBuyQuantity(item.getBuyQuantity() - 1);
                }
                break;
            case "change":
                if (value < maxQuantityCouldBeSet) {
                    item.setBuyQuantity(value);
                }
                break;
            case "remove":
                items.remove(item);
                item.setBuyQuantity(0);
                item.setToppings(new ArrayList<Topping>());
                break;
        }

        text = cart.toString();
        text = URLEncoder.encode(text, "UTF-8");

        Cookie cartCookie = new Cookie("cart", text);
        cartCookie.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(cartCookie);

        HttpSession session = request.getSession();
        session.setAttribute("cart", cart);

        String json = "{"
                + "\"product\":\"" + cart.getTotalQuantity() + "\","
                + "\"cartPrice\":" + cart.getTotalPrice() + ","
                + "\"productPrice\":" + item.getTotalPrice() + ","
                + "\"quantity\":" + item.getBuyQuantity()
                + "}";

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
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
