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
import Model.PriceQuote;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PriceQuoteDAO {

    private Connection connection;

    public PriceQuoteDAO() {
        try {
            DBContext db = new DBContext();
            this.connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PriceQuote> getAllPriceQuotes() {
        List<PriceQuote> priceQuotes = new ArrayList<>();
        String query = "SELECT * FROM PriceQuote";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                priceQuotes.add(PriceQuote.builder()
                        .priceQuoteID(rs.getInt("PriceQuoteID"))
                        .truckAmount(rs.getInt("TruckAmount"))
                        .staffAmount(rs.getInt("StaffAmount"))
                        .finalCost(rs.getBigDecimal("FinalCost"))
                        .priceCostID(rs.getInt("PriceCostID"))
                        .checkingFormID(rs.getInt("CheckingFormID"))
                        .status(rs.getString("Status"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceQuotes;
    }

    public boolean updateStatus(int priceQuoteID, String status) {
        String sql = "UPDATE PriceQuote SET Status = ? WHERE PriceQuoteID = ?";

        try {
            DBContext db = new DBContext();
            this.connection = db.connection;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, priceQuoteID);

            int affectedRows = ps.executeUpdate();
            ps.close();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả PriceQuotes có trạng thái "Canceled"
    public List<PriceQuote> getAllCanceledPriceQuotes() {
        List<PriceQuote> priceQuotes = new ArrayList<>();
        String query = "SELECT * FROM PriceQuote WHERE Status = 'Cancelled'";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                priceQuotes.add(new PriceQuote(
                        rs.getInt("PriceQuoteID"),
                        rs.getInt("TruckAmount"),
                        rs.getInt("StaffAmount"),
                        rs.getDouble("FinalCost"),
                        rs.getInt("PriceCostID"),
                        rs.getInt("CheckingFormID"),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceQuotes;
    }

// Lấy tất cả PriceQuotes có trạng thái "Pending"
    public List<PriceQuote> getAllPendingPriceQuotes() {
        List<PriceQuote> priceQuotes = new ArrayList<>();
        String query = "SELECT * FROM PriceQuote WHERE Status = 'Pending'";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                priceQuotes.add(new PriceQuote(
                        rs.getInt("PriceQuoteID"),
                        rs.getInt("TruckAmount"),
                        rs.getInt("StaffAmount"),
                        rs.getDouble("FinalCost"),
                        rs.getInt("PriceCostID"),
                        rs.getInt("CheckingFormID"),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceQuotes;
    }

    public void cancelPriceQuote(int priceQuoteID) {
        String sql = "UPDATE PriceQuote SET Status = 'Cancelled' WHERE PriceQuoteID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, priceQuoteID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public PriceQuote getPriceQuoteById(int priceQuoteID) {
        String query = "SELECT * FROM PriceQuote WHERE PriceQuoteID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, priceQuoteID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return PriceQuote.builder()
                        .priceQuoteID(rs.getInt("PriceQuoteID"))
                        .truckAmount(rs.getInt("TruckAmount"))
                        .staffAmount(rs.getInt("StaffAmount"))
                        .finalCost(rs.getBigDecimal("FinalCost"))
                        .priceCostID(rs.getInt("PriceCostID"))
                        .checkingFormID(rs.getInt("CheckingFormID"))
                        .status(rs.getString("Status"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPriceQuote(PriceQuote priceQuote) {
        String query = "INSERT INTO PriceQuote (TruckAmount, StaffAmount, FinalCost, PriceCostID, CheckingFormID, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, priceQuote.getTruckAmount());
            stmt.setInt(2, priceQuote.getStaffAmount());
            stmt.setBigDecimal(3, priceQuote.getFinalCost());
            stmt.setInt(4, priceQuote.getPriceCostID());
            stmt.setInt(5, priceQuote.getCheckingFormID());
            stmt.setString(6, "Pending"); // Default status
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePriceQuote(PriceQuote priceQuote) {
        String query = "UPDATE PriceQuote SET TruckAmount = ?, StaffAmount = ?, FinalCost = ?, PriceCostID = ?, CheckingFormID = ?, Status = ? WHERE PriceQuoteID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, priceQuote.getTruckAmount());
            stmt.setInt(2, priceQuote.getStaffAmount());
            stmt.setBigDecimal(3, priceQuote.getFinalCost());
            stmt.setInt(4, priceQuote.getPriceCostID());
            stmt.setInt(5, priceQuote.getCheckingFormID());
            stmt.setString(6, priceQuote.getStatus());
            stmt.setInt(7, priceQuote.getPriceQuoteID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePriceQuote(int priceQuoteID) {
        String query = "DELETE FROM PriceQuote WHERE PriceQuoteID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, priceQuoteID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal calculateFinalCost(int truckAmount, int staffAmount, int priceCostID) {
        // Get price costs from database
        String query = "SELECT TruckPrice, StaffPrice FROM PriceCost WHERE PriceCostID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, priceCostID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BigDecimal truckPrice = rs.getBigDecimal("TruckPrice");
                BigDecimal staffPrice = rs.getBigDecimal("StaffPrice");
                
                // Calculate final cost
                BigDecimal truckTotal = truckPrice.multiply(BigDecimal.valueOf(truckAmount));
                BigDecimal staffTotal = staffPrice.multiply(BigDecimal.valueOf(staffAmount));
                
                return truckTotal.add(staffTotal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
}
