/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.CustomerDAO;
import daos.CakeDAO;
import java.sql.PreparedStatement;
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

            String sql = "Select * from [dbo].[Ratings] Order BY ratingDate DESC";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Rating rating = new Rating();

                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Customer customer = cDAo.findByID(rs.getInt("userID"));

                rating.setCustomer(customer);
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

    public Rating findByCakeUserID(int cakeID, int userID) {
        Rating rating = new Rating();
        try {

            String sql = "Select * from [dbo].[Ratings] where cakeID=" + cakeID + " AND userID=" + userID;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Cake cake = cakeDAO.findByID(rs.getInt("cakeID"));

                Customer customer = cDAo.findByID(rs.getInt("userID"));

                rating.setCustomer(customer);
                rating.setCake(cake);
                rating.setRatingDate(rs.getDate("ratingDate"));
                rating.setRatingValue(rs.getInt("ratingValue"));
                rating.setComment(rs.getString("comment"));

                return rating;
            }

        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public void create(Rating r) {
        String sql = "INSERT INTO [dbo].[Ratings]\n"
                + "           ([userID]\n"
                + "           ,[cakeID]\n"
                + "           ,[ratingDate]\n"
                + "           ,[ratingValue]\n"
                + "           ,[comment])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getCustomer().getUserID());
            ps.setInt(2, r.getCake().getCakeID());
            ps.setDate(3, r.getRatingDate());
            ps.setInt(4, r.getRatingValue());
            ps.setString(5, r.getComment());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rating r) {
        String sql = "UPDATE [dbo].[Ratings] SET "
                + "[userID] = '" + r.getCustomer().getUserID() + "', "
                + "[cakeID] = '" + r.getCake().getCakeID() + "', "
                + "[ratingDate] = '" + r.getRatingDate() + "', "
                + "[ratingValue] = '" + r.getRatingValue() + "', "
                + "[comment] = N\'" + r.getComment() + "\' "
                + "WHERE [userID] = '" + r.getCustomer().getUserID() + "' AND [cakeID] = '" + r.getCake().getCakeID() + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Rating findByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Rating> getAllRatingsByCakeID(int cakeID) {
        List<Rating> ratings = new ArrayList<>();

        try {

            String sql = "Select * from [dbo].[Ratings]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Cake cake = cakeDAO.findByID(cakeID);

            if (cake == null) {
                return ratings;
            }

            while (rs.next()) {
                Rating rating = new Rating();

                Customer customer = cDAo.findByID(rs.getInt("userID"));

                rating.setCustomer(customer);
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

    public static void main(String[] args) {
        RatingDAO cDAO = new RatingDAO();

        List<Rating> list = cDAO.readAll();
        for (Rating r : list) {
            System.out.println(r.toString());
        }
    }
    
    public boolean isExistRating(String cakeID, int id) {
        try {

            String sql = "Select * from [dbo].[Ratings] where cakeID=" + cakeID + " AND userID=" + id;
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
        }
        return false;
    }

}
