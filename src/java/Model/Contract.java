/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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
    private double finalCost;
    private int contractStatusId;
    private Date contractDate; // Ngày tạo hợp đồng
    private String statusDescription; // Mô tả trạng thái hợp đồng
    private String staffNames; // Danh sách nhân viên thực hiện hợp đồng
    private String licensePlates; // Danh sách xe tải thực hiện hợp đồng
    
    private String licensePlate;
    private CheckingForm checkingForm;

    // Constructor giữ nguyên từ bản cũ để tránh xung đột
    public Contract(int contractID, int checkingFormID, int priceQuoteID, double finalCost, int contractStatusID) {
        this.contractId = contractID;
        this.checkingFormId = checkingFormID;
        this.priceQuoteId = priceQuoteID;
        this.finalCost = finalCost;
        this.contractStatusId = contractStatusID;
    }
}


