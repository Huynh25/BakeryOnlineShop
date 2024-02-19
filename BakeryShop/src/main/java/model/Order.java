/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Order {
     private int orderID;
     private int userID;
     private int staffID;
     private String orderDescription;
     private int totalPrice;
     private Date orderDate;
     private Date receivedDate; 
     private boolean wasPaid;
     private String status;

    public Order() {
    }

    public Order(int orderID, int userID, int staffID, String orderDescription, int totalPrice, Date orderDate, Date receivedDate, boolean wasPaid, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.staffID = staffID;
        this.orderDescription = orderDescription;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.receivedDate = receivedDate;
        this.wasPaid = wasPaid;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public boolean isWasPaid() {
        return wasPaid;
    }

    public void setWasPaid(boolean wasPaid) {
        this.wasPaid = wasPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", userID=" + userID + ", staffID=" + staffID + ", orderDescription=" + orderDescription + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", receivedDate=" + receivedDate + ", wasPaid=" + wasPaid + ", status=" + status + '}';
    }
     
     
}
