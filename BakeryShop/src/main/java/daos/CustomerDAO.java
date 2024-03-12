/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String hashedPassword = hashPass(c.getPassword());
        c.setPassword(hashedPassword);
        String sql = "INSERT INTO Customers (username, password, fullname, email, googleID, accessToken, userAvatar, address, phoneNumber) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getFullname());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getGoogleID());
            ps.setString(6, c.getAccessToken());
            ps.setString(7, c.getUserAvatar());
            ps.setString(8, c.getAddress());
            ps.setString(9, c.getPhoneNumber());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int generatedUserID = rs.getInt(1);
                    c.setUserID(generatedUserID);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * hash password by MD5
     *
     * @param msg - guideline
     * @param op - option to get password: 0(check valid) and 1(not check valid)
     * @return pass after hash
     */
    private String hashPass(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] MD = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, MD);
            String hashPass = number.toString(16);
            while (hashPass.length() < 32) {
                hashPass = "0" + hashPass;
            }
            return hashPass;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Customer c) {
        try {
            String sql = "UPDATE Customers\n"
                    + "SET userAvatar=?, fullname =?, email =?, address =?, phoneNumber=?\n"
                    + "WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getFullname());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getPhoneNumber());
            ps.setInt(7, c.getUserID());
            ps.executeUpdate();
        } catch (Exception e) {
        }
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

    public Customer findByUsername(String username) {
        try {

            String sql = "Select * from [dbo].[Customers]"
                    + "where username =\'" + username + "\'";
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

    public boolean updateProfile(Customer c) {
        try {
            String sql = "UPDATE Customers\n"
                    + "SET userAvatar=?, fullname =?, email =?, address =?, phoneNumber=?\n"
                    + "WHERE userID = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getUserAvatar());
            ps.setString(2, c.getFullname());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getPhoneNumber());
            ps.setInt(6, c.getUserID());
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
        System.out.println("+_____________________+");
        System.out.println(cDAO.findByUsername("voldemort"));

        Customer newCustomer = new Customer();
        newCustomer.setUsername("newuser");
        newCustomer.setPassword("newpassword");
        newCustomer.setFullname("New User");
        newCustomer.setEmail("newuser@example.com");
        newCustomer.setGoogleID("newgoogleid");
        newCustomer.setAccessToken("newaccesstoken");
        newCustomer.setUserAvatar("avatar.jpg");
        newCustomer.setAddress("New Address");
        newCustomer.setPhoneNumber("0123456789");

        // Thử tạo mới khách hàng bằng cách gọi phương thức create
        cDAO.create(newCustomer);

        // In ra thông tin của khách hàng sau khi tạo mới
        System.out.println("New customer created:");
        System.out.println(newCustomer);

        int userIDToUpdate = 1; // ID của khách hàng cần cập nhật
        Customer customerToUpdate = cDAO.findByID(userIDToUpdate);
        System.out.println("Customer to update:");
        System.out.println(customerToUpdate);
        // Cập nhật thông tin của khách hàng
        if (customerToUpdate != null) {
            // Cập nhật các thông tin cần thay đổi
            customerToUpdate.setFullname("Updated Full Name");
            customerToUpdate.setEmail("updatedemail@example.com");
            customerToUpdate.setAddress("Updated Address");
            customerToUpdate.setPhoneNumber("0123456789");
            // Gọi phương thức update trong CustomerDAO để cập nhật thông tin
            boolean isUpdated = cDAO.updateProfile(customerToUpdate);
            // Kiểm tra kết quả cập nhật
            if (isUpdated) {
                System.out.println("Customer information updated successfully!");
                // In ra thông tin của khách hàng sau khi cập nhật
                System.out.println("Updated customer:");
                System.out.println(customerToUpdate);
            } else {
                System.out.println("Failed to update customer information.");
            }
        } else {
            System.out.println("Customer not found. Unable to update.");
        }
    }
}
