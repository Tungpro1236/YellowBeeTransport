package model;

import java.sql.Timestamp;

public class Contract {
    private int contractID;
    private int customerID;
    private int serviceID;
    private int staffID;
    private Timestamp transportTime;
    private double finalCost;
    private String status;
    
    // Additional fields for display
    private String customerName;
    private String customerPhone;
    private String serviceName;
    private String staffName;
    
    // Getters and Setters
    public int getContractID() {
        return contractID;
    }
    
    public void setContractID(int contractID) {
        this.contractID = contractID;
    }
    
    public int getCustomerID() {
        return customerID;
    }
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public int getServiceID() {
        return serviceID;
    }
    
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public Timestamp getTransportTime() {
        return transportTime;
    }
    
    public void setTransportTime(Timestamp transportTime) {
        this.transportTime = transportTime;
    }
    
    public double getFinalCost() {
        return finalCost;
    }
    
    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
} 