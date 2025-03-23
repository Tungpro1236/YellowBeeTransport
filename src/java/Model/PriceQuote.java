/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */

public class PriceQuote {
    private int priceQuoteID;
    private int truckAmount;
    private int staffAmount;
    private double finalCost;
    private int priceCostID;
    private int checkingFormID;
    private String status;

    public PriceQuote(int priceQuoteID, int truckAmount, int staffAmount, double finalCost, int priceCostID, int checkingFormID, String status) {
        this.priceQuoteID = priceQuoteID;
        this.truckAmount = truckAmount;
        this.staffAmount = staffAmount;
        this.finalCost = finalCost;
        this.priceCostID = priceCostID;
        this.checkingFormID = checkingFormID;
        this.status = status;
    }

    public PriceQuote() {
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public int getPriceQuoteID() {
        return priceQuoteID;
    }

    public void setPriceQuoteID(int priceQuoteID) {
        this.priceQuoteID = priceQuoteID;
    }

    public int getTruckAmount() {
        return truckAmount;
    }

    public void setTruckAmount(int truckAmount) {
        this.truckAmount = truckAmount;
    }

    public int getStaffAmount() {
        return staffAmount;
    }

    public void setStaffAmount(int staffAmount) {
        this.staffAmount = staffAmount;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public int getPriceCostID() {
        return priceCostID;
    }

    public void setPriceCostID(int priceCostID) {
        this.priceCostID = priceCostID;
    }

    public int getCheckingFormID() {
        return checkingFormID;
    }

    public void setCheckingFormID(int checkingFormID) {
        this.checkingFormID = checkingFormID;
    }


}