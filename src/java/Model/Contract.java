/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
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


    int contractId;
    int checkingFormId;
    int priceQuoteId;
    Date contractDate;
    double finalCost;
    int contractStatusId;
    

  

    
  
}

