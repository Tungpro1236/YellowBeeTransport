/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Admin
 */
import Model.PriceQuote;
import DBConnect.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class PriceQuoteDAO {

    public List<PriceQuote> getAllPriceQuotes() throws SQLException, ClassNotFoundException {
        List<PriceQuote> priceQuotes = new ArrayList<>();
        String sql = "SELECT * FROM PriceQuote";

        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PriceQuote priceQuote = new PriceQuote(
                        resultSet.getInt("PriceQuoteID"),
                        resultSet.getInt("TruckAmount"),
                        resultSet.getInt("StaffAmount"),
                        resultSet.getBigDecimal("FinalCost"),
                        resultSet.getInt("PriceCostID"),
                        resultSet.getInt("CheckingFormID")
                );
                priceQuotes.add(priceQuote);
            }
        }
        return priceQuotes;
    }

    public PriceQuote getPriceQuoteById(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM PriceQuote WHERE PriceQuoteID = ?";

        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new PriceQuote(
                            rs.getInt("PriceQuoteID"),
                            rs.getInt("TruckAmount"),
                            rs.getInt("StaffAmount"),
                            rs.getBigDecimal("FinalCost"),
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
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, priceQuote.getTruckAmount());
            statement.setInt(2, priceQuote.getStaffAmount());
            statement.setBigDecimal(3, priceQuote.getFinalCost());
            statement.setInt(4, priceQuote.getPriceCostID());
            statement.setInt(5, priceQuote.getCheckingFormID());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    priceQuote.setPriceQuoteID(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void updatePriceQuote(PriceQuote priceQuote) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE PriceQuote SET TruckAmount = ?, StaffAmount = ?, FinalCost = ?, PriceCostID = ?, CheckingFormID = ? WHERE PriceQuoteID = ?";

        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, priceQuote.getTruckAmount());
            statement.setInt(2, priceQuote.getStaffAmount());
            statement.setBigDecimal(3, priceQuote.getFinalCost());
            statement.setInt(4, priceQuote.getPriceCostID());
            statement.setInt(5, priceQuote.getCheckingFormID());
            statement.setInt(6, priceQuote.getPriceQuoteID());

            statement.executeUpdate();
        }
    }

    public void deletePriceQuote(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM PriceQuote WHERE PriceQuoteID = ?";

        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public BigDecimal calculateFinalCost(int truckAmount, int staffAmount, int priceCostID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Cost FROM PriceCost WHERE PriceCostID = ?";
        BigDecimal baseCost = BigDecimal.ZERO;
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.connection; PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, priceCostID);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    baseCost = rs.getBigDecimal("Cost");
                }
            }
        }

        BigDecimal staffCost = new BigDecimal("500000").multiply(new BigDecimal(staffAmount));
        return baseCost.multiply(new BigDecimal(truckAmount)).add(staffCost);
    }
}
