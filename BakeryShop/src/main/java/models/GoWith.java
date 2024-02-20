/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class GoWith {
    private Cake cake;
    private Topping topping;

    public GoWith() {
    }

    public GoWith(Cake cake, Topping topping) {
        this.cake = cake;
        this.topping = topping;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    @Override
    public String toString() {
        return "GoWith{" + "cake=" + cake + ", topping=" + topping + '}';
    }
     
    
}
