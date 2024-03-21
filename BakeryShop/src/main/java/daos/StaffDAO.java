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
import models.Staff;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class StaffDAO extends AbstractDAO<Staff> {
    
    @Override
    public List<Staff> readAll() {
        List<Staff> staffs = new ArrayList<>();
        try {
            
            String sql = "Select * from [dbo].[Staffs]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setManagerID(rs.getInt("managerID"));
                staffs.add(staff);
            }
            
        } catch (SQLException ex) {
        }
        return staffs;
    }
    
    @Override
    public void create(Staff s) {
        String sql = "INSERT INTO [dbo].[Staffs]\n"
                + "           ([staffName]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[staffAvatar]\n"
                + "           ,[managerID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,(Select TOP 1 managerID from Staffs))\n";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getStaffName());
            ps.setString(2, "MD5(" + s.getPassword() + ")");
            ps.setString(3, s.getFullname());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getAddress());
            ps.setString(6, s.getPhoneNumber());
            ps.setString(7, s.getStaffAvatar());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Staff findByUsernameAndEmail(String staffName, String email) {
        Staff staff = new Staff();
        try {
            
            String sql = "Select * from [dbo].[Staffs]"
                    + "where staffName =\'" + staffName + "\' AND email=\'" + email + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setManagerID(rs.getInt("managerID"));
                return staff;
            }
            
        } catch (SQLException ex) {
        }
        return null;
    }
    
    @Override
    public void update(Staff s) {
        String sql = "UPDATE [dbo].[Staffs] SET \n"
                + "      [fullname] = \'" + s.getFullname() + "\'\n"
                + "      ,[email] = \'" + s.getEmail() + "\'\n"
                + "      ,[address] = \'" + s.getAddress() + "\'\n"
                + "      ,[phoneNumber] = \'" + s.getPhoneNumber() + "\'\n"
                + "      ,[staffAvatar] = \'" + s.getStaffAvatar() + "\'\n";
        
        if (!s.getPassword().equals("@PWNT*****")) {
            sql += ",[password] = CONVERT(VARCHAR(32),HASHBYTES('MD5',\'" + s.getPassword() + "\'), 2)\n";
        }
        sql += " WHERE staffID =" + s.getStaffID();
        System.out.println(sql);
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Staff findByID(int id) {
        try {
            
            String sql = "Select * from [dbo].[Staffs]"
                    + "where staffID =\'" + id + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                return staff;
            }
            
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public Staff findByFullname(String name) {
        try {
            
            String sql = "Select * from [dbo].[Staffs]"
                    + "where staffName =\'" + name + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                return staff;
            }
            
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        StaffDAO cDAO = new StaffDAO();
        
        List<Staff> list = cDAO.readAll();
        for (Staff s : list) {
            System.out.println(s.toString());
        }
        System.out.println("---------------");
        System.out.println(cDAO.findByID(1).toString());
        
    }
    
    public void updatePassworD(String password1, int id) {
        try {
            String sql = "UPDATE Staffs\n"
                    + "SET [password] = CONVERT(VARCHAR(32),HASHBYTES('MD5',\'" + password1 + "\'), 2)\n"
                    + "WHERE staffID = " + id;
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Staff readByEmail(String email) {
        Staff staff = new Staff();
        try {
            
            String readSQL = "Select * from Staffs where email = \'" + email + "\'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(readSQL);
            System.out.println("sql" + readSQL);
            if (rs.next()) {
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setManagerID(rs.getInt("managerID"));
                return staff;
            }
            
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public Staff findByPhone(String phoneNumber) {
        try {
            String sql = "SELECT * FROM [dbo].[Staffs] WHERE phoneNumber = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("staffID"));
                staff.setStaffName(rs.getString("staffName"));
                staff.setPassword(rs.getString("password"));
                staff.setFullname(rs.getString("fullname"));
                staff.setEmail(rs.getString("email"));
                staff.setAddress(rs.getString("address"));
                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setStaffAvatar(rs.getString("staffAvatar"));
                staff.setManagerID(rs.getInt("managerID"));
                return staff;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    
    public boolean updateProfile(Staff s) {
        try {
            String sql = "UPDATE Staffs\n"
                    + "SET staffAvatar=?, staffName =?, fullname =?, email =?, address =?, phoneNumber=?\n"
                    + "WHERE staffID = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println(s);
              ps.setString(1, s.getStaffAvatar());
              ps.setString(2, s.getStaffName());
              ps.setString(3, s.getFullname());
              ps.setString(4, s.getEmail());
              ps.setString(5, s.getAddress());
              ps.setString(6, s.getPhoneNumber());
              ps.setInt(7, s.getStaffID());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean verifyPassword(int staffID, String currentPassword) {
        try {
            String hashedPassword = hashPass(currentPassword);
            String sql = "SELECT * FROM Staffs WHERE staffID = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffID);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu tìm thấy mật khẩu, ngược lại trả về false
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
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
    
        
        public boolean changePassword(int staffID, String newPassword) {
        try {
            String hashedPassword = hashPass(newPassword);
            String sql = "UPDATE Staffs SET password = ? WHERE staffID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setInt(2, staffID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
