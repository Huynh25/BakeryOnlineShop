/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Order;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class OrderDAO extends AbstractDAO<Order>{

    @Override
    public List<Order> readAll() {
      List<Order> orders = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Orders]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setUserID(rs.getInt("userID"));
                order.setStaffID(rs.getInt("staffID"));
                order.setOrderDescription(rs.getString("orderDescription"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setReceivedDate(rs.getDate("receivedDate"));
                order.setWasPaid(rs.getBoolean("wasPaid"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }

        } catch (SQLException ex) {
        }
        return orders;    }

    @Override
    public void create(Order object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Order object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Order findByID(int id) {
                try {

            String sql = "Select * from [dbo].[Orders]"
                    + "where orderID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                 Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setUserID(rs.getInt("userID"));
                order.setStaffID(rs.getInt("staffID"));
                order.setOrderDescription(rs.getString("orderDescription"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setReceivedDate(rs.getDate("receivedDate"));
                order.setWasPaid(rs.getBoolean("wasPaid"));
                order.setStatus(rs.getString("status"));
                
                return order;
            }

        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        OrderDAO oDAO = new OrderDAO();

        List<Order> list = oDAO.readAll();
        for (Order o : list) {
            System.out.println(o.toString());
        }
        
        System.out.println("------------");
        
        System.out.println(oDAO.findByID(01).toString());

    }
}
