/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DBConnect.DBContext;

/**
 *
 * @author Admin
 */
public class StaffDAO extends DBContext {

    // Lấy danh sách nhân viên khảo sát (Survey Staff)
    public List<Staff> getSurveyStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT s.StaffID, s.UserID, s.PriceCostID, u.Name "
                + "FROM Staff s JOIN Users u ON s.UserID = u.UserID";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"),
                        rs.getInt("UserID"),
                        rs.getInt("PriceCostID"),
                        rs.getString("Name")
                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public Staff getStaffByUserID(int userId) throws SQLException {
        String sql = "SELECT s.*, u.FullName, u.Phone, u.Email, u.Address "
                + "FROM Staff s "
                + "JOIN Users u ON s.UserID = u.UserID "
                + "WHERE s.UserID = ?";

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

    private Staff mapResultSetToStaff(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setStaffID(rs.getInt("StaffID"));
        staff.setUserID(rs.getInt("UserID"));
        staff.setFullName(rs.getString("FullName"));
        staff.setPhone(rs.getString("Phone"));
        staff.setEmail(rs.getString("Email"));
        //.setAddress(rs.getString("Address"));
        //staff.setStatus(rs.getString("Status"));
        return staff;
    }
}
