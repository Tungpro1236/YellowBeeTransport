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
import java.util.stream.Collectors;

/**
 *
 * @author regio
 */
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
                if (truckIdsStr != null && !truckIdsStr.isEmpty()) {
                    for (String id : truckIdsStr.split(",")) {
                        truckIds.add(Integer.parseInt(id));
                    }
                }
                System.out.println("Truck IDs: " + truckIds);

                // Lấy danh sách StaffID
                String staffIdsStr = result.getString("StaffIDs");
                List<Integer> staffIds = new ArrayList<>();
                if (staffIdsStr != null && !staffIdsStr.isEmpty()) {
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
        String query = "SELECT COUNT(c.ContractID) FROM Contracts c "
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
                        .contractId(rs.getInt("ContractID")) // Thêm contractId
                        .contractDate(rs.getDate("ContractDate"))
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

    public static void main(String[] args) {
        // Tạo đối tượng DAO
        ContractDAO contractDAO = new ContractDAO();
        // Test với trạng thái hợp đồng = 1 (Pending)
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
