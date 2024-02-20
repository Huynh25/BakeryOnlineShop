/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Customer {
     private int userID;
     private String username;
     private String password;
     private String fullname;
     private String email;
     private String googleID;
     private String accessToken; 
     private String userAvatar;
     private String address;
     private String phoneNumber;

    public Customer() {
    }

    public Customer(int userID, String username, String password, String fullname, String googleID, String accessToken, String userAvatar, String address, String phonrNumber) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.googleID = googleID;
        this.accessToken = accessToken;
        this.userAvatar = userAvatar;
        this.address = address;
        this.phoneNumber = phonrNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", googleID=" + googleID + ", accessToken=" + accessToken + ", userAvatar=" + userAvatar + ", address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }
     
     
     
}
