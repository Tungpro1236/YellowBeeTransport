package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.PriceQuote;

public class PriceQuoteDAO extends DBContext {
    
    public void createPriceQuote(PriceQuote quote) throws SQLException {
        String sql = "INSERT INTO PriceQuotes (CheckingFormID, StaffID, BasePrice, AdditionalFees, " +
                    "TotalPrice, Notes, CreatedAt, Status) VALUES (?, ?, ?, ?, ?, ?, GETDATE(), 'pending')";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, quote.getCheckingFormID());
            statement.setInt(2, quote.getStaffID());
            statement.setDouble(3, quote.getBasePrice());
            statement.setDouble(4, quote.getAdditionalFees());
            statement.setDouble(5, quote.getTotalPrice());
            statement.setString(6, quote.getNotes());
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public PriceQuote getPriceQuoteByCheckingForm(int checkingFormId) throws SQLException {
        String sql = "SELECT pq.*, s.FullName as StaffName, cf.CustomerName, sv.ServiceName " +
                    "FROM PriceQuotes pq " +
                    "JOIN Staff s ON pq.StaffID = s.StaffID " +
                    "JOIN CheckingForms cf ON pq.CheckingFormID = cf.CheckingFormID " +
                    "JOIN Services sv ON cf.ServiceID = sv.ServiceID " +
                    "WHERE pq.CheckingFormID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, checkingFormId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToPriceQuote(result);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return null;
    }
    
    public void updateStatus(int priceQuoteId, String status) throws SQLException {
        String sql = "UPDATE PriceQuotes SET Status = ? WHERE PriceQuoteID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, priceQuoteId);
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    private PriceQuote mapResultSetToPriceQuote(ResultSet rs) throws SQLException {
        PriceQuote quote = new PriceQuote();
        quote.setPriceQuoteID(rs.getInt("PriceQuoteID"));
        quote.setCheckingFormID(rs.getInt("CheckingFormID"));
        quote.setStaffID(rs.getInt("StaffID"));
        quote.setBasePrice(rs.getDouble("BasePrice"));
        quote.setAdditionalFees(rs.getDouble("AdditionalFees"));
        quote.setTotalPrice(rs.getDouble("TotalPrice"));
        quote.setNotes(rs.getString("Notes"));
        quote.setCreatedAt(rs.getTimestamp("CreatedAt"));
        quote.setStatus(rs.getString("Status"));
        
        // Additional display fields
        quote.setStaffName(rs.getString("StaffName"));
        quote.setCustomerName(rs.getString("CustomerName"));
        quote.setServiceName(rs.getString("ServiceName"));
        
        return quote;
    }
} 