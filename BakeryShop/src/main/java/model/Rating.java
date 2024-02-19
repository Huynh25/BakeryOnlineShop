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
public class Rating {
    private Customer userID;
    private Cake cake;
    private Date ratingDate;
    private int ratingValue;
    private String comment;

    public Rating() {
    }

    public Rating(Customer userID, Cake cake, Date ratingDate, int ratingValue, String comment) {
        this.userID = userID;
        this.cake = cake;
        this.ratingDate = ratingDate;
        this.ratingValue = ratingValue;
        this.comment = comment;
    }

    public Customer getUserID() {
        return userID;
    }

    public void setUserID(Customer userID) {
        this.userID = userID;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" + "userID=" + userID + ", cake=" + cake + ", ratingDate=" + ratingDate + ", ratingValue=" + ratingValue + ", comment=" + comment + '}';
    }

    
}
