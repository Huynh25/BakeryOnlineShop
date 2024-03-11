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
    public void create(Topping newTopping) {
    try {
        String sql = "INSERT INTO [dbo].[Toppings] (toppingName, toppingQuantity, toppingPrice, toppingImg, toppingDescription) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newTopping.getToppingName());
            preparedStatement.setInt(2, newTopping.getToppingQuantity());
            preparedStatement.setInt(3, newTopping.getToppingPrice());
            preparedStatement.setString(4, newTopping.getToppingImg());
            preparedStatement.setString(5, newTopping.getToppingDescription());

            // Execute the insert and retrieve the generated keys
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                // Get the generated keys (if any)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedID = generatedKeys.getInt(1);
                        newTopping.setToppingID(generatedID);
                        System.out.println("Topping with ID " + generatedID + " created successfully.");
                    } else {
                        System.out.println("Failed to retrieve generated ID for the new topping.");
                    }
                }
            } else {
                System.out.println("Failed to create the new topping.");
            }
        }
    } catch (SQLException ex) {
        // Handle exceptions as needed
        ex.printStackTrace();
    }
}

    @Override
    public void update(Topping topping) {
        try {
            String sql = "UPDATE [dbo].[Toppings] SET "
                    + "toppingName = ?, "
                    + "toppingQuantity = ?, "
                    + "toppingPrice = ?, "
                    + "toppingImg = ?, "
                    + "toppingDescription = ? "
                    + "WHERE toppingID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, topping.getToppingName());
            pstmt.setInt(2, topping.getToppingQuantity());
            pstmt.setInt(3, topping.getToppingPrice());
            pstmt.setString(4, topping.getToppingImg());
            pstmt.setString(5, topping.getToppingDescription());
            pstmt.setInt(6, topping.getToppingID());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // Handle exception (e.g., log or throw a custom exception)
            ex.printStackTrace();
        }
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

    public List<Topping> searchToppings(String searchTerm) {
        ToppingDAO toppingDAO = new ToppingDAO();

        List<Topping> toppingList = toppingDAO.readAll();
        List<Topping> searchResult = new ArrayList<>();

        for (Topping topping : toppingList) {
            String toppingID = topping.getToppingID() + "";
            String toppingName = topping.getToppingName();
            String toppingPrice = topping.getToppingPrice() + "";
            String toppingDescription = topping.getToppingDescription();

            if (toppingID.equalsIgnoreCase(searchTerm) || toppingName.contains(searchTerm) || toppingPrice.equalsIgnoreCase(searchTerm) || toppingDescription.contains(searchTerm)) {
                searchResult.add(topping);
            }
        }
        return searchResult;
    }

    public void updateQuantity(Topping topping) {
        String sql = "UPDATE Toppings SET toppingQuantity=? WHERE toppingID=?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, topping.getToppingQuantity());
            statement.setInt(2, topping.getToppingID());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
            }
        } catch (Exception e) {
        }

    }
    public Topping findByName(String name) {
        try {

            String sql = "Select * from [dbo].[Toppings]"
                    + "where toppingName =\'" + name + "\'";
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
