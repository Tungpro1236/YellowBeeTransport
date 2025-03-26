/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.Contract;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContractDAO extends DBContext {

    public Contract getContractById(int contractId) {
        Contract contract = null;

        String query = "SELECT c.ContractID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID, c.ContractDate, "
                + "cf.Name AS CheckingUserName, cf.Phone AS CheckingUserPhone, cf.Email AS CheckingUserEmail, "
                + "cf.Address AS CheckingUserAddress, cf.CheckingTime, cf.TransportTime, cf.ServiceID, cf.Status AS CheckingFormStatus, "
                + "COALESCE(STRING_AGG(DISTINCT asf.StaffID, ','), '') AS StaffIDs, "
                + "COALESCE(STRING_AGG(DISTINCT atc.TruckID, ','), '') AS TruckIDs "
                + "FROM [YellowBee].[dbo].[Contracts] c "
                + "LEFT JOIN [YellowBee].[dbo].[CheckingForm] cf ON c.CheckingFormID = cf.CheckingFormID "
                + "LEFT JOIN [YellowBee].[dbo].[ArrangeStaff] asf ON c.ContractID = asf.ContractID "
                + "LEFT JOIN [YellowBee].[dbo].[ArrangeTruck] atc ON c.ContractID = atc.ContractID "
                + "WHERE c.ContractID = ? "
                + "GROUP BY c.ContractID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID, c.ContractDate, "
                + "cf.Name, cf.Phone, cf.Email, cf.Address, cf.CheckingTime, cf.TransportTime, cf.ServiceID, cf.Status;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contractId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .contractDate(result.getTimestamp("ContractDate"))
                        .build();

                // Process StaffIDs and TruckIDs if needed
                String staffIdsStr = result.getString("StaffIDs");
                String truckIdsStr = result.getString("TruckIDs");
                
                if (staffIdsStr != null && !staffIdsStr.isEmpty()) {
                    List<Integer> staffIds = new ArrayList<>();
                    for (String id : staffIdsStr.split(",")) {
                        staffIds.add(Integer.parseInt(id));
                    }
                }

                if (truckIdsStr != null && !truckIdsStr.isEmpty()) {
                    List<Integer> truckIds = new ArrayList<>();
                    for (String id : truckIdsStr.split(",")) {
                        truckIds.add(Integer.parseInt(id));
                    }
                }
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
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
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

    public int countActiveContracts(Integer userId) {
        int count = 0;
        String query = "SELECT COUNT(c.ContractID) FROM Contracts c "
                + "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID "
                + "WHERE c.ContractStatusID IN (1, 2) AND cf.UserID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT c.ContractID, c.CheckingFormID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID, "
                + "STRING_AGG(DISTINCT at.TruckID, ', ') AS TruckIDs, "
                + "STRING_AGG(DISTINCT ast.StaffID, ', ') AS StaffIDs "
                + "FROM Contracts c "
                + "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID "
                + "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID "
                + "GROUP BY c.ContractID, c.CheckingFormID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            
            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
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
        String query = "SELECT c.ContractID, c.ContractDate, c.FinalCost, c.ContractStatusID, c.PriceQuoteID "
                + "FROM Contracts c WHERE c.ContractStatusID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, statusId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Contract contract = Contract.builder()
                        .contractId(rs.getInt("ContractID"))
                        .contractDate(rs.getTimestamp("ContractDate"))
                        .finalCost(rs.getDouble("FinalCost"))
                        .contractStatusId(rs.getInt("ContractStatusID"))
                        .priceQuoteId(rs.getInt("PriceQuoteID"))
                        .build();
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public List<Contract> getContractsByMultipleStatus(List<Integer> statusList) {
        List<Contract> contracts = new ArrayList<>();
        String placeholders = statusList.stream().map(s -> "?").collect(Collectors.joining(","));
        String query = "SELECT ContractID, ContractDate, FinalCost, ContractStatusID, PriceQuoteID "
                + "FROM Contracts WHERE ContractStatusID IN (" + placeholders + ")";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < statusList.size(); i++) {
                statement.setInt(i + 1, statusList.get(i));
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contract contract = Contract.builder()
                        .contractId(rs.getInt("ContractID"))
                        .contractDate(rs.getTimestamp("ContractDate"))
                        .finalCost(rs.getDouble("FinalCost"))
                        .contractStatusId(rs.getInt("ContractStatusID"))
                        .priceQuoteId(rs.getInt("PriceQuoteID"))
                        .build();
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public boolean createContract(int priceQuoteID, String[] truckIDs, String[] staffIDs, BigDecimal finalCost, int checkingFormID) {
        String insertContractSQL = "INSERT INTO Contracts (PriceQuoteID, CheckingFormID, FinalCost, ContractStatusID, ContractDate) VALUES (?, ?, ?, ?, GETDATE());";
        String insertTruckSQL = "INSERT INTO ArrangeTruck (ContractID, TruckID) VALUES (?, ?);";
        String insertStaffSQL = "INSERT INTO ArrangeStaff (ContractID, StaffID) VALUES (?, ?);";

        try (Connection conn = new DBContext().connection;
             PreparedStatement psContract = conn.prepareStatement(insertContractSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            psContract.setInt(1, priceQuoteID);
            psContract.setInt(2, checkingFormID);
            psContract.setBigDecimal(3, finalCost);
            psContract.setInt(4, 1);  // 1 là ContractStatusID của "Pending"

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

            // Insert truck assignments
            try (PreparedStatement psTruck = conn.prepareStatement(insertTruckSQL)) {
                for (String truckID : truckIDs) {
                    psTruck.setInt(1, contractID);
                    psTruck.setInt(2, Integer.parseInt(truckID));
                    psTruck.addBatch();
                }
                psTruck.executeBatch();
            }

            // Insert staff assignments
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
            return false;
        }
    }

    public boolean updateContractStatus(int contractId, int newStatus) {
        String query = "UPDATE Contracts SET ContractStatusID = ? WHERE ContractID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newStatus);
            statement.setInt(2, contractId);
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        ContractDAO contractDAO = new ContractDAO();
        int testStatusId = 1;

        List<Contract> contracts = contractDAO.getContractsByStatus(testStatusId);

        if (contracts.isEmpty()) {
            System.out.println("Không tìm thấy hợp đồng nào có trạng thái ID = " + testStatusId);
        } else {
            System.out.println("Danh sách hợp đồng có trạng thái ID = " + testStatusId + ":");
            for (Contract contract : contracts) {
                System.out.println("ID: " + contract.getContractId()
                        + ", Date: " + contract.getContractDate()
                        + ", Cost: " + contract.getFinalCost()
                        + ", Status: " + contract.getContractStatusId()
                        + ", Quote ID: " + contract.getPriceQuoteId());
            }
        }
    }
}
