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
 * @author regio
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheckingForm {
    int userId;
    String name;
    String phone;
    String email;
    String address;
    Timestamp checkingTime;
    Timestamp transportTime;
    int serviceId;
    int staffId;
    String status;
}
