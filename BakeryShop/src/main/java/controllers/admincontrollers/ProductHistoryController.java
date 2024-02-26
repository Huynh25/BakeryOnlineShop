/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import daos.ProductHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.ProductHistory;

/**
 *
 * @author Tran Nguyen Nam Thuan CE171497
 */
public class ProductHistoryController extends HttpServlet {

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
            out.println("<title>Servlet ProductHistoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductHistoryController at " + request.getContextPath() + "</h1>");
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
        ProductHistoryDAO phd = new ProductHistoryDAO();
        ArrayList<ProductHistory> phList = (ArrayList<ProductHistory>) phd.readSome(1, "all","","");
        int numberAllPage = phd.countNumberRow("all","","");
         int allPage= (int) Math.ceil(numberAllPage*1.0/ 8);
        request.setAttribute("allPage", allPage);
        request.setAttribute("numberAllPage", numberAllPage);
        request.setAttribute("phList", phList);
        request.getRequestDispatcher("views/adminviews/product-history.jsp").forward(request, response);
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
        String filter = request.getParameter("filter");
        String search = request.getParameter("search");
        String date = request.getParameter("date");
        String currentPage=request.getParameter("currentPage");
        ProductHistoryDAO phd = new ProductHistoryDAO();
        ArrayList<ProductHistory> phList = (ArrayList<ProductHistory>) phd.readSome(Integer.parseInt(currentPage), filter,search,date);
        int numberAllPage = phd.countNumberRow(filter,search,date);
        Gson gson = new Gson();
        response.setContentType("application/json");
        int allPage= (int) Math.ceil(numberAllPage*1.0 / 8);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("allPage", gson.toJsonTree(allPage));
        jsonObject.add("numberAllPage", gson.toJsonTree(numberAllPage));
        jsonObject.add("phList", gson.toJsonTree(phList));
        String jsonData = gson.toJson(jsonObject);
        response.getWriter().println(jsonData);

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
