/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Truck;
import DBConnect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO {
    private Connection connection;

    public TruckDAO() {
        try {
            DBContext db = new DBContext();
            connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách tất cả xe
    public List<Truck> getAllTrucks() {
        List<Truck> truckList = new ArrayList<>();
        String sql = "SELECT TruckID, TruckTypeID, LicensePlate, TruckImage FROM Trucks";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Truck truck = new Truck(
                    rs.getInt("TruckID"),
                    rs.getInt("TruckTypeID"),
                    rs.getString("LicensePlate"),
                    rs.getString("TruckImage")
                );
                truckList.add(truck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return truckList;
    }
    
    // Lấy danh sách xe khả dụng (chưa có trong hợp đồng)
public List<Truck> getAvailableTrucks() {
    List<Truck> truckList = new ArrayList<>();
    String sql = "SELECT t.TruckID, t.TruckTypeID, tt.TruckPayload, t.LicensePlate, t.TruckImage, t.CurrentContractID "
               + "FROM Trucks t "
               + "JOIN TruckType tt ON t.TruckTypeID = tt.TruckTypeID "
               + "WHERE NOT EXISTS (SELECT 1 FROM Contracts c WHERE c.TruckID = t.TruckID)";
    
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Truck truck = new Truck(
                rs.getInt("TruckID"),
                rs.getInt("TruckTypeID"),
                rs.getString("LicensePlate"),
                rs.getString("TruckImage")
                
            );
            truck.setTruckPayload(rs.getDouble("TruckPayload")); // Gán giá trị TruckPayload
            truckList.add(truck);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return truckList;
}



    // Lấy danh sách xe đang được sử dụng trong hợp đồng
    public List<Truck> getTrucksInContracts() {
        List<Truck> truckList = new ArrayList<>();
        String sql = "SELECT t.TruckID, t.TruckTypeID, t.LicensePlate, t.TruckImage, c.ContractID " +
                     "FROM Trucks t " +
                     "JOIN Contracts c ON t.TruckID = c.TruckID";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Truck truck = new Truck(
                    rs.getInt("TruckID"),
                    rs.getInt("TruckTypeID"),
                    rs.getString("LicensePlate"),
                    rs.getString("TruckImage")
                );
                truck.setCurrentContractID(rs.getInt("ContractID"));
                truckList.add(truck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return truckList;
    }
}

