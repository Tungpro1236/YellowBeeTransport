/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author Admin
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheckingForm {
<<<<<<< HEAD
  private int checkingFormID;
    private int customerID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Timestamp checkingTime;
    private Timestamp transportTime;
    private int serviceID;
    private String status;
    private int assignedStaffID;
=======
    int userId;
    String name;
    String phone;
    String email;
    String address;
    Timestamp checkingTime;
    Timestamp transportTime;
    int serviceId;
    int staffId;
//    String Customer;
//    String status;
>>>>>>> b5996a4bcd17749926d69557a2e93524a4fe712e

   
}
