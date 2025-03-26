package dao;

import model.PriceQuote;
import util.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceQuoteDAO {
    
    public List<PriceQuote> getAllPriceQuotes() throws SQLException, ClassNotFoundException {
        List<PriceQuote> priceQuotes = new ArrayList<>();
        String sql = "SELECT * FROM PriceQuote";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PriceQuote priceQuote = new PriceQuote(
                    rs.getInt("PriceQuoteID"),
                    rs.getInt("TruckAmount"),
                    rs.getInt("StaffAmount"),
                    rs.getDouble("FinalCost"),
                    rs.getInt("PriceCostID"),
                    rs.getInt("CheckingFormID")
                );
                priceQuotes.add(priceQuote);
            }
        }
        return priceQuotes;
    }
    
    public PriceQuote getPriceQuoteById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM PriceQuote WHERE PriceQuoteID = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PriceQuote(
                        rs.getInt("PriceQuoteID"),
                        rs.getInt("TruckAmount"),
                        rs.getInt("StaffAmount"),
                        rs.getDouble("FinalCost"),
                        rs.getInt("PriceCostID"),
                        rs.getInt("CheckingFormID")
                    );
                }
            }
        }
        return null;
    }
    
    public void createPriceQuote(PriceQuote priceQuote) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO PriceQuote (TruckAmount, StaffAmount, FinalCost, PriceCostID, CheckingFormID) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, priceQuote.getTruckAmount());
            ps.setInt(2, priceQuote.getStaffAmount());
            ps.setDouble(3, priceQuote.getFinalCost());
            ps.setInt(4, priceQuote.getPriceCostID());
            ps.setInt(5, priceQuote.getCheckingFormID());
            
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    priceQuote.setPriceQuoteID(generatedKeys.getInt(1));
                }
            }
        }
    }
    
    public void updatePriceQuote(PriceQuote priceQuote) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE PriceQuote SET TruckAmount = ?, StaffAmount = ?, FinalCost = ?, PriceCostID = ?, CheckingFormID = ? WHERE PriceQuoteID = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, priceQuote.getTruckAmount());
            ps.setInt(2, priceQuote.getStaffAmount());
            ps.setDouble(3, priceQuote.getFinalCost());
            ps.setInt(4, priceQuote.getPriceCostID());
            ps.setInt(5, priceQuote.getCheckingFormID());
            ps.setInt(6, priceQuote.getPriceQuoteID());
            
            ps.executeUpdate();
        }
    }
    
    public void deletePriceQuote(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM PriceQuote WHERE PriceQuoteID = ?";
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public double calculateFinalCost(int truckAmount, int staffAmount, int priceCostID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Cost FROM PriceCost WHERE PriceCostID = ?";
        double baseCost = 0;
        
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, priceCostID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    baseCost = rs.getDouble("Cost");
                }
            }
        }
        
        return (baseCost * truckAmount) + (500000 * staffAmount); // 500,000 VND per staff
    }
} 