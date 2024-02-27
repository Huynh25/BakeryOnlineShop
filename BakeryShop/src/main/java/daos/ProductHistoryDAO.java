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
import models.ProductHistory;
import models.Staff;
import models.Topping;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class ProductHistoryDAO extends AbstractDAO<ProductHistory> {

    CakeDAO cakeDAO = new CakeDAO();
    ToppingDAO toppingDAO = new ToppingDAO();
    StaffDAO staffDAO = new StaffDAO();

    @Override
    public List<ProductHistory> readAll() {
        List<ProductHistory> productHistorys = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[ProductHistory]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ProductHistory productHistory = new ProductHistory();

                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Topping topping = toppingDAO.findByID(rs.getInt("toppingID"));

                Staff updatedBy = staffDAO.findByID(rs.getInt("updateBy"));

                Staff createBy = staffDAO.findByID(rs.getInt("createBy"));

                productHistory.setCake(cake);
                productHistory.setTopping(topping);
                productHistory.setCreateBy(createBy);
                productHistory.setUpdateBy(updatedBy);

                productHistory.setPhID(rs.getInt("phID"));
                productHistory.setPhQuantity(rs.getInt("phQuantity"));
                productHistory.setUpdatedDate(rs.getDate("updateDate"));
                productHistory.setCreateDate(rs.getDate("createDate"));

                productHistorys.add(productHistory);
            }

        } catch (SQLException ex) {
        }
        return productHistorys;
    }

    public int countNumberRow(String filter, String search, String date) {
        int count = 0;
        try {

            String sql = "SELECT ph.*,c.cakeName,c.cakeID,t.toppingName,t.toppingID,s1.staffID,s1.staffName,s2.staffID,s2.staffName\n"
                    + "FROM ProductHistory ph\n"
                    + "LEFT JOIN Cakes c ON ph.cakeID = c.cakeID\n"
                    + "LEFT JOIN Toppings t ON ph.toppingID = t.toppingID\n"
                    + "LEFT JOIN Staffs s1 ON ph.createBy = s1.staffID\n"
                    + "LEFT JOIN Staffs s2 ON ph.updateBy = s2.staffID\n"
                    + "where (c.cakeName Like N'%" + search + "%' OR c.cakeID Like '%" + search + "%' OR t.toppingName Like N'%" + search + "%' OR t.toppingID Like '%" + search + "%' OR s1.staffID like '%" + search + "%' OR s1.fullname like N'%" + search + "%' OR s2.staffID like'%" + search + "%' OR s2.fullname like N'%" + search + "%')";
            switch (filter) {
                case "cake": {
                    sql += " AND t.toppingID IS NULL ";
                    break;
                }
                case "topping": {
                    sql += " AND c.cakeID IS NULL ";
                    break;
                }
                case "all": {

                }
            }
             if(!date.equals("")){
                sql+=" AND (ph.updateDate Like '%"+date+"%' OR ph.createDate Like '%"+date+"%')";
            }
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                count++;
            }

        } catch (SQLException ex) {
        }
        return count;
    }

    public List<ProductHistory> readSome(int preNumberPage, String filter, String search,String date) {
        List<ProductHistory> productHistorys = new ArrayList<>();
        try {

            String sql = "SELECT ph.*,c.cakeName,c.cakeID,t.toppingName,t.toppingID,s1.staffID,s1.staffName,s2.staffID,s2.staffName\n"
                    + "FROM ProductHistory ph\n"
                    + "LEFT JOIN Cakes c ON ph.cakeID = c.cakeID\n"
                    + "LEFT JOIN Toppings t ON ph.toppingID = t.toppingID\n"
                    + "LEFT JOIN Staffs s1 ON ph.createBy = s1.staffID\n"
                    + "LEFT JOIN Staffs s2 ON ph.updateBy = s2.staffID\n"
                    + "where (c.cakeName Like N'%" + search + "%' OR c.cakeID Like '%" + search + "%' OR t.toppingName Like N'%" + search + "%' OR t.toppingID Like '%" + search + "%' OR s1.staffID like '%" + search + "%' OR s1.fullname like N'%" + search + "%' OR s2.staffID like'%" + search + "%' OR s2.fullname like N'%" + search + "%')";
            switch (filter) {
                case "cake": {
                    sql += " AND t.toppingID IS NULL ";
                    break;
                }
                case "topping": {
                    sql += " AND c.cakeID IS NULL ";
                    break;
                }
                case "all": {

                }
            }
            if(!date.equals("")){
                sql+=" AND (ph.updateDate Like '%"+date+"%' OR ph.createDate Like '%"+date+"%')";
            }
            sql += "ORDER BY CASE\n"
                    + "        WHEN updateDate IS NULL THEN createDate\n"
                    + "        ELSE updateDate\n"
                    + "    END  DESC OFFSET " + (preNumberPage -1)*8 + " ROWS FETCH NEXT 8 ROWS ONLY";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ProductHistory productHistory = new ProductHistory();

                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Topping topping = toppingDAO.findByID(rs.getInt("toppingID"));

                Staff updatedBy = staffDAO.findByID(rs.getInt("updateBy"));

                Staff createBy = staffDAO.findByID(rs.getInt("createBy"));

                productHistory.setCake(cake);
                productHistory.setTopping(topping);
                productHistory.setCreateBy(createBy);
                productHistory.setUpdateBy(updatedBy);

                productHistory.setPhID(rs.getInt("phID"));
                productHistory.setPhQuantity(rs.getInt("phQuantity"));
                productHistory.setUpdatedDate(rs.getDate("updateDate"));
                productHistory.setCreateDate(rs.getDate("createDate"));

                productHistorys.add(productHistory);
            }

        } catch (SQLException ex) {
        }
        return productHistorys;
    }

    @Override
    public void create(ProductHistory object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ProductHistory object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductHistory findByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        ProductHistoryDAO pDAO = new ProductHistoryDAO();

        List<ProductHistory> list = pDAO.readAll();
        for (ProductHistory p : list) {
            System.out.println(p.toString());
        }
    }
}
