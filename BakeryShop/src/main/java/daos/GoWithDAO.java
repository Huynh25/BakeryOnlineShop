/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.CakeDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.GoWith;
import models.Topping;

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

    public List<GoWith> findAllByCakeID(int cakeID) {
        List<GoWith> goWiths = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[GoWiths]\n"
                    + "where cakeID = " + cakeID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Cake cake = cakeDAO.findByID(cakeID);
            
            if (cake == null) {
                return goWiths;
            }

            while (rs.next()) {
                GoWith goWith = new GoWith();

                Topping topping = tDAO.findByID(rs.getInt("toppingID"));

                goWith.setCake(cake);
                goWith.setTopping(topping);

                goWiths.add(goWith);
            }

        } catch (SQLException ex) {
        }
        return goWiths;
    }
    public void updateGoWith(int cakeID, List<Topping> toppingList) {
        try {
            // Xóa tất cả goWith hiện tại của cakeID
            String deleteSql = "DELETE FROM [dbo].[GoWiths] WHERE cakeID = " + cakeID;
            Statement deleteStatement = con.createStatement();
            deleteStatement.executeUpdate(deleteSql);

            // Thêm mới goWiths dựa trên danh sách Topping mới
            for (Topping topping : toppingList) {
                // Kiểm tra nếu topping có tồn tại trong danh sách topping
                if (tDAO.findByID(topping.getToppingID()) != null) {
                    // Thêm goWith mới vào CSDL
                    String insertSql = "INSERT INTO [dbo].[GoWiths] (cakeID, toppingID) VALUES (" + cakeID + ", " + topping.getToppingID() + ")";
                    Statement insertStatement = con.createStatement();
                    insertStatement.executeUpdate(insertSql);
                }
            }
        } catch (SQLException ex) {
            // Xử lý exception nếu có
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GoWithDAO gDAO = new GoWithDAO();

        List<GoWith> list = gDAO.readAll();
        for (GoWith g : list) {
            System.out.println(g.toString());
        }
    }
}
