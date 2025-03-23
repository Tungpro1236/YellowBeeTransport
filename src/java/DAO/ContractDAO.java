/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author admin
 */
import DBConnect.DBContext;
import Model.Contract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {
    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM Contracts";

        try (Connection connection = new DBContext().connection;
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                contracts.add(new Contract(
                    resultSet.getInt("ContractID"),
                    resultSet.getInt("CheckingFormID"),
                    resultSet.getInt("PriceQuoteID"),
                    resultSet.getInt("TruckID"),
                    resultSet.getInt("StaffID"),
                    resultSet.getDouble("FinalCost"),
                    resultSet.getInt("ContractStatusID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
    
    public boolean createContract(int priceQuoteID, String[] truckIDs, String[] staffIDs) {
    String insertContractSQL = "INSERT INTO Contracts (PriceQuoteID, Status) VALUES (?, 'Pending');";
    String insertTruckSQL = "INSERT INTO ContractTrucks (ContractID, TruckID) VALUES (?, ?);";
    String insertStaffSQL = "INSERT INTO ContractStaff (ContractID, StaffID) VALUES (?, ?);";
    
    try (Connection conn = new DBContext().connection;
         PreparedStatement psContract = conn.prepareStatement(insertContractSQL, Statement.RETURN_GENERATED_KEYS)) {
        
        // 1. Tạo hợp đồng mới
        psContract.setInt(1, priceQuoteID);
        int affectedRows = psContract.executeUpdate();
        
        if (affectedRows == 0) {
            throw new SQLException("Creating contract failed, no rows affected.");
        }
        
        // 2. Lấy ContractID vừa tạo
        ResultSet generatedKeys = psContract.getGeneratedKeys();
        int contractID = -1;
        if (generatedKeys.next()) {
            contractID = generatedKeys.getInt(1);
        } else {
            throw new SQLException("Creating contract failed, no ContractID obtained.");
        }

        // 3. Thêm xe tải vào ContractTrucks
        try (PreparedStatement psTruck = conn.prepareStatement(insertTruckSQL)) {
            for (String truckID : truckIDs) {
                psTruck.setInt(1, contractID);
                psTruck.setInt(2, Integer.parseInt(truckID));
                psTruck.addBatch();
            }
            psTruck.executeBatch();
        }

        // 4. Thêm nhân viên vào ContractStaff
        try (PreparedStatement psStaff = conn.prepareStatement(insertStaffSQL)) {
            for (String staffID : staffIDs) {
                psStaff.setInt(1, contractID);
                psStaff.setInt(2, Integer.parseInt(staffID));
                psStaff.addBatch();
            }
            psStaff.executeBatch();
        }

        return true;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
}