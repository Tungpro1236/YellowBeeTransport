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
 * @author regio
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {

    int contractId;
    int checkingFormId;
    int priceQuoteId;
    int truckId;
    int staffId;
    double finalCost;
    int contractStatusId;
    
    
    
    private String licensePlate;
    private CheckingForm checkingForm;
}
