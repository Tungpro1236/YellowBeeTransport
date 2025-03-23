/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Contract {
    private int contractID;
    private int checkingFormID;
    private int priceQuoteID;
    private int truckID;
    private int staffID;
    private double finalCost;
    private int contractStatusID;

    public Contract() {}

    public Contract(int contractID, int checkingFormID, int priceQuoteID, int truckID, int staffID, double finalCost, int contractStatusID) {
        this.contractID = contractID;
        this.checkingFormID = checkingFormID;
        this.priceQuoteID = priceQuoteID;
        this.truckID = truckID;
        this.staffID = staffID;
        this.finalCost = finalCost;
        this.contractStatusID = contractStatusID;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public int getCheckingFormID() {
        return checkingFormID;
    }

    public void setCheckingFormID(int checkingFormID) {
        this.checkingFormID = checkingFormID;
    }

    public int getPriceQuoteID() {
        return priceQuoteID;
    }

    public void setPriceQuoteID(int priceQuoteID) {
        this.priceQuoteID = priceQuoteID;
    }

    public int getTruckID() {
        return truckID;
    }

    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public int getContractStatusID() {
        return contractStatusID;
    }

    public void setContractStatusID(int contractStatusID) {
        this.contractStatusID = contractStatusID;
    }
    
    
    
}