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

    @Override
    public void update(Staff s) {
        String sql = "UPDATE [dbo].[Staffs] SET [password] = \'" + s.getPassword() + "\'\n"
                + "      ,[fullname] = \'" + s.getFullname() + "\'\n"
                + "      ,[email] = \'" + s.getEmail() + "\'\n"
                + "      ,[address] = \'" + s.getAddress() + "\'\n"
                + "      ,[phoneNumber] = \'" + s.getPhoneNumber() + "\'\n"
                + "      ,[staffAvatar] = \'" + s.getStaffAvatar() + "\'\n"
                + " WHERE staffID =" + s.getStaffID();
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
}
