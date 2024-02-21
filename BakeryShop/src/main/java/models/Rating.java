/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Rating {
    private Customer customer;
    private Cake cake;
    private Date ratingDate;
    private int ratingValue;
    private String comment;

    public Rating(Customer customer, Cake cake, Date ratingDate, int ratingValue, String comment) {
        this.customer = customer;
        this.cake = cake;
        this.ratingDate = ratingDate;
        this.ratingValue = ratingValue;
        this.comment = comment;
    }

    public Rating() {
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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


    
}
