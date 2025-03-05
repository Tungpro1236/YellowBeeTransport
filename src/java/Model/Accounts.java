/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author regio
 */
@Data
@Builder

public class Accounts {

    int AccountID;
    String Username;
    String Password;
    int AccountStatusID;
    String Description;
    String FullName;
    String Role;
}
