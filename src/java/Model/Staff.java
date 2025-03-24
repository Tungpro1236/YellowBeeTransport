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
    private Integer currentContractID; 
    private Integer currentCheckingFormID; 

    // Constructor 1: Dùng trong một số DAO cũ
    public Staff(int staffID, int userID, int priceCostID, String fullName) {
        this.staffID = staffID;
        this.userID = userID;
        this.priceCostID = priceCostID;
        this.fullName = fullName;
    }

    // Constructor 2: Dùng trong một số DAO khác
    public Staff(int staffID, int userID, int priceCostID, boolean isAvailable) {
        this.staffID = staffID;
        this.userID = userID;
        this.priceCostID = priceCostID;
        this.isAvailable = isAvailable;
    }

    // Constructor 3: Đầy đủ thông tin (cũ)
    public Staff(int staffID, int userID, int priceCostID, boolean isAvailable, int roleID, String fullName, String phone, String email, Integer currentContractID, Integer currentCheckingFormID) {
        this.staffID = staffID;
        this.userID = userID;
        this.priceCostID = priceCostID;
        this.isAvailable = isAvailable;
        this.roleID = roleID;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.currentContractID = currentContractID;
        this.currentCheckingFormID = currentCheckingFormID;
    }


    // Getter và Setter
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
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

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
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
