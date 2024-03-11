/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.sql.PreparedStatement;
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
public class OrderDAO extends AbstractDAO<Order> {

    @Override
    public List<Order> readAll() {
        List<Order> orders = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Orders] ORDER BY orderDate DESC";
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
        return orders;
    }

    @Override
    public void create(Order object) {
        String sql = "INSERT INTO Orders ([userID], [orderDescription], [totalPrice], [orderDate], [wasPaid], [status]) "
                + "OUTPUT Inserted.[orderID]"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, object.getUserID());
            ps.setString(2, object.getOrderDescription());
            ps.setInt(3, object.getTotalPrice());

            ps.setDate(4, object.getOrderDate());

            ps.setBoolean(5, object.isWasPaid());
            ps.setString(6, object.getStatus());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                object.setOrderID(rs.getInt("orderID"));
            }

        } catch (Exception e) {
        }
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

    public List<Order> searchOrders(String searchTerm) {
        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderList = orderDAO.readAll();
        List<Order> searchResult = new ArrayList<>();

        for (Order order : orderList) {
            String orderID = order.getOrderID() + "";
            String orderDate = order.getOrderDate() + "";
            String orderPrice = order.getTotalPrice() + "";
            if (orderID.equalsIgnoreCase(searchTerm)) {
                searchResult.add(order);
            } else if (orderDate.contains(searchTerm)) {
                searchResult.add(order);
            } else if (orderPrice.equalsIgnoreCase(searchTerm)) {
                searchResult.add(order);
            }
        }

        return searchResult;
    }

    public List<Order> readSomeByID(String numberOrder, int userID) {
        List<Order> orders = new ArrayList<>();
        try {

            String sql = "SELECT *\n"
                    + "FROM [dbo].[Orders]\n"
                    + "Where userID=\'" + userID + "\'"
                    + "ORDER BY orderDate DESC\n"
                    + "OFFSET " + numberOrder + " ROWS FETCH NEXT 8 ROWS ONLY;";
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
        return orders;
    }

    public List<Order> filterSomeByID(String numberOrder, int userID, String filter, String search, String orderDate, String receivedDate) {

        List<Order> orders = new ArrayList<>();
        try {
            String sql = "select DISTINCT o.* from Orders o join CakeInOrder cio on cio.orderID=o.orderID join Cakes c on cio.cakeID=c.cakeID\n"
                    + "where userID=\'" + userID + "\' AND (o.orderID Like \'%" + search + "%\' OR o.staffID Like \'%" + search + "%\' OR o.orderDescription Like N\'%" + search + "%' OR c.cakeName Like N\'%" + search + "%\')";
            switch (filter) {
                case "wasPaid": {
                    sql += "AND wasPaid=1";
                    break;
                }
                case "waiting": {
                    sql += "AND status=\'Waiting\'";
                    break;
                }
                case "accepted": {
                    sql += "AND status=\'Delivering\'";
                    break;
                }
                case "completed": {
                    sql += "AND status=\'Done\'";
                    break;
                }
                case "all": {
                    break;
                }
                default: {

                }
            }
            if (!orderDate.equals("")) {
                sql += "AND orderDate=\'" + orderDate + "\'";
            }
            if (!receivedDate.equals("")) {
                sql += "AND receivedDate=\'" + receivedDate + "\'";
            }
            sql += " ORDER BY orderDate DESC OFFSET " + numberOrder + " ROWS FETCH NEXT 8 ROWS ONLY;";
            System.out.println(sql);
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
        return orders;
    }

    public int numberByUserID(int id) {
        int number = 0;
        try {
            String sql = "Select * from [dbo].[Orders]"
                    + "where userID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                number++;
            }

        } catch (SQLException ex) {
        }
        return number;
    }

    public int numberFilterByUserID(int id, String filter, String search, String orderDate, String receivedDate) {
        int number = 0;
        try {
            String sql = "select DISTINCT o.* from Orders o join CakeInOrder cio on cio.orderID=o.orderID join Cakes c on cio.cakeID=c.cakeID\n"
                    + "where userID=\'" + id + "\' AND (o.orderID Like \'%" + search + "%\' OR o.staffID Like \'%" + search + "%\' OR o.orderDescription Like N\'%" + search + "%' OR c.cakeName Like N\'%" + search + "%\')";
            switch (filter) {
                case "wasPaid": {
                    sql += "AND wasPaid=1";
                    break;
                }
                case "waiting": {
                    sql += "AND status=\'Waiting\'";
                    break;
                }
                case "accepted": {
                    sql += "AND status=\'Delivering\'";
                    break;
                }
                case "completed": {
                    sql += "AND status=\'Done\'";
                    break;
                }
                case "all": {
                    break;
                }
                default: {

                }
            }
            if (!orderDate.equals("")) {
                sql += "AND orderDate=\'" + orderDate + "\'";
            }
            if (!receivedDate.equals("")) {
                sql += "AND receivedDate=\'" + receivedDate + "\'";
            }

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                number++;
            }

        } catch (SQLException ex) {
        }
        return number;
    }

    public void acceptOrder(int orderID, int staffID) {
        try {
            String sql = "UPDATE [dbo].[Orders] SET status = 'Delivering', staffID = " + staffID + " WHERE orderID = " + orderID;

            Statement stm = con.createStatement();
            int rowsAffected = stm.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Đơn hàng đã được chấp nhận và đang được giao hàng.");
            } else {
                System.out.println("Không tìm thấy đơn hàng hoặc có lỗi xảy ra khi cập nhật thông tin.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
