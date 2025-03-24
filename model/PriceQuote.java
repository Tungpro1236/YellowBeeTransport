package model;

import java.sql.Timestamp;

public class PriceQuote {
    private int priceQuoteID;
    private int checkingFormID;
    private int staffID;
    private double basePrice;
    private double additionalFees;
    private double totalPrice;
    private String notes;
    private Timestamp createdAt;
    private String status;
    
    // Additional fields for display
    private String staffName;
    private String customerName;
    private String serviceName;
    
    // Getters and Setters
    public int getPriceQuoteID() {
        return priceQuoteID;
    }
    
    public void setPriceQuoteID(int priceQuoteID) {
        this.priceQuoteID = priceQuoteID;
    }
    
    public int getCheckingFormID() {
        return checkingFormID;
    }
    
    public void setCheckingFormID(int checkingFormID) {
        this.checkingFormID = checkingFormID;
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    public double getAdditionalFees() {
        return additionalFees;
    }
    
    public void setAdditionalFees(double additionalFees) {
        this.additionalFees = additionalFees;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
} 