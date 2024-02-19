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
 * @author ACER PC
 */
public class UserDAO extends AbstractDAO<User>{

    @Override
    public List<User> readAll() {
List<User> users = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Customers]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setRole("Staff");

                users.add(user);
            }

        } catch (SQLException ex) {
        }
        return users;    }

    @Override
    public void create(User object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        UserDAO uDAO = new UserDAO();

        List<User> list = uDAO.readAll();
        for (User u : list) {
            System.out.println(u.toString());
        }
       
    }
    
}
