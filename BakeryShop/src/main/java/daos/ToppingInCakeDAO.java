/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import daos.ToppingDAO;
import daos.CakeInOrderDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.CakeInOrder;
import models.Topping;
import models.ToppingInCake;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class ToppingInCakeDAO extends AbstractDAO<ToppingInCake> {

    CakeInOrderDAO cDAO = new CakeInOrderDAO();
    ToppingDAO tDAO = new ToppingDAO();
    @Override
    public List<ToppingInCake> readAll() {
        List<ToppingInCake> toppingInCakes = new ArrayList<>();
        try {

            String sql = "Select * from [dbo].[ToppingInCake]";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ToppingInCake toppingInCake = new ToppingInCake();

                CakeInOrder cakeInOrder = cDAO.findByID(rs.getInt("cioID"));
                Topping topping = tDAO.findByID(rs.getInt("toppingID"));

                toppingInCake.setCakeInOrder(cakeInOrder);
                toppingInCake.setTopping(topping);
                
                toppingInCake.setTicQuantity(rs.getInt("ticQuantity"));

                toppingInCakes.add(toppingInCake);
            }

        } catch (SQLException ex) {
        }
        return toppingInCakes;
    }

    @Override
    public void create(ToppingInCake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ToppingInCake object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ToppingInCake findByID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public static void main(String[] args) {
        ToppingInCakeDAO tDAO = new ToppingInCakeDAO();

        List<ToppingInCake> list = tDAO.readAll();
        for (ToppingInCake t : list) {
            System.out.println(t.toString());
        }
    }

}
