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
    public void create(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Staff object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        return null;    }

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
