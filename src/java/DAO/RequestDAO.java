/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PriceQuote;
import DBConnect.DBContext;
import Model.CheckingForm;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import java.util.UUID;
import java.sql.Statement;

/**
 *
 * @author regio
 */
public class RequestDAO extends DBContext {

    public List<PriceQuote> getRequestsByCustomerId(Integer userId) {
        List<PriceQuote> quotes = new ArrayList<>();
        String query = "SELECT pq.PriceQuoteID, pq.TruckAmount, pq.StaffAmount, "
                + "pq.FinalCost, pq.PriceCostID, pq.CheckingFormID "
                + "FROM PriceQuote pq "
                + "JOIN CheckingForm cf ON pq.CheckingFormID = cf.CheckingFormID "
                + "WHERE cf.UserID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            result = statement.executeQuery();
            while (result.next()) {
                PriceQuote quote = new PriceQuote(
                        result.getInt("PriceQuoteID"),
                        result.getInt("TruckAmount"),
                        result.getInt("StaffAmount"),
                        result.getBigDecimal("FinalCost"),
                        result.getInt("PriceCostID"),
                        result.getInt("CheckingFormID")
                );
                quotes.add(quote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotes;
    }

    public int countPendingRequests(int userId) {
        String query = "SELECT COUNT(pq.PriceQuoteID) "
                + "FROM PriceQuote pq "
                + "JOIN CheckingForm cf ON pq.CheckingFormID = cf.CheckingFormID "
                + "WHERE cf.UserID = ? AND pq.Status = 'Pending'";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public PriceQuote getRequestDetails(int priceQuoteId) {
        String query = "SELECT * FROM PriceQuote WHERE PriceQuoteID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, priceQuoteId);
            result = statement.executeQuery();
            if (result.next()) {
                return PriceQuote.builder()
                        .priceQuoteID(result.getInt("PriceQuoteID"))
                        .truckAmount(result.getInt("TruckAmount"))
                        .staffAmount(result.getInt("StaffAmount"))
                        .finalCost(result.getBigDecimal("FinalCost"))
                        .priceCostID(result.getInt("PriceCostID"))
                        .checkingFormID(result.getInt("CheckingFormID"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateFinalCost(int priceQuoteId, BigDecimal finalCost) {
        String query = "UPDATE PriceQuote SET FinalCost = ? WHERE PriceQuoteID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setBigDecimal(1, finalCost);
            statement.setInt(2, priceQuoteId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean createCheckingForm(Integer userId, String name, String phone, String email, String address, Timestamp checkingTime, Timestamp transportTime) {
        String query = "INSERT INTO CheckingForm (UserID, Name, Phone, Email, Address, CheckingTime, TransportTime, Status) VALUES (?, ?, ?, ?, ?, ?, ?, 'pending')";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setString(3, phone);
            if (email != null) {
                statement.setString(4, email);
            } else {
                statement.setNull(4, Types.NVARCHAR);
            }
            if (address != null) {
                statement.setString(5, address);
            } else {
                statement.setNull(5, Types.NVARCHAR);
            }
            statement.setTimestamp(6, checkingTime);
            statement.setTimestamp(7, transportTime);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePriceQuoteStatus(int quoteId, String status) {
        String sql = "UPDATE PriceQuote SET Status = ? WHERE PriceQuoteID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, quoteId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createContractFromQuote(int quoteId) {
        if (!updatePriceQuoteStatus(quoteId, "Accepted")) {
            return false; // Nếu không thể cập nhật trạng thái báo giá, dừng lại
        }

        String sql = "INSERT INTO Contract (PriceQuoteID, ContractStatus) VALUES (?, 'Pending')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quoteId);
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int contractId = generatedKeys.getInt(1);
                    System.out.println("Contract created with ID: " + contractId);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Khởi tạo kết nối database
    public static void main(String[] args) {
        // Tạo đối tượng DAO
        RequestDAO requestDAO = new RequestDAO();

        // Test với một user ID cụ thể
        int testUserId = 9; // Đổi ID tùy vào dữ liệu có sẵn trong DB

        // Kiểm tra số lượng đơn báo giá đang chờ xử lý
        int pendingRequests = requestDAO.countPendingRequests(testUserId);
        System.out.println("Pending Requests for User " + testUserId + ": " + pendingRequests);
    }
}

