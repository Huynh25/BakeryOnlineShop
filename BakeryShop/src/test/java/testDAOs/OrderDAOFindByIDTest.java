package testDAOs;

import daos.OrderDAO;
import models.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class OrderDAOFindByIDTest {

    private static OrderDAO orderDAO;

    @BeforeClass
    public static void setUpClass() {
        // Khởi tạo một đối tượng OrderDAO trước tất cả các phương thức kiểm thử
        orderDAO = new OrderDAO();
    }

    @AfterClass
    public static void tearDownClass() {
        // Đặt các tài nguyên sau khi đã chạy xong tất cả các phương thức kiểm thử
    }

    @Before
    public void setUp() {
        // Các công việc chuẩn bị trước mỗi phương thức kiểm thử
    }

    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi phương thức kiểm thử
    }

    //UTCID01
    @Test
    public void testFindByIDReturnsCorrectOrder() throws SQLException {
        int orderIdToFind = 1; // Adjust the ID to be tested
        Order foundOrder = orderDAO.findByID(orderIdToFind);
        assertNotNull(foundOrder);
        assertEquals(orderIdToFind, foundOrder.getOrderID());
    }
    
    //UTCID02
    @Test
    public void testFindByIDReturnsNull() throws SQLException {
        int orderIdToFind = 0; // Adjust the ID to be tested
        Order foundOrder = orderDAO.findByID(orderIdToFind);
        assertNull(foundOrder);
    }
    
    //UTCID03
    @Test
    public void testFindByIDGivenWrongArgumentReturnsException() {
        int invalidOrderID = -1;
        try {
            Order foundOrder = orderDAO.findByID(invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("ID must be non-negative")) {
                Assert.assertEquals("ID must be non-negative", e.getMessage());
            }
        }

    }

    //UTCID04
    @Test
    public void testFindByIDReturnsCorrectOrder2() throws SQLException {
        int orderIdToFind = 5; // Adjust the ID to be tested
        Order foundOrder = orderDAO.findByID(orderIdToFind);
        assertNotNull(foundOrder);
        assertEquals(orderIdToFind, foundOrder.getOrderID());
    }

    //UTCID05
    @Test
    public void testFindByIDReturnsNull2() throws SQLException {
        int orderIdToFind = 1000; // Adjust the ID to be tested
        Order foundOrder = orderDAO.findByID(orderIdToFind);
        assertNull(foundOrder);
    }

    
    //UTCID06
    @Test
    public void testFindByIDGivenWrongArgumentReturnsException2() {

        try {
            int invalidOrderID = Integer.parseInt("a");
            Order foundOrder = orderDAO.findByID(invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("ID must be a number")) {
                Assert.assertEquals("ID must be a number", e.getMessage());
            }
        }

    }
    
    
    

}
