/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportDAO {
    private Connection connection;

    public ReportDAO() {
        try {
            DBContext db = new DBContext();
            connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Tính tổng doanh thu trong khoảng thời gian
    public double getTotalRevenue(String startDate, String endDate) {
        String sql = "SELECT SUM(FinalCost) AS TotalRevenue FROM Contracts WHERE ContractDate BETWEEN ? AND ?";
        double totalRevenue = 0;
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("TotalRevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRevenue;
    }
    
       public Map<String, Integer> getContractStatusCounts() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT cs.Description, COUNT(*) AS total " +
                     "FROM Contracts c " +
                     "JOIN ContractStatus cs ON c.ContractStatusID = cs.ContractStatusID " +
                     "GROUP BY cs.Description";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("Description"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public int getTotalTransportProblemsUnresolved() {
    int total = 0;
    String sql = "SELECT COUNT(*) AS total FROM TransportProblemForm WHERE Status = 'Pending'";
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            total = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

    public int getTotalCheckingFormsByStatus(String status) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM CheckingForm WHERE Status = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public int getTotalContractsByStatus(String status) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total " +
                     "FROM Contracts c " +
                     "JOIN ContractStatus cs ON c.ContractStatusID = cs.ContractStatusID " +
                     "WHERE cs.Description = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
