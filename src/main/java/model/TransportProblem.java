package model;

import java.sql.Timestamp;

public class TransportProblem {
    private int tpfID;
    private int contractID;
    private String problemDescription;
    private double problemCost;
    private int staffID;
    private Timestamp checkingTime;
    private Timestamp transportTime;
    
    public TransportProblem() {
    }
    
    public TransportProblem(int tpfID, int contractID, String problemDescription, double problemCost, int staffID) {
        this.tpfID = tpfID;
        this.contractID = contractID;
        this.problemDescription = problemDescription;
        this.problemCost = problemCost;
        this.staffID = staffID;
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

    public Timestamp getCheckingTime() {
        return checkingTime;
    }

    public void setCheckingTime(Timestamp checkingTime) {
        this.checkingTime = checkingTime;
    }

    public Timestamp getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Timestamp transportTime) {
        this.transportTime = transportTime;
    }

    @Override
    public String toString() {
        return "TransportProblem{" +
                "tpfID=" + tpfID +
                ", contractID=" + contractID +
                ", problemDescription='" + problemDescription + '\'' +
                ", problemCost=" + problemCost +
                ", staffID=" + staffID +
                ", checkingTime=" + checkingTime +
                ", transportTime=" + transportTime +
                '}';
    }
} 