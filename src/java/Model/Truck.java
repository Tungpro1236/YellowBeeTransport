/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Truck {
    private int truckID;
    private int truckTypeID;
    private String licensePlate;
    private String truckImage;
    private double truckPayload;
    private Integer currentContractID; // Xe đang được sử dụng trong hợp đồng nào

    public Truck(int truckID, int truckTypeID, String licensePlate, String truckImage) {
        this.truckID = truckID;
        this.truckTypeID = truckTypeID;
        this.licensePlate = licensePlate;
        this.truckImage = truckImage;
    }

    // Getter và Setter
    public int getTruckID() {
        return truckID;
    }

    public void setTruckID(int truckID) {
        this.truckID = truckID;
    }

    public double getTruckPayload() {
        return truckPayload;
    }

    public void setTruckPayload(double truckPayload) {
        this.truckPayload = truckPayload;
    }

    public int getTruckTypeID() {
        return truckTypeID;
    }

    public void setTruckTypeID(int truckTypeID) {
        this.truckTypeID = truckTypeID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTruckImage() {
        return truckImage;
    }

    public void setTruckImage(String truckImage) {
        this.truckImage = truckImage;
    }

    public Integer getCurrentContractID() {
        return currentContractID;
    }

    public void setCurrentContractID(Integer currentContractID) {
        this.currentContractID = currentContractID;
    }
}
