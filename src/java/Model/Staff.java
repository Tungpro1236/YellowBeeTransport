/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author regio
 */


public class Staff {
    private int staffId;
    private int userId;
    private int priceCostId;
    private String name; // Thêm để hiển thị dễ hơn

    public Staff(int staffId, int userId, int priceCostId, String name) {
        this.staffId = staffId;
        this.userId = userId;
        this.priceCostId = priceCostId;
        this.name = name;
    }

    // Getters và Setters
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

    public int getPriceCostId() {
        return priceCostId;
    }

    public void setPriceCostId(int priceCostId) {
        this.priceCostId = priceCostId;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
