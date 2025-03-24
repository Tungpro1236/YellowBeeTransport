/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.CheckingForm;
import Model.Contract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO extends DBContext {

    public Contract getContractById(int contractId) {
        Contract contract = null;
        String query = "SELECT c.ContractID, c.FinalCost, c.ContractStatusID, " +
                "cf.CheckingTime, cf.TransportTime, " +
                "GROUP_CONCAT(DISTINCT at.TruckID) AS TruckIDs, " +
                "GROUP_CONCAT(DISTINCT ast.StaffID) AS StaffIDs, " +
                "tp.ProblemCost " +
                "FROM Contracts c " +
                "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID " +
                "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID " +
                "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID " +
                "LEFT JOIN TransportProblemForm tp ON c.ContractID = tp.ContractID " +
                "WHERE c.ContractID = ? " +
                "GROUP BY c.ContractID";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contractId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                CheckingForm checkingForm = CheckingForm.builder()
                        .checkingTime(result.getTimestamp("CheckingTime"))
                        .transportTime(result.getTimestamp("TransportTime"))
                        .build();
                
                contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .checkingForm(checkingForm)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM Contracts";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                contracts.add(new Contract(
                    result.getInt("ContractID"),
                    result.getInt("CheckingFormID"),
                    result.getInt("PriceQuoteID"),
                    result.getInt("TruckID"),
                    result.getInt("StaffID"),
                    result.getDouble("FinalCost"),
                    result.getInt("ContractStatusID")
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
            
            psContract.setInt(1, priceQuoteID);
            int affectedRows = psContract.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating contract failed, no rows affected.");
            }
            
            ResultSet generatedKeys = psContract.getGeneratedKeys();
            int contractID = -1;
            if (generatedKeys.next()) {
                contractID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating contract failed, no ContractID obtained.");
            }

            try (PreparedStatement psTruck = conn.prepareStatement(insertTruckSQL)) {
                for (String truckID : truckIDs) {
                    psTruck.setInt(1, contractID);
                    psTruck.setInt(2, Integer.parseInt(truckID));
                    psTruck.addBatch();
                }
                psTruck.executeBatch();
            }

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
