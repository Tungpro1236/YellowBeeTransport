package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Staff;

public class StaffDAO extends DBContext {
    
    public Staff getStaffByUserID(int userId) throws SQLException {
        String sql = "SELECT s.*, u.FullName, u.Phone, u.Email, u.Address " +
                    "FROM Staff s " +
                    "JOIN Users u ON s.UserID = u.UserID " +
                    "WHERE s.UserID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToStaff(result);
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
    
    public List<Staff> getStaffByRole(int roleId) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT s.*, u.FullName, u.Phone, u.Email, u.Address " +
                    "FROM Staff s " +
                    "JOIN Users u ON s.UserID = u.UserID " +
                    "WHERE u.RoleID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, roleId);
            result = statement.executeQuery();
            
            while (result.next()) {
                staffList.add(mapResultSetToStaff(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return staffList;
    }
    
    private Staff mapResultSetToStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setStaffID(rs.getInt("StaffID"));
        staff.setUserID(rs.getInt("UserID"));
        staff.setFullName(rs.getString("FullName"));
        staff.setPhone(rs.getString("Phone"));
        staff.setEmail(rs.getString("Email"));
        staff.setAddress(rs.getString("Address"));
        staff.setStatus(rs.getString("Status"));
        return staff;
    }
} 