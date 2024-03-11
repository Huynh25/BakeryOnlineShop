/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers.admincontrollers;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import daos.CakeDAO;
import daos.GoWithDAO;
import daos.ProductHistoryDAO;
import daos.StaffDAO;
import daos.ToppingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.GoWith;
import models.ProductHistory;
import models.Topping;
import models.User;

/**
 *
 * @author ACER PC
 */
public class CakeDetailManagementController extends HttpServlet {

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
            out.println("<title>Servlet CakeDetailManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CakeDetailManagementController at " + request.getContextPath() + "</h1>");
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
        CakeDAO cakeDAO = new CakeDAO();
        GoWithDAO goWithDAO = new GoWithDAO();
        ToppingDAO toppingDAO = new ToppingDAO();

        int cakeID = Integer.parseInt(request.getParameter("cakeID"));
        List<GoWith> goWithList = goWithDAO.findAllByCakeID(cakeID);

        List<Topping> thisToppingList = new ArrayList<>();
        List<Topping> toppingList = toppingDAO.readAll();

        for (GoWith goWith : goWithList) {
            thisToppingList.add(goWith.getTopping());
        }
        Cake cake = cakeDAO.findByID(cakeID);

        List<Topping> notInThisToppingList = new ArrayList<>();

        for (Topping topping : toppingList) {
            boolean found = false;

            for (Topping thisTopping : thisToppingList) {
                if (topping.getToppingID() == thisTopping.getToppingID()) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                notInThisToppingList.add(topping);
            }
        }
        List<String> thisToppingListName = new ArrayList<>();
        for (Topping topping : thisToppingList) {
            thisToppingListName.add(topping.getToppingName());
        }
        System.out.println(thisToppingListName);

        request.setAttribute("thisToppingList", thisToppingListName);
        request.setAttribute("toppingList", notInThisToppingList);
        request.setAttribute("cake", cake);
        request.getRequestDispatcher("views/adminviews/Cake-detail-management.jsp").forward(request, response);
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
    CakeDAO cakeDAO = new CakeDAO();
    
    // Read the JSON data from the request body
    BufferedReader reader = request.getReader();
    StringBuilder jsonInput = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        jsonInput.append(line);
    }
    System.out.println("JSON Data: " + jsonInput);

    // Parse JSON data into JsonElement
    JsonElement jsonElement = JsonParser.parseString(jsonInput.toString());

    // Extract the toppingList array directly
    if (jsonElement.isJsonObject()) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Extract form fields
        int cakeId = Integer.parseInt(jsonObject.getAsJsonPrimitive("cake-id").getAsString());
        String cakeImageUrl = jsonObject.getAsJsonPrimitive("cake-image-url").getAsString();
        String cakeName = jsonObject.getAsJsonPrimitive("cake-name").getAsString();
        String cakeType = jsonObject.getAsJsonPrimitive("cake-type").getAsString();
        int cakeQuantity = Integer.parseInt(jsonObject.getAsJsonPrimitive("cake-quantity").getAsString());
        int cakePrice = Integer.parseInt(jsonObject.getAsJsonPrimitive("cake-price").getAsString());
        String cakeDescription = jsonObject.getAsJsonPrimitive("cake-description").getAsString();
        
        Cake cake = new Cake(cakeId, cakeName, cakeDescription, cakePrice, cakeImageUrl, cakeQuantity, cakeType);
        cakeDAO.update(cake);

        // Extract the toppingList array
        JsonElement toppingListElement = jsonObject.get("thisToppingList");

        if (toppingListElement != null && toppingListElement.isJsonArray()) {
            JsonArray toppingListArray = toppingListElement.getAsJsonArray();
            
            // Convert JsonArray to String array
            List<String> toppingList = new ArrayList<>();
            for (JsonElement element : toppingListArray) {
                toppingList.add(element.getAsString());
            }

            // Now you can use the form fields and toppingList as needed
            System.out.println("Cake ID: " + cakeId);
            System.out.println("Cake Image URL: " + cakeImageUrl);
            System.out.println("Cake Name: " + cakeName);
            System.out.println("Cake Type: " + cakeType);
            System.out.println("Cake Quantity: " + cakeQuantity);
            System.out.println("Cake Price: " + cakePrice);
            System.out.println("Cake Description: " + cakeDescription);
            
            ToppingDAO toppingDAO = new ToppingDAO();
            List<Topping> thisToppingList = new ArrayList<>();
            for (String topping : toppingList) {
                System.out.println("Topping: " + topping);
                thisToppingList.add(toppingDAO.findByName(topping));
            }
            
            GoWithDAO goWithDAO = new GoWithDAO();
            goWithDAO.updateGoWith(cakeId, thisToppingList);
            StaffDAO staffDAO = new StaffDAO();
            ProductHistoryDAO phDAO = new ProductHistoryDAO();
            HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Date date = new Date(System.currentTimeMillis());
            System.out.println(user);
            System.out.println(staffDAO.findByID(user.getId()));
            ProductHistory ph = new ProductHistory(0, cakeQuantity, date, null, staffDAO.findByID(user.getId()), null, null, cake);
            System.out.println(ph.toString());
            phDAO.createPH(ph,"update");

            // Respond back if needed
            response.getWriter().write("Data received successfully");
        } else {
            System.out.println("Invalid JSON format: 'thisToppingList' is missing or not an array");
            // Handle the error as needed
        }
    } else {
        System.out.println("Invalid JSON format: not an object");
        // Handle the error as needed
    }}

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
