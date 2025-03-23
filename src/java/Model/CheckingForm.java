/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

public class CheckingForm {
    private int checkingFormID;
    private int customerID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date checkingTime;
    private Date transportTime;
    private int serviceID;
    private String status;
    private int assignedStaffID;

    public CheckingForm() {
    }

    public CheckingForm(int checkingFormID, int customerID, String name, String phone, String email, String address, Date checkingTime, Date transportTime, int serviceID, String status, int assignedStaffID) {
        this.checkingFormID = checkingFormID;
        this.customerID = customerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.checkingTime = checkingTime;
        this.transportTime = transportTime;
        this.serviceID = serviceID;
        this.status = status;
        this.assignedStaffID = assignedStaffID;
    }

    public int getAssignedStaffID() {
        return assignedStaffID;
    }

    public void setAssignedStaffID(int assignedStaffID) {
        this.assignedStaffID = assignedStaffID;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCheckingFormID() {
        return checkingFormID;
    }

    public void setCheckingFormID(int checkingFormID) {
        this.checkingFormID = checkingFormID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCheckingTime() {
        return checkingTime;
    }

    public void setCheckingTime(Date checkingTime) {
        this.checkingTime = checkingTime;
    }

    public Date getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Date transportTime) {
        this.transportTime = transportTime;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
}
