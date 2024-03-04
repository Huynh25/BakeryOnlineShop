/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.ToppingDAO;
import daos.CakeInOrderDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.CakeInOrder;
import models.Order;
import models.Topping;
import models.ToppingInCake;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class ToppingInCakeDAO extends AbstractDAO<ToppingInCake> {

    CakeInOrderDAO cDAO = new CakeInOrderDAO();
    ToppingDAO tDAO = new ToppingDAO();
    @Override
    public List<ToppingInCake> readAll() {
        List<ToppingInCake> toppingInCakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[ToppingInCake]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ToppingInCake toppingInCake = new ToppingInCake();

                CakeInOrder cakeInOrder = cDAO.findByID(rs.getInt("cioID"));
                Topping topping = tDAO.findByID(rs.getInt("toppingID"));

                toppingInCake.setCakeInOrder(cakeInOrder);
                toppingInCake.setTopping(topping);
                
                toppingInCake.setTicQuantity(rs.getInt("ticQuantity"));

                toppingInCakes.add(toppingInCake);
            }

        } catch (SQLException ex) {
        }
        return toppingInCakes;
    }

    @Override
    public void create(ToppingInCake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ToppingInCake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ToppingInCake findByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
        public List<ToppingInCake> findAllByCioID(String cioID) {
        List<ToppingInCake> toppingInCakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[ToppingInCake] Where cioID="+cioID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ToppingInCake toppingInCake = new ToppingInCake();

                CakeInOrder cakeInOrder = cDAO.findByID(rs.getInt("cioID"));
                Topping topping = tDAO.findByID(rs.getInt("toppingID"));

                toppingInCake.setCakeInOrder(cakeInOrder);
                toppingInCake.setTopping(topping);
                
                toppingInCake.setTicQuantity(rs.getInt("ticQuantity"));

                toppingInCakes.add(toppingInCake);
            }

        } catch (SQLException ex) {
        }
        return toppingInCakes;
    }
        
        public List<ToppingInCake> getToppingsInCakesWithOrder(int orderID) {
    List<ToppingInCake> toppingsInCakes = new ArrayList<>();
    try {
        String sql = "SELECT tic.toppingID, t.toppingName, t.toppingQuantity, t.toppingPrice, t.toppingImg, t.toppingDescription, "
                + "ci.cakeID, ci.cakeName, ci.cakeDescription, ci.cakePrice, ci.cakeImg, ci.cakeQuantity, ci.cakeType, "
                + "o.orderID, o.orderDate, o.status, o.wasPaid, o.totalPrice, tic.ticQuantity, cio.cioQuantity "
                + "FROM [dbo].[ToppingInCake] tic "
                + "JOIN [dbo].[Toppings] t ON tic.toppingID = t.toppingID "
                + "JOIN [dbo].[CakeInOrder] cio ON tic.cioID = cio.cioID "
                + "JOIN [dbo].[Cakes] ci ON cio.cakeID = ci.cakeID "
                + "JOIN [dbo].[Orders] o ON cio.orderID = o.orderID "
                + "WHERE o.orderID = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ToppingInCake toppingInCake = new ToppingInCake();
                
                Topping topping = new Topping();
                topping.setToppingID(rs.getInt("toppingID"));
                topping.setToppingName(rs.getString("toppingName"));
                topping.setToppingQuantity(rs.getInt("toppingQuantity"));
                topping.setToppingPrice(rs.getInt("toppingPrice"));
                topping.setToppingImg(rs.getString("toppingImg"));
                topping.setToppingDescription(rs.getString("toppingDescription"));
                
                Cake cake = new Cake();
                cake.setCakeID(rs.getInt("cakeID"));
                cake.setCakeName(rs.getString("cakeName"));
                cake.setCakeDescription(rs.getString("cakeDescription"));
                cake.setCakePrice(rs.getInt("cakePrice"));
                cake.setCakeImg(rs.getString("cakeImg"));
                cake.setCakeQuantity(rs.getInt("cakeQuantity"));
                cake.setCakeType(rs.getString("cakeType"));
                
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setStatus(rs.getString("status"));
                order.setWasPaid(rs.getBoolean("wasPaid"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                
                CakeInOrder cakeInOrder = new CakeInOrder();
                
                toppingInCake.setTicQuantity(rs.getInt("ticQuantity"));
                // Set the retrieved objects to ToppingInCake
                toppingInCake.setTopping(topping);
                cakeInOrder.setCake(cake);
                cakeInOrder.setOrder(order);
                cakeInOrder.setCioQuantity(rs.getInt("cioQuantity"));
                toppingInCake.setCakeInOrder(cakeInOrder);
                
                toppingsInCakes.add(toppingInCake);
            }
        }
    } catch (SQLException ex) {
        // Handle the exception
    }
    return toppingsInCakes;
}


}
