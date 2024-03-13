/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import com.google.gson.JsonObject;
import daos.CakeDAO;
import daos.CakeInOrderDAO;
import daos.CustomerDAO;
import daos.OrderDAO;
import daos.ToppingDAO;
import daos.ToppingInCakeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Cake;
import models.CakeInOrder;
import models.Cart;
import models.Config;
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

    private String testString = "testString";
    private CakeDAO cakeDAO;
    private HttpSession session;
    private Cart cart;
    private ToppingDAO toppingDAO;

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

        //Begin process return from VNPAY
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = null;
            if (fieldName.equals("vnp_OrderInfo")) {
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.UTF_8.toString());
            } else {
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = Config.hashAllFields(fields);

//        String transacID = request.getParameter("vnp_TxnRef");
//        String amount = request.getParameter("vnp_Amount");
        String orderInfo = request.getParameter("vnp_OrderInfo");
//        String responseCode = request.getParameter("vnp_ResponseCode");
//        String transacNo = request.getParameter("vnp_TransactionNo");
//        String bankCode = request.getParameter("vnp_BankCode");
//        String payDate = request.getParameter("vnp_PayDate");
//
//        System.out.println("Mã giao dịch thanh toán: " + transacID);
//        System.out.println("Số tiền: " + amount);
//        System.out.println("Mô tả giao dịch: " + orderInfo);
//        System.out.println("Mã lỗi thanh toán: " + responseCode);
//        System.out.println("Mã giao dịch tại CTT VNPAY-QR: " + transacNo);
//        System.out.println("Mã ngân hàng thanh toán: " + bankCode);
//        System.out.println("Thời gian thanh toán: " + payDate);
        String status = "invalid signature";
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                status = "successful";
            } else {
                status = "failed";
            }
        }
//        System.out.println("Tình trạng giao dịch: " + status);
        if ("successful".equals(status)) {
            purchased(request, response, true, orderInfo);
//        } else if ("failed".equals(status)) {
//            purchased(request, response, false, orderInfo);
        } else {
            response.sendRedirect("home");
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
        String description = (String) request.getAttribute("description");

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
        cakeDAO = new CakeDAO();
        List<Cake> cakeList = cakeDAO.readAll();

        toppingDAO = new ToppingDAO();
        List<Topping> toppingList = toppingDAO.readAll();

        cart = new Cart(text, cakeList, toppingList);

        session = request.getSession();
        User userFromSession = (User) session.getAttribute("user");

        if (userFromSession == null || !userFromSession.getRole().equalsIgnoreCase("customer")) {
            response.sendRedirect("login");
            return;
        }

        if (payment == null || cart.isEmpty()) {
            response.sendRedirect("login");
            return;
        } else if ("e-wallet".equals(payment)) {
            testString = "before go to e wallet";
            request.getServletContext().setAttribute("amount", cart.getTotalPrice());
            response.setStatus(307);
            response.addHeader("Location", "e-wallet");
            return;
        }

        purchased(request, response, false, description);
    }

    private void purchased(HttpServletRequest request, HttpServletResponse response, boolean wasPaid, String description)
            throws ServletException, IOException {
        System.setOut(new PrintStream(System.out, true, "UTF8"));

        User userFromSession = (User) session.getAttribute("user");
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order();
        order.setOrderID(-1);
        order.setUserID(userFromSession.getId());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDescription(description);
        order.setWasPaid(wasPaid);

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
        response.sendRedirect("order-history");
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
