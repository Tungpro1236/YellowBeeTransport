package model;

import java.sql.Timestamp;

public class WorkShift {
    private int shiftID;
    private int staffID;
    private Timestamp shiftDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    
    // Additional field for display
    private String staffName;
    
    // Getters and Setters
    public int getShiftID() {
        return shiftID;
    }
    
    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }
    
    public int getStaffID() {
        return staffID;
    }
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public Timestamp getShiftDate() {
        return shiftDate;
    }
    
    public void setShiftDate(Timestamp shiftDate) {
        this.shiftDate = shiftDate;
    }
    
    public Timestamp getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
    public Timestamp getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
} 