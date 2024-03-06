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
    private List<Integer> toppingsBuyQuantity;
    private int buyQuantity;

    public Item() {
    }

    public Item(Cake cake, List<Topping> toppings, List<Integer> toppingsBuyQuantity, int buyQuantity) {
        this.cake = cake;
        this.toppings = toppings;
        this.toppingsBuyQuantity = toppingsBuyQuantity;
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

    public List<Integer> getToppingsBuyQuantity() {
        return toppingsBuyQuantity;
    }

    public void setToppingsBuyQuantity(List<Integer> toppingsBuyQuantity) {
        this.toppingsBuyQuantity = toppingsBuyQuantity;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public int getTotalPrice() {
        int toppingsPrice = 0;
        int length = toppings.size();
        for (int i = 0; i < length; ++i) {
            toppingsPrice += (toppings.get(i).getToppingPrice() * toppingsBuyQuantity.get(i));
        }

        return buyQuantity * (cake.getCakePrice() + toppingsPrice);
    }

    public boolean equals(Item item) {
        if (this.cake.getCakeID() != item.cake.getCakeID()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String itemString = "";
        itemString += cake.getCakeID() + ":";
        itemString += buyQuantity + ":";

        int length = toppings.size();
        for (int i = 0; i < length; ++i) {
            itemString += (toppings.get(i).getToppingID() + "-" + toppingsBuyQuantity.get(i) + ",");
        }

        if (!itemString.isEmpty()) {
            itemString = itemString.subSequence(0, itemString.length() - 1) + ";";
        }

        return itemString;
    }

}
