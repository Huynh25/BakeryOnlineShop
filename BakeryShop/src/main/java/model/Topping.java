/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Topping {

    private int toppingID;
    private String toppingName;
    private int toppingQuantity;
    private int toppingPrice;
    private String toppingImg;
    private String toppingDescription;

    public Topping() {
    }

    public Topping(int toppingID, String toppingName, int toppingQuantity, int toppingPrice, String toppingImg, String toppingDescription) {
        this.toppingID = toppingID;
        this.toppingName = toppingName;
        this.toppingQuantity = toppingQuantity;
        this.toppingPrice = toppingPrice;
        this.toppingImg = toppingImg;
        this.toppingDescription = toppingDescription;
    }

    public int getToppingID() {
        return toppingID;
    }

    public void setToppingID(int toppingID) {
        this.toppingID = toppingID;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public int getToppingQuantity() {
        return toppingQuantity;
    }

    public void setToppingQuantity(int toppingQuantity) {
        this.toppingQuantity = toppingQuantity;
    }

    public int getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(int toppingPrice) {
        this.toppingPrice = toppingPrice;
    }

    public String getToppingImg() {
        return toppingImg;
    }

    public void setToppingImg(String toppingImg) {
        this.toppingImg = toppingImg;
    }

    public String getToppingDescription() {
        return toppingDescription;
    }

    public void setToppingDescription(String toppingDescription) {
        this.toppingDescription = toppingDescription;
    }

    @Override
    public String toString() {
        return "Topping{" + "toppingID=" + toppingID + ", toppingName=" + toppingName + ", toppingQuantity=" + toppingQuantity + ", toppingPrice=" + toppingPrice + ", toppingImg=" + toppingImg + ", toppingDescription=" + toppingDescription + '}';
    }
    
    
}
