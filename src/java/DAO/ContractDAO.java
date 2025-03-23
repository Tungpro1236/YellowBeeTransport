/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Admin
 */
import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Contract;
import Model.Customer;
import Model.Service;
import Model.Staff;
import jdk.jfr.Timestamp;

public class ContractDAO extends DBContext {
    
    public List<Contract> getContractsByStaffId(int staffId) throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.*, u.FullName as CustomerName, u.Phone as CustomerPhone, " +
                    "s.ServiceName, st.FullName as StaffName " +
                    "FROM Contracts c " +
                    "JOIN Users u ON c.CustomerID = u.UserID " +
                    "JOIN Services s ON c.ServiceID = s.ServiceID " +
                    "JOIN Staff st ON c.StaffID = st.StaffID " +
                    "WHERE c.StaffID = ? " +
                    "ORDER BY c.TransportTime DESC";
        
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
    
    public Contract getContractById(int contractId) throws SQLException {
        String sql = "SELECT c.*, u.FullName as CustomerName, u.Phone as CustomerPhone, " +
                    "s.ServiceName, st.FullName as StaffName " +
                    "FROM Contracts c " +
                    "JOIN Users u ON c.CustomerID = u.UserID " +
                    "JOIN Services s ON c.ServiceID = s.ServiceID " +
                    "JOIN Staff st ON c.StaffID = st.StaffID " +
                    "WHERE c.ContractID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contractId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToContract(result);
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
    
    public void updateContractStatus(int contractId, String status) throws SQLException {
        String sql = "UPDATE Contracts SET Status = ? WHERE ContractID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, contractId);
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    private Contract mapResultSetToContract(ResultSet rs) throws SQLException {
        Contract contract = new Contract();
        contract.setContractID(rs.getInt("ContractID"));
        contract.setCustomerID(rs.getInt("CustomerID"));
        contract.setServiceID(rs.getInt("ServiceID"));
        contract.setStaffID(rs.getInt("StaffID"));
        contract.setTransportTime((Timestamp) rs.getTimestamp("TransportTime"));
        contract.setFinalCost(rs.getDouble("FinalCost"));
        contract.setStatus(rs.getString("Status"));
        contract.setCustomerName(rs.getString("CustomerName"));
        contract.setCustomerPhone(rs.getString("CustomerPhone"));
        contract.setServiceName(rs.getString("ServiceName"));
        contract.setStaffName(rs.getString("StaffName"));
        return contract;
    }
} 
