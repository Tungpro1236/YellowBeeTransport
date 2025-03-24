/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.CheckingForm;
import Model.Contract;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO extends DBContext {

    public Contract getContractById(int contractId) {
        Contract contract = null;
        String query = "SELECT c.ContractID, c.FinalCost, c.ContractStatusID, "
                + "cf.CheckingTime, cf.TransportTime, "
                + "GROUP_CONCAT(DISTINCT at.TruckID) AS TruckIDs, "
                + "GROUP_CONCAT(DISTINCT ast.StaffID) AS StaffIDs, "
                + "tp.ProblemCost "
                + "FROM Contracts c "
                + "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID "
                + "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID "
                + "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID "
                + "LEFT JOIN TransportProblemForm tp ON c.ContractID = tp.ContractID "
                + "WHERE c.ContractID = ? "
                + "GROUP BY c.ContractID";

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
        String query = "SELECT "
                + "    c.ContractID, "
                + "    c.CheckingFormID, "
                + "    c.PriceQuoteID, "
                + "    c.FinalCost, "
                + "    c.ContractStatusID, "
                + "    c.ContractDate, "
                + "    cs.Description, "
                + "    ISNULL((SELECT STRING_AGG(u.FullName, ', ') "
                + "            FROM ArrangeStaff ast "
                + "            JOIN Staff s ON ast.StaffID = s.StaffID "
                + "            JOIN Users u ON s.UserID = u.UserID "
                + "            WHERE ast.ContractID = c.ContractID), 'No Staff Assigned') AS StaffNames, "
                + "    ISNULL((SELECT STRING_AGG(t.LicensePlate, ', ') "
                + "            FROM ArrangeTruck at "
                + "            JOIN Trucks t ON at.TruckID = t.TruckID "
                + "            WHERE at.ContractID = c.ContractID), 'No Trucks Assigned') AS LicensePlates "
                + "FROM Contracts c "
                + "LEFT JOIN ContractStatus cs ON c.ContractStatusID = cs.ContractStatusID";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .contractDate(result.getDate("ContractDate"))
                        .statusDescription(result.getString("Description"))
                        .staffNames(result.getString("StaffNames"))
                        .licensePlates(result.getString("LicensePlates"))
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
        String updateTruckStatusSQL = "UPDATE Truck SET IsAvailable = 0 WHERE TruckID = ?;";
        String updateStaffStatusSQL = "UPDATE Staff SET IsAvailable = 0 WHERE StaffID = ?;";

        try (Connection conn = new DBContext().connection; PreparedStatement psContract = conn.prepareStatement(insertContractSQL, Statement.RETURN_GENERATED_KEYS)) {

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

            // Gán xe tải vào hợp đồng và cập nhật trạng thái
            try (PreparedStatement psTruck = conn.prepareStatement(insertTruckSQL); PreparedStatement psUpdateTruck = conn.prepareStatement(updateTruckStatusSQL)) {
                for (String truckID : truckIDs) {
                    int id = Integer.parseInt(truckID);
                    psTruck.setInt(1, contractID);
                    psTruck.setInt(2, id);
                    psTruck.addBatch();

                    psUpdateTruck.setInt(1, id);
                    psUpdateTruck.addBatch();
                }
                psTruck.executeBatch();
                psUpdateTruck.executeBatch();
            }

            // Gán nhân viên vào hợp đồng và cập nhật trạng thái
            try (PreparedStatement psStaff = conn.prepareStatement(insertStaffSQL); PreparedStatement psUpdateStaff = conn.prepareStatement(updateStaffStatusSQL)) {
                for (String staffID : staffIDs) {
                    int id = Integer.parseInt(staffID);
                    psStaff.setInt(1, contractID);
                    psStaff.setInt(2, id);
                    psStaff.addBatch();

                    psUpdateStaff.setInt(1, id);
                    psUpdateStaff.addBatch();
                }
                psStaff.executeBatch();
                psUpdateStaff.executeBatch();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách hợp đồng theo trạng thái, dựa trên ContractStatus.Description
    public List<Contract> getContractsByStatus(String status) {
    List<Contract> contracts = new ArrayList<>();
    String query = "SELECT " +
                   "    c.ContractID, " +
                   "    c.CheckingFormID, " +
                   "    c.PriceQuoteID, " +
                   "    c.FinalCost, " +
                   "    c.ContractStatusID, " +
                   "    c.ContractDate, " +
                   "    cs.Description, " +
                   "    ISNULL((SELECT STRING_AGG(u.FullName, ', ') " +
                   "            FROM ArrangeStaff ast " +
                   "            JOIN Staff s ON ast.StaffID = s.StaffID " +
                   "            JOIN Users u ON s.UserID = u.UserID " +
                   "            WHERE ast.ContractID = c.ContractID), 'No Staff Assigned') AS StaffNames, " +
                   "    ISNULL((SELECT STRING_AGG(t.LicensePlate, ', ') " +
                   "            FROM ArrangeTruck at " +
                   "            JOIN Trucks t ON at.TruckID = t.TruckID " +
                   "            WHERE at.ContractID = c.ContractID), 'No Trucks Assigned') AS LicensePlates " +
                   "FROM Contracts c " +
                   "LEFT JOIN ContractStatus cs ON c.ContractStatusID = cs.ContractStatusID " +
                   "WHERE cs.Description = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        // Gán giá trị cho placeholder
        statement.setString(1, status);
        
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Contract contract = Contract.builder()
                        .contractId(result.getInt("ContractID"))
                        .checkingFormId(result.getInt("CheckingFormID"))
                        .priceQuoteId(result.getInt("PriceQuoteID"))
                        .finalCost(result.getDouble("FinalCost"))
                        .contractStatusId(result.getInt("ContractStatusID"))
                        .contractDate(result.getDate("ContractDate"))
                        .statusDescription(result.getString("Description"))
                        .staffNames(result.getString("StaffNames"))
                        .licensePlates(result.getString("LicensePlates"))
                        .build();
                contracts.add(contract);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return contracts;
}


    // Cập nhật trạng thái hợp đồng dựa trên contractId và newStatus (mô tả trạng thái)
    public boolean updateContractStatus(int contractId, String newStatus) {
        int newStatusId = convertStatus(newStatus);
        String sql = "UPDATE Contracts SET ContractStatusID = ? WHERE ContractID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newStatusId);
            ps.setInt(2, contractId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int convertStatus(String status) {
        if (status.equalsIgnoreCase("Pending")) {
            return 1;
        } else if (status.equalsIgnoreCase("Completed")) {
            return 2;
        } else if (status.equalsIgnoreCase("Canceled") || status.equalsIgnoreCase("Cancelled")) {
            return 3;
        }
        return 0; // Trả về 0 nếu không khớp; có thể ném Exception nếu cần
    }
}
