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
    
    // Lấy số lượng hợp đồng theo trạng thái
    public Map<String, Integer> getContractStatusCounts() {
        Map<String, Integer> statusCounts = new HashMap<>();
        String sql = "SELECT ContractStatus, COUNT(*) AS Count FROM Contracts GROUP BY ContractStatus";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                statusCounts.put(rs.getString("ContractStatus"), rs.getInt("Count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusCounts;
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
    
    // Lấy số lượng vấn đề phát sinh trong quá trình vận chuyển
    public int getTotalTransportProblems() {
        String sql = "SELECT COUNT(*) AS TotalProblems FROM TransportProblemForm";
        int totalProblems = 0;
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalProblems = rs.getInt("TotalProblems");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProblems;
    }
    
    // Lấy nhân viên xử lý nhiều vấn đề nhất
    public Map<Integer, Integer> getTopProblemSolvers() {
        Map<Integer, Integer> topSolvers = new HashMap<>();
        String sql = "SELECT StaffID, COUNT(*) AS ProblemCount FROM TransportProblemForm GROUP BY StaffID ORDER BY ProblemCount DESC LIMIT 5";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                topSolvers.put(rs.getInt("StaffID"), rs.getInt("ProblemCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topSolvers;
    }
}
