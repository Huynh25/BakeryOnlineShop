/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        CakeInOrderDAO cDAO = new CakeInOrderDAO();

        List<CakeInOrder> list = cDAO.readAll();
        for (CakeInOrder c : list) {
            System.out.println(c.toString());
        }
        System.out.println("--------------");
        System.out.println(cDAO.findByID(1).toString());

    }
}
