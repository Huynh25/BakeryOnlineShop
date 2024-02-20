/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nguyen Truong An CE170984
 */
public class Staff {
     private int staffID;
     private String staffName;
     private String password;
     private String fullname;
     private String email;
     private String staffAvatar;
     private String address;
     private String phoneNumber;
     private int managerID;

    public Staff() {
    }

    public Staff(int staffID, String staffName, String password, String fullname, String email, String staffAvatar, String address, String phoneNumber, int managerID) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.staffAvatar = staffAvatar;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.managerID = managerID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStaffAvatar() {
        return staffAvatar;
    }

    public void setStaffAvatar(String staffAvatar) {
        this.staffAvatar = staffAvatar;
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

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffID=" + staffID + ", staffName=" + staffName + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", staffAvatar=" + staffAvatar + ", address=" + address + ", phoneNumber=" + phoneNumber + ", managerID=" + managerID + '}';
    }
     
     
     
}
