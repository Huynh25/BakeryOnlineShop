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
public class ProductHistoryDAO extends AbstractDAO<ProductHistory>{
 
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
        return productHistorys;    }

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
