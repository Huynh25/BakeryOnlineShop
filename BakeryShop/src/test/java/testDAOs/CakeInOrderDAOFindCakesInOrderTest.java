/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package testDAOs;

import daos.CakeInOrderDAO;
import java.sql.SQLException;
import java.util.List;
import models.Cake;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class CakeInOrderDAOFindCakesInOrderTest {
    CakeInOrderDAO cakeInOrderDAO = new CakeInOrderDAO();

    public CakeInOrderDAOFindCakesInOrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
    public void testFindCakesInOrderReturnsCorrectOrder() throws SQLException {
        int orderIDToFind = 1; // Adjust the ID to be tested
        List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(orderIDToFind);
        assertNotNull(cakesInOrder);
        // Add additional assertions based on the actual behavior of findCakesInOrder method
    }

//UTCID02
    @Test
    public void testFindCakesInOrderReturnsEmptyList() throws SQLException {
        int orderIDToFind = 0; // Adjust the ID to be tested
        List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(orderIDToFind);
        assertTrue(cakesInOrder.isEmpty());
    }

//UTCID03
    @Test
    public void testFindCakesInOrderGivenWrongArgumentThrowsException() {
        int invalidOrderID = -1;
        try {
            List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("Order ID must be non-negative")) {
                assertEquals("Order ID must be non-negative", e.getMessage());
            }
        }
    }

//UTCID04
    @Test
    public void testFindCakesInOrderReturnsCorrectOrder2() throws SQLException {
        int orderIDToFind = 5; // Adjust the ID to be tested
        List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(orderIDToFind);
        assertNotNull(cakesInOrder);
        // Add additional assertions based on the actual behavior of findCakesInOrder method
    }

//UTCID05
    @Test
    public void testFindCakesInOrderReturnsEmptyList2() throws SQLException {
        int orderIDToFind = 1000; // Adjust the ID to be tested
        List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(orderIDToFind);
        assertTrue(cakesInOrder.isEmpty());
    }

//UTCID06
    @Test
    public void testFindCakesInOrderGivenInvalidArgumentThrowsException2() {
        try {
            int invalidOrderID = Integer.parseInt("a");
            List<Cake> cakesInOrder = cakeInOrderDAO.findCakesInOrder(invalidOrderID);
        } catch (Exception e) {
            if (e.getMessage().equals("Order ID must be a number")) {
                assertEquals("Order ID must be a number", e.getMessage());
            }
        }
    }

}
