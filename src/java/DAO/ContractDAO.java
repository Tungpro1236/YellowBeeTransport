/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.CheckingForm;
import Model.Contract;
<<<<<<< HEAD
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.sql.Statement;
>>>>>>> b5996a4bcd17749926d69557a2e93524a4fe712e

/**
 *
 * @author Admin
 */
public class ContractDAO extends DBContext {

    public Contract getContractById(int contractId) {
        Contract contract = null;
        String query = "SELECT c.ContractID, c.FinalCost, c.ContractStatusID, "
<<<<<<< HEAD
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
=======
                + "       cf.CheckingTime, cf.TransportTime, "
                + "       GROUP_CONCAT(DISTINCT at.TruckID) AS TruckIDs, "
                + "       GROUP_CONCAT(DISTINCT ast.StaffID) AS StaffIDs, "
                + "       tp.ProblemCost "
                + "FROM Contracts c "
                + "JOIN CheckingForm cf ON c.CheckingFormID = cf.CheckingFormID "
                + "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID "
                + // Thay đổi cách lấy Truck
                "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID "
                + // Thay đổi cách lấy Staff
                "LEFT JOIN TransportProblemForm tp ON c.ContractID = tp.ContractID "
                + "WHERE c.ContractID = ? "
                + "GROUP BY c.ContractID";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
>>>>>>> b5996a4bcd17749926d69557a2e93524a4fe712e
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

    public List<Contract> getContractsByStaffId(int staffId) throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.*, u.FullName as CustomerName, u.Phone as CustomerPhone, "
                + "s.ServiceName, st.FullName as StaffName "
                + "FROM Contracts c "
                + "JOIN Users u ON c.CustomerID = u.UserID "
                + "JOIN Services s ON c.ServiceID = s.ServiceID "
                + "JOIN Staff st ON c.StaffID = st.StaffID "
                + "WHERE c.StaffID = ? "
                + "ORDER BY c.ContractDate DESC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            result = statement.executeQuery();

            while (result.next()) {
                contracts.add(mapResultSetToContract(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return contracts;
    }

    public List<Contract> getActiveContractsByStaffId(int staffId) throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.*, u.FullName as CustomerName, u.Phone as CustomerPhone, "
                + "s.ServiceName, st.FullName as StaffName "
                + "FROM Contracts c "
                + "JOIN Users u ON c.CustomerID = u.UserID "
                + "JOIN Services s ON c.ServiceID = s.ServiceID "
                + "JOIN Staff st ON c.StaffID = st.StaffID "
                + "WHERE c.StaffID = ? AND c.Status = 'Active' "
                + "ORDER BY c.ContractDate DESC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            result = statement.executeQuery();

            while (result.next()) {
                contracts.add(mapResultSetToContract(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return contracts;
    }

    public boolean updateContractStatus(int contractId, String status) throws SQLException {
        String sql = "UPDATE Contracts SET Status = ? WHERE ContractID = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, contractId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private Contract mapResultSetToContract(ResultSet rs) throws SQLException {
        Contract contract = new Contract();
        contract.setContractId(rs.getInt("ContractID"));
        //contract.setCustomerID(rs.getInt("CustomerID"));
        // contract.setStaffID(rs.getInt("StaffID"));
        //contract.setServiceID(rs.getInt("ServiceID"));
        //contract.setContractDate(rs.getTimestamp("ContractDate"));
        //contract.setStatus(rs.getString("Status"));
        //contract.setTotalPrice(rs.getDouble("TotalPrice"));

        // Additional display fields
        //contract.setCustomerName(rs.getString("CustomerName"));
        //contract.setCustomerPhone(rs.getString("CustomerPhone"));
        //contract.setServiceName(rs.getString("ServiceName"));
        //contract.setStaffName(rs.getString("StaffName"));
        contract.setCheckingFormId(rs.getInt("checkingformID"));
        contract.setPriceQuoteId(rs.getInt("pricequoteID"));
        contract.setTruckId(rs.getInt("setTruckID"));
        contract.setStaffId(rs.getInt("staffID"));
        contract.setFinalCost(rs.getDouble("FinalCost"));
        contract.setContractStatusId(rs.getInt("contractstatusID"));

        return contract;
    }
<<<<<<< HEAD

//    public List<Contract> getAllContracts() {
//        List<Contract> contracts = new ArrayList<>();
//        String query = "SELECT c.ContractID, c.CheckingFormID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID, "
//                + "STRING_AGG(DISTINCT at.TruckID, ', ') AS TruckIDs, "
//                + "STRING_AGG(DISTINCT ast.StaffID, ', ') AS StaffIDs "
//                + "FROM Contracts c "
//                + "LEFT JOIN ArrangeTruck at ON c.ContractID = at.ContractID "
//                + "LEFT JOIN ArrangeStaff ast ON c.ContractID = ast.ContractID "
//                + "GROUP BY c.ContractID, c.CheckingFormID, c.PriceQuoteID, c.FinalCost, c.ContractStatusID";
//
//        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet result = statement.executeQuery()) {
//            while (result.next()) {
//                Contract contract = new Contract(
//                        result.getInt("ContractID"),
//                        result.getInt("CheckingFormID"),
//                        result.getInt("PriceQuoteID"),
//                        result.getString("TruckIDs"), // Lấy danh sách TruckID
//                        result.getString("StaffIDs"), // Lấy danh sách StaffID
//                        result.getDouble("FinalCost"),
//                        result.getInt("ContractStatusID")
//                );
//                contracts.add(contract);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return contracts;
//    }

    public boolean createContract(int priceQuoteID, String[] truckIDs, String[] staffIDs, BigDecimal finalCost, int checkingFormID) {
        String insertContractSQL = "INSERT INTO Contracts (PriceQuoteID, CheckingFormID, FinalCost, ContractStatusID, ContractDate) VALUES (?, ?, ?, ?, GETDATE());";
        String insertTruckSQL = "INSERT INTO ArrangeTruck (ContractID, TruckID) VALUES (?, ?);";
        String insertStaffSQL = "INSERT INTO ArrangeStaff (ContractID, StaffID) VALUES (?, ?);";

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
=======
>>>>>>> b5996a4bcd17749926d69557a2e93524a4fe712e
}
