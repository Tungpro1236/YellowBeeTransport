/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Staff;
import DBConnect.DBContext;

/**
 *
 * @author regio & admin
 */
public class StaffDAO extends DBContext {

    private Connection connection;

    public StaffDAO() {
        try {
            DBContext db = new DBContext();
            connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    // Lấy toàn bộ danh sách nhân viên từ bảng Users
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT u.UserID, u.FullName, u.Phone, u.Email, u.RoleID "
                + "FROM Users u "
                + "WHERE u.RoleID IN (1, 2)"; // Chỉ lấy nhân viên
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        0, // Không cần StaffID
                        rs.getInt("UserID"),
                        0, // Không cần PriceCostID
                        true
                );
                staff.setFullName(rs.getString("FullName"));
                staff.setPhone(rs.getString("Phone"));
                staff.setEmail(rs.getString("Email"));
                staff.setRoleID(rs.getInt("RoleID"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Lấy danh sách nhân viên đang rảnh và thuộc nhóm SurveyStaff
    public List<Staff> getAvailableStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT u.UserID, u.FullName, u.Phone, u.Email "
                + "FROM Users u "
                + "WHERE u.RoleID = 1 "
                + "AND NOT EXISTS (SELECT 1 FROM CheckingForm cf WHERE cf.AssignedStaffID = u.UserID)";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        0,
                        rs.getInt("UserID"),
                        0,
                        true
                );
                staff.setFullName(rs.getString("FullName"));
                staff.setPhone(rs.getString("Phone"));
                staff.setEmail(rs.getString("Email"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Lấy danh sách nhân viên đang có hợp đồng
    public List<Staff> getStaffInContracts() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT u.UserID, u.FullName, u.Phone, u.Email, c.ContractID "
                + "FROM Users u "
                + "JOIN Contracts c ON u.UserID = c.StaffID "
                + "WHERE u.RoleID IN (1, 2)";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        0,
                        rs.getInt("UserID"),
                        0,
                        false
                );
                staff.setFullName(rs.getString("FullName"));
                staff.setPhone(rs.getString("Phone"));
                staff.setEmail(rs.getString("Email"));
                staff.setCurrentContractID(rs.getInt("ContractID"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Lấy danh sách nhân viên đang khảo sát CheckingForm
    public List<Staff> getStaffInCheckingForms() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT u.UserID, u.FullName, u.Phone, u.Email, cf.CheckingFormID "
                + "FROM Users u "
                + "JOIN CheckingForm cf ON u.UserID = cf.StaffID "
                + "WHERE u.RoleID = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        0,
                        rs.getInt("UserID"),
                        0,
                        false
                );
                staff.setFullName(rs.getString("FullName"));
                staff.setPhone(rs.getString("Phone"));
                staff.setEmail(rs.getString("Email"));
                staff.setCurrentCheckingFormID(rs.getInt("CheckingFormID"));
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
        Staff staff = new Staff(rs.getInt("StaffID"),
                rs.getInt("UserID"), 
                rs.getInt("priceCostID"),
                true, rs.getInt("roleID"), 
                rs.getString("FullName"), 
                rs.getString("Phone"), 
                rs.getString("Email"), 
                Integer.MIN_VALUE, 
                Integer.MIN_VALUE);


        return staff;
    }

    public List<Staff> getAvailableMovingStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT s.StaffID, u.UserID, u.FullName, u.Phone, u.Email "
                + "FROM Staff s "
                + "JOIN Users u ON s.UserID = u.UserID "
                + // Liên kết bảng Users để lấy thông tin nhân viên
                "WHERE u.RoleID = 2 "
                + // Chỉ lấy nhân viên có RoleID = 2
                "AND s.IsAvailable = 1";  // Chỉ lấy nhân viên đang rảnh

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"), // Lấy StaffID
                        rs.getInt("UserID"),
                        0, // Không cần PriceCostID
                        true
                );
                staff.setFullName(rs.getString("FullName"));
                staff.setPhone(rs.getString("Phone"));
                staff.setEmail(rs.getString("Email"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

}
