/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 *
 */
public class Staff {

    private int staffId;
    private int userId;
    private String fullname;
    private String phone;
    private String email;
    private String address;
    private String status;
    private int priceCostId;
    private String name; // Thêm để hiển thị dễ hơn

    public Staff() {
    }

    public Staff(int staffId, int userId, int priceCostId, String fullname) {
        this.staffId = staffId;
        this.userId = userId;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.priceCostId = priceCostId;
        this.name = name;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriceCostId() {
        return priceCostId;
    }

    public void setPriceCostId(int priceCostId) {
        this.priceCostId = priceCostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
