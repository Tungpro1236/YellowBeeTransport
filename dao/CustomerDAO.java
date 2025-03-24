package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAO extends DBContext {
    
    public Customer getCustomerByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM Users WHERE Phone = ? AND RoleID = 5"; // 5 = Customer role
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToCustomer(result);
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
    
    public void createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Users (FullName, Phone, Email, Address, RoleID) "
                + "VALUES (?, ?, ?, ?, 5)"; // 5 = Customer role
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            
            statement.executeUpdate();
            
            // Get the generated UserID
            result = statement.getGeneratedKeys();
            if (result.next()) {
                customer.setUserID(result.getInt(1));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
    }
    
    public Customer getCustomerById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserID = ? AND RoleID = 5"; // 5 = Customer role
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToCustomer(result);
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
    
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setUserID(rs.getInt("UserID"));
        customer.setFullName(rs.getString("FullName"));
        customer.setPhone(rs.getString("Phone"));
        customer.setEmail(rs.getString("Email"));
        customer.setAddress(rs.getString("Address"));
        return customer;
    }
} 