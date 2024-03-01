/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 * This class is used for Cart purpose. Item in cart.
 *
 * @author Gia Huy <https://github.com/ThomasTran17>
 */
public class Item {

    private Cake cake;
    private List<Topping> toppings;
    private int buyQuantity;

    public Item() {
    }

    public Item(Cake cake, List<Topping> toppings, int buyQuantity) {
        this.cake = cake;
        this.toppings = toppings;
        this.buyQuantity = buyQuantity;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public int getTotalPrice() {
        int toppingsPrice = 0;
        for (Topping topping : toppings) {
            toppingsPrice += topping.getToppingPrice();
        }

        return buyQuantity * (cake.getCakePrice() + toppingsPrice);
    }

    public boolean equals(Item item) {
        if (this.cake.getCakeID() != item.cake.getCakeID()
                || this.toppings.size() != item.toppings.size()) {
            return false;
        }

        for (Topping topping : item.toppings) {
            if (!this.toppings.contains(topping)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String itemString = "";
        itemString += cake.getCakeID() + ":";
        itemString += buyQuantity + ":";
        for (Topping topping : toppings) {
            itemString += topping.getToppingID() + ",";
        }
        
        if (!itemString.isEmpty()) {
            itemString = itemString.subSequence(0, itemString.length() - 1) + ";";
        }
        
        return itemString;
    }

}
