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
import models.Cake;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class CakeDAO extends AbstractDAO<Cake> {

    @Override
    public List<Cake> readAll() {
        List<Cake> cakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Cakes]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Cake cake = new Cake();
                cake.setCakeID(rs.getInt("cakeID"));
                cake.setCakeName(rs.getString("cakeName"));
                cake.setCakeDescription(rs.getString("cakeDescription"));
                cake.setCakePrice(rs.getInt("cakePrice"));
                cake.setCakeImg(rs.getString("cakeImg"));
                cake.setCakeQuantity(rs.getInt("cakeQuantity"));
                cake.setCakeType(rs.getString("cakeType"));

                cakes.add(cake);
            }

        } catch (SQLException ex) {
        }
        return cakes;
    }

    @Override
    public void create(Cake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Cake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cake findByID(int id) {
        try {

            String sql = "Select * from [dbo].[Cakes]"
                    + "where cakeID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                Cake cake = new Cake();
                cake.setCakeID(rs.getInt("cakeID"));
                cake.setCakeName(rs.getString("cakeName"));
                cake.setCakeDescription(rs.getString("cakeDescription"));
                cake.setCakePrice(rs.getInt("cakePrice"));
                cake.setCakeImg(rs.getString("cakeImg"));
                cake.setCakeQuantity(rs.getInt("cakeQuantity"));
                cake.setCakeType(rs.getString("cakeType"));
                return cake;
            }

        } catch (SQLException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        CakeDAO cDAO = new CakeDAO();

        List<Cake> list = cDAO.readAll();
        for (Cake c : list) {
            System.out.println(c.toString());
        }
        System.out.println("------------------");
        System.out.println(cDAO.findByID(01));
    }
}
