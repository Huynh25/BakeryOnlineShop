/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.customercontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import daos.CakeInOrderDAO;
import daos.OrderDAO;
import daos.ToppingDAO;
import daos.ToppingInCakeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import models.Cake;
import models.CakeInOrder;
import models.Order;
import models.Topping;
import models.ToppingInCake;
import models.User;

/**
 *
 * @author Tran Nguyen Nam Thuan CE171497
 */
public class OrderHistoryController extends HttpServlet {

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
            out.println("<title>Servlet OrderHistoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHistoryController at " + request.getContextPath() + "</h1>");
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

        OrderDAO od = new OrderDAO();
        CakeInOrderDAO ciod = new CakeInOrderDAO();
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");   
        ArrayList<Order> orderList = (ArrayList<Order>) od.readSomeByID(0 + "", user.getId());
        int numberCurrentOrder = od.numberByUserID(user.getId());
        ArrayList<ArrayList<CakeInOrder>> cakeInOrder = new ArrayList<>();
        for (Order order : orderList) {
            ArrayList<CakeInOrder> cakeInEachOrder = (ArrayList<CakeInOrder>) ciod.readSomeByOrderID(order.getOrderID());
            cakeInOrder.add(cakeInEachOrder);
        }
        request.setAttribute("numberCurrentOrder", numberCurrentOrder);
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalNumberPage", (int) Math.ceil((double) numberCurrentOrder / 8));
        request.setAttribute("orderList", orderList);
        request.setAttribute("cakeInOrder", cakeInOrder);
        request.getRequestDispatcher("views/customerviews/purchase-history.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String filter = request.getParameter("filter");
        String cioID = request.getParameter("cioID");
         HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");  
        if (filter != null) {
            String numberPage = request.getParameter("numberPage");
            OrderDAO od = new OrderDAO();
            CakeInOrderDAO ciod = new CakeInOrderDAO();
            String search = request.getParameter("search");
            String orderDate = request.getParameter("order-date");
            String receivedDate = request.getParameter("received-date");
            ArrayList<Order> orderList = (ArrayList<Order>) od.filterSomeByID((Integer.parseInt(numberPage) - 1) * 8 + "", user.getId(), filter, search, orderDate, receivedDate);
            ArrayList<ArrayList<CakeInOrder>> cakeInOrder = new ArrayList<>();
            for (Order order : orderList) {
                ArrayList<CakeInOrder> cakeInEachOrder = (ArrayList<CakeInOrder>) ciod.readSomeByOrderID(order.getOrderID());
                cakeInOrder.add(cakeInEachOrder);
            }

            int numberCurrentOrder = od.numberFilterByUserID(user.getId(), filter, search, orderDate, receivedDate);
            Gson gson = new Gson();
            response.setContentType("application/json");
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("numberCurrentOrder", gson.toJsonTree(numberCurrentOrder));
            jsonObject.add("jsonOrderList", gson.toJsonTree(orderList));
            jsonObject.add("jsonCakeInOrder", gson.toJsonTree(cakeInOrder));
            String jsonData = gson.toJson(jsonObject);
            response.getWriter().println(jsonData);
        } else if(cioID !=null){
            ToppingInCakeDAO ticd = new ToppingInCakeDAO();
            ArrayList<ToppingInCake> toppingInCakeList = (ArrayList<ToppingInCake>) ticd.findAllByCioID(cioID);
            Gson gson = new Gson();
            response.setContentType("application/json");
            String jsonToppingInCakeList = gson.toJson(toppingInCakeList);
            response.getWriter().print(jsonToppingInCakeList);
        }else{
            String orderID = request.getParameter("orderID");
            String currentQuantity = request.getParameter("currentQuantity");
            CakeInOrderDAO ciod=new CakeInOrderDAO();
            ArrayList<CakeInOrder> cakeInOrderList = (ArrayList<CakeInOrder>) ciod.showMoreByOrderID(orderID,currentQuantity);
            Gson gson = new Gson();
            response.setContentType("application/json");
            String jsonCakeInOrderList = gson.toJson(cakeInOrderList);
            response.getWriter().print(jsonCakeInOrderList);
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
