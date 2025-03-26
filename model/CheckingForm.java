package model;

import java.sql.Timestamp;

public class CheckingForm {
    private int checkingFormID;
    private Customer customer;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Timestamp checkingTime;
    private Timestamp transportTime;
    private Service service;
    private String status;
    private Staff staff;
    
    // Getters and Setters
    public int getCheckingFormID() {
        return checkingFormID;
    }
    
    public void setCheckingFormID(int checkingFormID) {
        this.checkingFormID = checkingFormID;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    
    public Timestamp getCheckingTime() {
        return checkingTime;
    }
    
    public void setCheckingTime(Timestamp checkingTime) {
        this.checkingTime = checkingTime;
    }
    
    public Timestamp getTransportTime() {
        return transportTime;
    }
    
    public void setTransportTime(Timestamp transportTime) {
        this.transportTime = transportTime;
    }
    
    public Service getService() {
        return service;
    }
    
    public void setService(Service service) {
        this.service = service;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Staff getStaff() {
        return staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
} 