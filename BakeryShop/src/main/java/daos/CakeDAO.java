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

    public List<Cake> getAllCakeWithUniqueType() {

        List<Cake> cakes = new ArrayList<>();
        try {

            String sql = "SELECT  [cakeID]\n"
                    + "      ,[cakeName]\n"
                    + "      ,[cakeDescription]\n"
                    + "      ,[cakePrice]\n"
                    + "      ,[cakeImg]\n"
                    + "      ,[cakeQuantity]\n"
                    + "	  ,[cakeType]\n"
                    + "FROM    (SELECT *,\n"
                    + "                ROW_NUMBER() OVER (PARTITION BY [cakeType] ORDER BY [cakeID]) AS RowNumber\n"
                    + "         FROM  [BakeryShop].[dbo].[Cakes]\n"
                    + "        ) AS cte\n"
                    + "WHERE   cte.RowNumber = 1\n"
                    + "ORDER BY [cakeID]";
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

    public List<Cake> getAllCakeWithType(String cakeType) {
        List<Cake> cakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Cakes]"
                    + "where cakeType like '" + cakeType + "'";
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

    public List<Cake> searchCakes(String cakeFind) {
        List<Cake> cakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Cakes]"
                    + "where upper(cakeName) like upper('%" + cakeFind + "%')";
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

    public List<Cake> getBestSeller(int top) {
        List<Cake> cakes = new ArrayList<>();
        try {

            String sql = "SELECT top(" + top + ") c.cakeID, c.cakeName, c.cakeDescription, c.cakePrice, c.cakeImg, c.cakeQuantity, c.cakeType, COUNT(*) AS NumberOfOrder\n"
                    + "FROM Cakes c\n"
                    + "JOIN CakeInOrder cio ON c.cakeID = cio.cakeID\n"
                    + "JOIN Orders o ON cio.orderID = o.orderID\n"
                    + "GROUP BY c.cakeID, c.cakeName, c.cakeDescription, c.cakePrice, c.cakeImg, c.cakeQuantity, c.cakeType\n"
                    + "ORDER BY NumberOfOrder desc;";
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
    
    public List<Cake> searchAllInCakes(String searchTerm) {
    CakeDAO cakeDAO = new CakeDAO();

    List<Cake> cakeList = cakeDAO.readAll();
    List<Cake> searchResult = new ArrayList<>();

    for (Cake cake : cakeList) {
        String cakeID = cake.getCakeID() + "";
        String cakeName = cake.getCakeName();
        String cakePrice = cake.getCakePrice() + "";
        String cakeDescription = cake.getCakeDescription();

        if (cakeID.equalsIgnoreCase(searchTerm) || cakeName.contains(searchTerm) || cakePrice.equalsIgnoreCase(searchTerm) || cakeDescription.contains(searchTerm)) {
            searchResult.add(cake);
        }
    }
    return searchResult;
    }
    
     public List<String> getAllTypes() {
    List<String> types = new ArrayList<>();
    types.add(0, "All");
    try {
        String sql = "SELECT DISTINCT cakeType FROM [dbo].[Cakes]";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            types.add(rs.getString("cakeType"));
        }
    } catch (SQLException ex) {
        // Xử lý exception nếu cần
    }
    return types;
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
