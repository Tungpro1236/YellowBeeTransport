/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Staff {
    private int staffID;
    private int userID;
    private int priceCostID;
    private boolean isAvailable;
    private int roleID;
    private String fullName;
    private String phone;
    private String email;
    private Integer currentContractID; // Nhân viên đang đi hợp đồng nào
    private Integer currentCheckingFormID; // Nhân viên đang khảo sát CheckingForm nào

    public Staff(int staffID, int userID, int priceCostID, boolean isAvailable) {
        this.staffID = staffID;
        this.userID = userID;
        this.priceCostID = priceCostID;
        this.isAvailable = isAvailable;
    }

    // Getter và Setter
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    
    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPriceCostID() {
        return priceCostID;
    }

    public void setPriceCostID(int priceCostID) {
        this.priceCostID = priceCostID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCurrentContractID() {
        return currentContractID;
    }

    public void setCurrentContractID(Integer currentContractID) {
        this.currentContractID = currentContractID;
    }

    public Integer getCurrentCheckingFormID() {
        return currentCheckingFormID;
    }

    public void setCurrentCheckingFormID(Integer currentCheckingFormID) {
        this.currentCheckingFormID = currentCheckingFormID;
    }

    
}
