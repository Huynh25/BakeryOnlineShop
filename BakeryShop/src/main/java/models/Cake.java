/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Cake {
    
    private int cakeID;
    private String cakeName;
    private String cakeDescription;
    private int cakePrice;
    private String cakeImg;
    private int cakeQuantity;
    private String cakeType;

    public Cake() {
    }

    public Cake(int cakeID, String cakeName, String cakeDescription, int cakePrice, String cakeImg, int cakeQuantity, String cakeType) {
        this.cakeID = cakeID;
        this.cakeName = cakeName;
        this.cakeDescription = cakeDescription;
        this.cakePrice = cakePrice;
        this.cakeImg = cakeImg;
        this.cakeQuantity = cakeQuantity;
        this.cakeType = cakeType;
    }

   

    public int getCakeID() {
        return cakeID;
    }

    public void setCakeID(int cakeID) {
        this.cakeID = cakeID;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getCakeDescription() {
        return cakeDescription;
    }

    public void setCakeDescription(String cakeDescription) {
        this.cakeDescription = cakeDescription;
    }

    public int getCakePrice() {
        return cakePrice;
    }

    public void setCakePrice(int cakePrice) {
        this.cakePrice = cakePrice;
    }

    public String getCakeImg() {
        return cakeImg;
    }

    public void setCakeImg(String cakeImg) {
        this.cakeImg = cakeImg;
    }

    public int getCakeQuantity() {
        return cakeQuantity;
    }

    public void setCakeQuantity(int cakeQuantity) {
        this.cakeQuantity = cakeQuantity;
    }

    public String getCakeType() {
        return cakeType;
    }

    public void setCakeType(String cakeType) {
        this.cakeType = cakeType;
    }

    @Override
    public String toString() {
        return "Cake{" + "cakeID=" + cakeID + ", cakeName=" + cakeName + ", cakeDescription=" + cakeDescription + ", cakePrice=" + cakePrice + ", cakeImg=" + cakeImg + ", cakeQuantity=" + cakeQuantity + ", cakeType=" + cakeType + '}';
    }

    
}
