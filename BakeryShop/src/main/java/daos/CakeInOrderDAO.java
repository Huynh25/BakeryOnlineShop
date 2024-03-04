/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.CakeDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.CakeInOrder;
import models.Order;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class CakeInOrderDAO extends AbstractDAO<CakeInOrder> {

    CakeDAO cDAO = new CakeDAO();
    OrderDAO oDAO = new OrderDAO();

    @Override
    public List<CakeInOrder> readAll() {
        List<CakeInOrder> cakeInOrders = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[CakeInOrder]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                CakeInOrder cakeInOrder = new CakeInOrder();
                cakeInOrder.setCioID(rs.getInt("cioID"));
                
                Cake cake = cDAO.findByID(rs.getInt("cakeID"));
                
                Order order = oDAO.findByID(rs.getInt("orderID"));
                cakeInOrder.setCioQuantity(rs.getInt("cioQuantity"));
                cakeInOrder.setOrder(order);
                cakeInOrder.setCake(cake);

                cakeInOrders.add(cakeInOrder);
            }

        } catch (SQLException ex) {
        }
        return cakeInOrders;
    }

    @Override
    public void create(CakeInOrder object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CakeInOrder object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CakeInOrder findByID(int id) {
try {

            String sql = "Select * from [dbo].[CakeInOrder]"
                    + "where cioID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                CakeInOrder cakeInOrder = new CakeInOrder();
                cakeInOrder.setCioID(rs.getInt("cioID"));
                
                Cake cake = cDAO.findByID(rs.getInt("cakeID"));
                
                Order order = oDAO.findByID(rs.getInt("orderID"));
                cakeInOrder.setCioQuantity(rs.getInt("cioQuantity"));
                cakeInOrder.setOrder(order);
                cakeInOrder.setCake(cake);
                return cakeInOrder;
            }

        } catch (SQLException ex) {
        }
        return null;    }

   public List<CakeInOrder> readSomeByOrderID(int orderID) {
        List<CakeInOrder> cakeInOrders = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[CakeInOrder]"
                    + "where orderID=\'"+orderID+"\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                CakeInOrder cakeInOrder = new CakeInOrder();
                cakeInOrder.setCioID(rs.getInt("cioID"));
                
                Cake cake = cDAO.findByID(rs.getInt("cakeID"));
                
                Order order = oDAO.findByID(rs.getInt("orderID"));
                cakeInOrder.setCioQuantity(rs.getInt("cioQuantity"));
                cakeInOrder.setOrder(order);
                cakeInOrder.setCake(cake);

                cakeInOrders.add(cakeInOrder);
            }

        } catch (SQLException ex) {
        }
        return cakeInOrders;
    }
       public List<CakeInOrder> showMoreByOrderID(String orderID,String quantity) {
        List<CakeInOrder> cakeInOrders = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[CakeInOrder]"
                    + "where orderID=\'"+orderID+"\'";
             sql += " ORDER BY cioID OFFSET " + quantity + " ROWS FETCH NEXT 4 ROWS ONLY;";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CakeInOrder cakeInOrder = new CakeInOrder();
                cakeInOrder.setCioID(rs.getInt("cioID"));
                
                Cake cake = cDAO.findByID(rs.getInt("cakeID"));
                
                Order order = oDAO.findByID(rs.getInt("orderID"));
                cakeInOrder.setCioQuantity(rs.getInt("cioQuantity"));
                cakeInOrder.setOrder(order);
                cakeInOrder.setCake(cake);

                cakeInOrders.add(cakeInOrder);
            }

        } catch (SQLException ex) {
        }
        return cakeInOrders;
    }
       
    public List<Cake> findCakesInOrder(int orderID) {
    List<Cake> cakesInOrder = new ArrayList<>();
    try {
        String sql = "SELECT cio.cioID, c.cakeID, c.cakeName, c.cakeDescription, c.cakePrice, c.cakeImg, c.cakeQuantity, c.cakeType "
                + "FROM [dbo].[CakeInOrder] cio "
                + "JOIN [dbo].[Cakes] c ON cio.cakeID = c.cakeID "
                + "WHERE cio.orderID = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Cake cake = new Cake();
                cake.setCakeID(rs.getInt("cakeID"));
                cake.setCakeName(rs.getString("cakeName"));
                cake.setCakeDescription(rs.getString("cakeDescription"));
                cake.setCakePrice(rs.getInt("cakePrice"));
                cake.setCakeImg(rs.getString("cakeImg"));
                cake.setCakeQuantity(rs.getInt("cakeQuantity"));
                cake.setCakeType(rs.getString("cakeType"));
                
                cakesInOrder.add(cake);
            }
        }
    } catch (SQLException ex) {
        // Handle the exception
    }
    return cakesInOrder;
}
}
