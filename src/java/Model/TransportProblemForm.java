/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

public class TransportProblemForm {
    private int tpfID;
    private int contractID;
    private String problemDescription;
    private double problemCost;
    private int staffID;
    private LocalDateTime reportDate;
    private String status;

    public TransportProblemForm() {
    }

    public TransportProblemForm(int tpfID, int contractID, String problemDescription, double problemCost, int staffID, LocalDateTime reportDate, String status) {
        this.tpfID = tpfID;
        this.contractID = contractID;
        this.problemDescription = problemDescription;
        this.problemCost = problemCost;
        this.staffID = staffID;
        this.reportDate = reportDate;
        this.status = status;
    }

    public int getTpfID() {
        return tpfID;
    }

    public void setTpfID(int tpfID) {
        this.tpfID = tpfID;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public double getProblemCost() {
        return problemCost;
    }

    public void setProblemCost(double problemCost) {
        this.problemCost = problemCost;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
