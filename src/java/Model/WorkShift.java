/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;


/**
 *
 * @author Admin
 */
public class WorkShift {

    private int shiftID;
    private int staffID;
    private Timestamp shiftDate;
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private String staffName;

    public WorkShift() {
    }

    public WorkShift(int shiftID, int staffID, Timestamp shiftDate, Timestamp startTime, Timestamp endTime, String status, String staffName) {
        this.shiftID = shiftID;
        this.staffID = staffID;
        this.shiftDate = shiftDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.staffName = staffName;
    }

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
