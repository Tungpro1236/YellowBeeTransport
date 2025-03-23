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

    public boolean updateStatus(int priceQuoteID, String status) {
        String sql = "UPDATE PriceQuotes SET Status = ? WHERE PriceQuoteID = ?";

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
    
    public PriceQuote getPriceQuoteByID(int priceQuoteID) {
    PriceQuote priceQuote = null;
    String sql = "SELECT priceQuoteID, checkingFormID, truckAmount, staffAmount, finalCost " +
                 "FROM PriceQuote WHERE priceQuoteID = ?";
    
    try (Connection conn = new DBContext().connection;
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, priceQuoteID);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            priceQuote = new PriceQuote();
            priceQuote.setPriceQuoteID(rs.getInt("priceQuoteID"));
            priceQuote.setTruckAmount(rs.getInt("truckAmount"));
            priceQuote.setStaffAmount(rs.getInt("staffAmount"));
            priceQuote.setFinalCost(rs.getDouble("finalCost"));
            priceQuote.setPriceCostID(rs.getInt("priceCostID"));
            priceQuote.setCheckingFormID(rs.getInt("checkingFormID"));
            priceQuote.setStatus(rs.getString("status"));
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return priceQuote;
}
}
