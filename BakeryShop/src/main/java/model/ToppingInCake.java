/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class ToppingInCake {
    private Topping topping;
    private CakeInOrder cakeInOrder;
    private int ticQuantity;

    public ToppingInCake() {
    }

    public ToppingInCake(Topping topping, CakeInOrder cakeInOrder, int ticQuantity) {
        this.topping = topping;
        this.cakeInOrder = cakeInOrder;
        this.ticQuantity = ticQuantity;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public CakeInOrder getCakeInOrder() {
        return cakeInOrder;
    }

    public void setCakeInOrder(CakeInOrder cakeInOrder) {
        this.cakeInOrder = cakeInOrder;
    }

    public int getTicQuantity() {
        return ticQuantity;
    }

    public void setTicQuantity(int ticQuantity) {
        this.ticQuantity = ticQuantity;
    }

    @Override
    public String toString() {
        return "ToppingInCake{" + "topping=" + topping.getToppingID() + ", cakeInOrder=" + cakeInOrder.getCioID() + ", ticQuantity=" + ticQuantity + '}';
    }

    
    }
