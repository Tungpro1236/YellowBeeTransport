/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.CheckingForm;
import Model.Contract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.Statement;

/**
 *
 * @author regio
 */
public class ContractDAO extends DBContext {

   public Contract getContractById(int contractId) {
    Contract contract = null;
    String query = "SELECT c.ContractID, c.FinalCost, c.ContractStatusID, " +
                   "       cf.CheckingTime, cf.TransportTime, " +
                   "       GROUP_CONCAT(DISTINCT at.TruckID) AS TruckIDs, " +
                   "       GROUP_CONCAT(DISTINCT ast.StaffID) AS StaffIDs, " +
                   "       tp.ProblemCost " +
                   "FROM Contracts c " +
                   "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID " +
                   "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID " +  // Thay đổi cách lấy Truck
                   "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID " + // Thay đổi cách lấy Staff
                   "LEFT JOIN TransportProblemForm tp ON c.ContractID = tp.ContractID " +
                   "WHERE c.ContractID = ? " +
                   "GROUP BY c.ContractID";

    try {
        PreparedStatement statement = connection.prepareStatement(query);
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

            // Lấy danh sách TruckID
            String truckIdsStr = result.getString("TruckIDs");
            List<Integer> truckIds = new ArrayList<>();
            if (truckIdsStr != null) {
                for (String id : truckIdsStr.split(",")) {
                    truckIds.add(Integer.parseInt(id));
                }
            }
            System.out.println("Truck IDs: " + truckIds);

            // Lấy danh sách StaffID
            String staffIdsStr = result.getString("StaffIDs");
            List<Integer> staffIds = new ArrayList<>();
            if (staffIdsStr != null) {
                for (String id : staffIdsStr.split(",")) {
                    staffIds.add(Integer.parseInt(id));
                }
            }
            System.out.println("Staff IDs: " + staffIds);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return contract;
}


    public List<Contract> getContractsByCustomerId(int userId) {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT c.ContractID, c.CheckingFormID, c.PriceQuoteID,"
                + " c.TruckID, c.StaffID, c.FinalCost, c.ContractStatusID "
                + "FROM Contracts c "
                + "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID "
                + "WHERE cf.UserID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            result = statement.executeQuery();

            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .truckId(result.getInt("TruckID"))
                        .staffId(result.getInt("StaffID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .build();
                contracts.add(contract);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contracts;

    }

    public int countActiveContracts(Integer userId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Contracts c "
                + "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID "
                + "WHERE c.ContractStatusID IN (1, 2) AND cf.UserID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int countContractsByStatus(int userId, int statusId) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Contracts c "
                + "JOIN CheckingForm cf ON c.ContractID = cf.ContractID "
                + "WHERE cf.UserID = ? AND c.ContractStatusID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, statusId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM Contracts";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .truckId(result.getInt("TruckID"))
                        .staffId(result.getInt("StaffID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .build();
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public List<Contract> getContractsByStatus(int statusId) {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM Contracts WHERE ContractStatusID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, statusId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .truckId(result.getInt("TruckID"))
                        .staffId(result.getInt("StaffID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .build();
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public boolean updateContractStatus(int contractId, int newStatus) {
        String query = "UPDATE Contracts SET ContractStatusID = ? WHERE ContractID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newStatus);
            statement.setInt(2, contractId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createContractFromQuote(int quoteId) {
        String sql = "INSERT INTO Contract (PriceQuoteID, ContractStatus) VALUES (?, 'pending')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quoteId);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
