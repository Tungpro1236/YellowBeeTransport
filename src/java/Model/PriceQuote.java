/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 *
 * @author regio & admin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceQuote {
    private int priceQuoteID;
    private int truckAmount;
    private int staffAmount;
    private BigDecimal finalCost; // Dùng BigDecimal thay vì double để giữ độ chính xác cao hơn
    private int priceCostID;
    private int checkingFormID;
    private String status;

    // Constructor 1
    public PriceQuote(int priceQuoteID, int truckAmount, int staffAmount, BigDecimal finalCost, int priceCostID, int checkingFormID) {
        this.priceQuoteID = priceQuoteID;
        this.truckAmount = truckAmount;
        this.staffAmount = staffAmount;
        this.finalCost = finalCost; 
        this.priceCostID = priceCostID;
        this.checkingFormID = checkingFormID;
    }

    // Constructor 2: Hỗ trợ kiểu double cho finalCost (từ bản cũ)
    public PriceQuote(int priceQuoteID, int truckAmount, int staffAmount, double finalCost, int priceCostID, int checkingFormID, String status) {
        this.priceQuoteID = priceQuoteID;
        this.truckAmount = truckAmount;
        this.staffAmount = staffAmount;
        this.finalCost = BigDecimal.valueOf(finalCost); // Chuyển double thành BigDecimal
        this.priceCostID = priceCostID;
        this.checkingFormID = checkingFormID;
        this.status = status;
    }

    
}
