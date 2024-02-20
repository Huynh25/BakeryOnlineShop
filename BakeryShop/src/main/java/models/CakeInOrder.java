/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class CakeInOrder {
    private int cioID;
    private int cioQuantity;
    private Order order;
    private Cake cake;

    public CakeInOrder() {
    }

    public CakeInOrder(int cioID, int cioQuantity, Order order, Cake cake) {
        this.cioID = cioID;
        this.cioQuantity = cioQuantity;
        this.order = order;
        this.cake = cake;
    }

    public int getCioID() {
        return cioID;
    }

    public void setCioID(int cioID) {
        this.cioID = cioID;
    }

    public int getCioQuantity() {
        return cioQuantity;
    }

    public void setCioQuantity(int cioQuantity) {
        this.cioQuantity = cioQuantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    @Override
    public String toString() {
        return "CakeInOrder{" + "cioID=" + cioID + ", cioQuantity=" + cioQuantity + ", order=" + order + ", cake=" + cake + '}';
    }


    
    
}
