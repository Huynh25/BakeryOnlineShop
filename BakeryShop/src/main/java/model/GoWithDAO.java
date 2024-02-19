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
public class GoWithDAO extends AbstractDAO<GoWith> {

    CakeDAO cakeDAO = new CakeDAO();
    ToppingDAO tDAO = new ToppingDAO();

    @Override
    public List<GoWith> readAll() {
        List<GoWith> goWiths = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[GoWiths]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                GoWith goWith = new GoWith();

                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Topping topping = tDAO.findByID(rs.getInt("toppingID"));

                goWith.setCake(cake);
                goWith.setTopping(topping);

                goWiths.add(goWith);
            }

        } catch (SQLException ex) {
        }
        return goWiths;
    }

    @Override
    public void create(GoWith object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(GoWith object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GoWith findByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        GoWithDAO gDAO = new GoWithDAO();

        List<GoWith> list = gDAO.readAll();
        for (GoWith g : list) {
            System.out.println(g.toString());
        }
    }
}
