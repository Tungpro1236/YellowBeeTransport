/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private int genderId;
    private String image;
    private int accountId;
    private int roleId;
   private String roleName; // Tên role đã có sẵn

    // Phương thức lấy giới tính theo genderId
    public String getGender() {
        return (genderId == 1) ? "Male" : (genderId == 2) ? "Female" : "Other";
    }

    // Phương thức lấy tên vai trò (đã có sẵn roleName)
    public String getRoleName() {
        return roleName != null ? roleName : "Unknown Role";
    }
}