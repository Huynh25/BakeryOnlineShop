/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.CustomerDAO;
import daos.CakeDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cake;
import models.Customer;
import models.Rating;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class RatingDAO extends AbstractDAO<Rating> {

    CustomerDAO cDAo = new CustomerDAO();
    CakeDAO cakeDAO = new CakeDAO();

    @Override
    public List<Rating> readAll() {
        List<Rating> ratings = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[Ratings]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Rating rating = new Rating();

                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Customer customer = cDAo.findByID(rs.getInt("userID"));

                rating.setUserID(customer);
                rating.setCake(cake);
                rating.setRatingDate(rs.getDate("ratingDate"));
                rating.setRatingValue(rs.getInt("ratingValue"));
                rating.setComment(rs.getString("comment"));

                ratings.add(rating);
            }

        } catch (SQLException ex) {
        }
        return ratings;
    }

    @Override
    public void create(Rating object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Rating object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rating findByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        RatingDAO cDAO = new RatingDAO();

        List<Rating> list = cDAO.readAll();
        for (Rating r : list) {
            System.out.println(r.toString());
        }
    }

}
