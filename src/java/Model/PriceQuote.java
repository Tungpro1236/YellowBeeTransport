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
 * @author regio
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceQuote {
    int priceQuoteID;
    int truckAmount;
    int staffAmount;
    BigDecimal finalCost;
    int priceCostID;
    int checkingFormID;
}

