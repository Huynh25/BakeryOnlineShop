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
public class ProductHistory {
    private int phID;
    private int phQuantity;
    private Date updatedDate;
    private Date createDate;
    private Staff updateBy;
    private Staff createBy;
    private Topping topping;
    private Cake cake;

    public ProductHistory() {
    }

    public ProductHistory(int phID, int phQuantity, Date updatedDate, Date createDate, Staff updateBy, Staff createBy, Topping topping, Cake cake) {
        this.phID = phID;
        this.phQuantity = phQuantity;
        this.updatedDate = updatedDate;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.createBy = createBy;
        this.topping = topping;
        this.cake = cake;
    }

    public int getPhID() {
        return phID;
    }

    public void setPhID(int phID) {
        this.phID = phID;
    }

    public int getPhQuantity() {
        return phQuantity;
    }

    public void setPhQuantity(int phQuantity) {
        this.phQuantity = phQuantity;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Staff getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Staff updateBy) {
        this.updateBy = updateBy;
    }

    public Staff getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Staff createBy) {
        this.createBy = createBy;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    
    
}