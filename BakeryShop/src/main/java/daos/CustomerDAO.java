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
import models.Customer;

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
    public void create(Customer c) {
        String sql = "INSER INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getUserID());
            ps.setString(2, c.getUsername());
            ps.setString(3, c.getPassword());
            ps.setString(4, c.getFullname());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getGoogleID());
            ps.setString(7, c.getAccessToken());
            ps.setString(8, c.getUserAvatar());
            ps.setString(9, c.getAddress());
            ps.setString(10, c.getPhoneNumber());
            ps.executeQuery();
        } catch (Exception e) {
        }
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

    public Customer findByPhone(String phoneNumber) {
        try {
            String sql = "SELECT * FROM [dbo].[Customers] WHERE phoneNumber = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
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
        System.out.println("++++++++++++++++");
        System.out.println(cDAO.findByPhone("0987654321"));
    }
}
