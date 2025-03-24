/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author regio & admin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {
    private int contractId;
    private int checkingFormId;
    private int priceQuoteId;
    private int truckId;
    private int staffId;
    private double finalCost;
    private int contractStatusId;
    
    private String licensePlate;
    private CheckingForm checkingForm;

    // Constructor giữ nguyên từ bản cũ để tránh xung đột
    public Contract(int contractID, int checkingFormID, int priceQuoteID, int truckID, int staffID, double finalCost, int contractStatusID) {
        this.contractId = contractID;
        this.checkingFormId = checkingFormID;
        this.priceQuoteId = priceQuoteID;
        this.truckId = truckID;
        this.staffId = staffID;
        this.finalCost = finalCost;
        this.contractStatusId = contractStatusID;
    }
}

