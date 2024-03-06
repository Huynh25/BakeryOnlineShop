/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package testDAOs;

import daos.OrderDAO;
import java.sql.SQLException;
import java.util.List;
import models.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class OrderDAOReadSomeByIDTest {

    private static OrderDAO orderDAO;

    public OrderDAOReadSomeByIDTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        orderDAO = new OrderDAO();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //UTCID01
    @Test
    public void testReadSomeByIDGivenValidInputReturnsNullResults() throws SQLException {
        int userID = 1;
        String numberOrder = "0";
        List<Order> orders = orderDAO.readSomeByID(numberOrder, userID);
        
        assertEquals(8, orders.size());
        
        assertEquals(29, orders.get(0).getOrderID());
        assertEquals(1, orders.get(0).getUserID());
        assertEquals("Bánh kỷ niệm cho sự kiện kỷ niệm 5 năm kết hôn.", orders.get(0).getOrderDescription());
        
        
        assertEquals(13, orders.get(7).getOrderID());
        assertEquals(1, orders.get(7).getUserID());
        assertEquals("Bánh sinh nhật cho bữa tiệc sinh nhật lần thứ 50 của Jane.", orders.get(7).getOrderDescription());
    }
    
    //UTCID02
    @Test
    public void testReadSomeByIDGivenValidInputReturnsWellResults() throws SQLException {
        int userID = 1;
        String numberOrder = "2";
        List<Order> orders = orderDAO.readSomeByID(numberOrder, userID);
        assertEquals(orders.size(), 8);
    }

    //UTCID03
    @Test
    public void testReadSomeByIDGivenInvalidNumberOrderReturnsException() throws SQLException {
        String invalidNumberOrder = "-1";
        int userID = 1;
        try {
            List<Order> orders = orderDAO.readSomeByID(invalidNumberOrder, userID);
        } catch (Exception e) {
            if (e.getMessage().equals("Number order must be non-negative")) {
                Assert.assertEquals("Number order must be non-negative", e.getMessage());
            }
        }
    }

    //UTCID04
    @Test
    public void testReadSomeByIDGivenInvalidOrderIDReturnsException() throws SQLException {
        String numberOrder = "1"; 
        int invalidOrderID = -1;
        try {
            List<Order> orders = orderDAO.readSomeByID(numberOrder, invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("ID must be non-negative")) {
                Assert.assertEquals("ID must be non-negative", e.getMessage());
            }
        }
    }
    
    //UTCID05
    @Test
    public void testReadSomeByIDGivenInvalidNumberOrderReturnsException2() throws SQLException {
        String numberOrder = "a"; 
        int invalidOrderID = 1;
        try {
            List<Order> orders = orderDAO.readSomeByID(numberOrder, invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("Number order must be a number")) {
                Assert.assertEquals("Number order must be a number", e.getMessage());
            }
        }
    }
    
    //UTCID06
    @Test
    public void testReadSomeByIDGivenInvalidOrderIDReturnsException2() throws SQLException {
        String numberOrder = "1"; 
        
        try {
            int invalidOrderID = Integer.parseInt("a");
            List<Order> orders = orderDAO.readSomeByID(numberOrder, invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("ID must be a number")) {
                Assert.assertEquals("ID must be a number", e.getMessage());
            }
        }
    }
    
    //UTCID07
    @Test
    public void testReadSomeByIDGivenValidInputReturnsNullResults2() throws SQLException {
        int userID = 1000;
        String numberOrder = "1000";
        List<Order> orders = orderDAO.readSomeByID(numberOrder, userID);
        assertEquals(orders.size(), 0);
    }
    
    //UTCID07
    @Test
    public void testReadSomeByIDGivenValidInputReturnsWell() throws SQLException {
        int userID = 1;
        String numberOrder = "1";
        List<Order> orders = orderDAO.readSomeByID(numberOrder, userID);
        assertEquals(8, orders.size());
        
        assertEquals(24, orders.get(0).getOrderID());
        assertEquals(1, orders.get(0).getUserID());
        assertEquals("Bánh đặc biệt cho buổi tiệc cuối năm của công ty.", orders.get(0).getOrderDescription());
        
        assertEquals(23, orders.get(1).getOrderID());
        assertEquals(1, orders.get(1).getUserID());
        assertEquals("Bánh tùy chỉnh cho sự kiện kỷ niệm hàng năm của công ty", orders.get(1).getOrderDescription());
        
        assertEquals(20, orders.get(2).getOrderID());
        assertEquals(1, orders.get(2).getUserID());
        assertEquals("Bánh cưới với hoa trang trí cho đám cưới của Smith-Jones", orders.get(2).getOrderDescription());
        
        assertEquals(19, orders.get(3).getOrderID());
        assertEquals(1, orders.get(3).getUserID());
        assertEquals("Bánh chia tay cho đồng nghiệp chuyển công tác.", orders.get(3).getOrderDescription());
        
        assertEquals(11, orders.get(7).getOrderID());
        assertEquals(1, orders.get(7).getUserID());
        assertEquals("Bánh trà cho buổi gặp mặt cựu đồng nghiệp.", orders.get(7).getOrderDescription());
        
    }
}
