/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.TransportProblemForm;
import DBConnect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportProblemDAO {
    private Connection connection;

    public TransportProblemDAO() {
        try {
            DBContext db = new DBContext();
            connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TransportProblemForm> getAllTransportProblems() {
        List<TransportProblemForm> problemList = new ArrayList<>();
        String sql = "SELECT TPFID, ContractID, ProblemDescription, ProblemCost, StaffID, ReportDate, Status FROM TransportProblemForm";
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TransportProblemForm problem = new TransportProblemForm(
                    rs.getInt("TPFID"),
                    rs.getInt("ContractID"),
                    rs.getString("ProblemDescription"),
                    rs.getDouble("ProblemCost"),
                    rs.getInt("StaffID"),
                    rs.getTimestamp("ReportDate").toLocalDateTime(),
                    rs.getString("Status")
                );
                problemList.add(problem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return problemList;
    }
    
    public void updateProblemStatus(int tpfID, String status) {
        String sql = "UPDATE TransportProblemForm SET Status = ? WHERE TPFID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, tpfID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void applyCompensation(int contractID, double amount) {
        String sql = "UPDATE Contracts SET FinalCost = FinalCost - ? WHERE ContractID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, contractID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void cancelContract(int contractID) {
        String sql = "UPDATE Contracts SET ContractStatusID = 3 WHERE ContractID = ?"; // 3 = Hủy hợp đồng
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, contractID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
