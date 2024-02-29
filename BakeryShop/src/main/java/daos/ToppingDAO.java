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
import models.Topping;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class ToppingDAO extends AbstractDAO<Topping> {

    @Override
    public List<Topping> readAll() {
        List<Topping> toppings = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Toppings]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Topping topping = new Topping();
                topping.setToppingID(rs.getInt("toppingID"));
                topping.setToppingName(rs.getString("toppingName"));
                topping.setToppingDescription(rs.getString("toppingDescription"));
                topping.setToppingPrice(rs.getInt("toppingPrice"));
                topping.setToppingImg(rs.getString("toppingImg"));
                topping.setToppingQuantity(rs.getInt("toppingQuantity"));
                toppings.add(topping);
            }

        } catch (SQLException ex) {
        }
        return toppings;
    }

    @Override
    public void create(Topping object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Topping object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Topping findByID(int id) {
        try {

            String sql = "Select * from [dbo].[Toppings]"
                    + "where toppingID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                Topping topping = new Topping();
                topping.setToppingID(rs.getInt("toppingID"));
                topping.setToppingName(rs.getString("toppingName"));
                topping.setToppingDescription(rs.getString("toppingDescription"));
                topping.setToppingPrice(rs.getInt("toppingPrice"));
                topping.setToppingImg(rs.getString("toppingImg"));
                topping.setToppingQuantity(rs.getInt("toppingQuantity"));

                return topping;
            }

        } catch (SQLException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        ToppingDAO tDAO = new ToppingDAO();

        List<Topping> list = tDAO.readAll();
        for (Topping t : list) {
            System.out.println(t.toString());
        }
        System.out.println("-----------");
        System.out.println(tDAO.findByID(1).toString());
    }
}
