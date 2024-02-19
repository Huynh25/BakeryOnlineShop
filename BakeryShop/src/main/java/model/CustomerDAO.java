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
public class CustomerDAO extends AbstractDAO<Customer> {

    @Override
    public List<Customer> readAll() {
        List<Customer> customers = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Customers]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setUserID(rs.getInt("userID"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setGoogleID(rs.getString("googleID"));
                customer.setAccessToken(rs.getString("accessToken"));
                customer.setUserAvatar(rs.getString("userAvatar"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));

                customers.add(customer);
            }

        } catch (SQLException ex) {
        }
        return customers;
    }

    @Override
    public void create(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Customer object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Customer findByID(int id) {
        try {

            String sql = "Select * from [dbo].[Customers]"
                    + "where userID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setUserID(rs.getInt("userID"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setGoogleID(rs.getString("googleID"));
                customer.setAccessToken(rs.getString("accessToken"));
                customer.setUserAvatar(rs.getString("userAvatar"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                return customer;
            }

        } catch (SQLException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        CustomerDAO cDAO = new CustomerDAO();

        List<Customer> list = cDAO.readAll();
        for (Customer c : list) {
            System.out.println(c.toString());
        }
        System.out.println("---------------");
        System.out.println(cDAO.findByID(1));

    }
}
