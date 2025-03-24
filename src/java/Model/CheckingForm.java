/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CheckingForm {
    private int checkingFormID;
    private int userID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Timestamp checkingTime;
    private Timestamp transportTime;
    private int serviceID;
    private String status;
    private int staffID;
}
