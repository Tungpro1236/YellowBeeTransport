package dao;

import model.TransportProblem;
import DBConnect.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportProblemDAO extends DBContext {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public TransportProblemDAO() {
        super();
        this.connection = super.connection;
    }
    
    public void createTransportProblem(TransportProblem problem) throws SQLException {
        String sql = "INSERT INTO TransportProblemForm (ContractID, ProblemDescription, ProblemCost, StaffID, CheckingTime, TransportTime) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, problem.getContractID());
            statement.setString(2, problem.getProblemDescription());
            statement.setDouble(3, problem.getProblemCost());
            statement.setInt(4, problem.getStaffID());
            statement.setTimestamp(5, problem.getCheckingTime());
            statement.setTimestamp(6, problem.getTransportTime());
            
            statement.executeUpdate();
            
            result = statement.getGeneratedKeys();
            if (result.next()) {
                problem.setTpfID(result.getInt(1));
            }
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
        }
    }
    
    public void resolveTransportProblem(int tpfId, int contractId) throws SQLException {
        // First update the transport problem status
        String updateProblemSql = "UPDATE TransportProblemForm SET Status = 'Resolved' WHERE TPFID = ?";
        try {
            statement = connection.prepareStatement(updateProblemSql);
            statement.setInt(1, tpfId);
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }
        
        // Then update the contract status to completed
        String updateContractSql = "UPDATE Contract SET Status = 2 WHERE ContractID = ?";
        try {
            statement = connection.prepareStatement(updateContractSql);
            statement.setInt(1, contractId);
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }
    }
    
    public List<TransportProblem> getProblemsByContractId(int contractId) throws SQLException {
        List<TransportProblem> problems = new ArrayList<>();
        String sql = "SELECT * FROM TransportProblemForm WHERE ContractID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, contractId);
            result = statement.executeQuery();
            
            while (result.next()) {
                TransportProblem problem = new TransportProblem(
                    result.getInt("TPFID"),
                    result.getInt("ContractID"),
                    result.getString("ProblemDescription"),
                    result.getDouble("ProblemCost"),
                    result.getInt("StaffID")
                );
                problem.setCheckingTime(result.getTimestamp("CheckingTime"));
                problem.setTransportTime(result.getTimestamp("TransportTime"));
                problems.add(problem);
            }
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
        }
        return problems;
    }
    
    public List<TransportProblem> getProblemsByStaffId(int staffId) throws SQLException {
        List<TransportProblem> problems = new ArrayList<>();
        String sql = "SELECT * FROM TransportProblemForm WHERE StaffID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            result = statement.executeQuery();
            
            while (result.next()) {
                TransportProblem problem = new TransportProblem(
                    result.getInt("TPFID"),
                    result.getInt("ContractID"),
                    result.getString("ProblemDescription"),
                    result.getDouble("ProblemCost"),
                    result.getInt("StaffID")
                );
                problem.setCheckingTime(result.getTimestamp("CheckingTime"));
                problem.setTransportTime(result.getTimestamp("TransportTime"));
                problems.add(problem);
            }
        } finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
        }
        return problems;
    }
    
    public void updateTransportProblem(TransportProblem problem) throws SQLException {
        String sql = "UPDATE TransportProblemForm SET ProblemDescription = ?, ProblemCost = ?, CheckingTime = ?, TransportTime = ? WHERE TPFID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, problem.getProblemDescription());
            statement.setDouble(2, problem.getProblemCost());
            statement.setTimestamp(3, problem.getCheckingTime());
            statement.setTimestamp(4, problem.getTransportTime());
            statement.setInt(5, problem.getTpfID());
            
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }
    }
    
    public void deleteTransportProblem(int tpfId) throws SQLException {
        String sql = "DELETE FROM TransportProblemForm WHERE TPFID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, tpfId);
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }
    }
} 