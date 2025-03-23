/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Staff;
import DBConnect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    private Connection connection;

    public StaffDAO() {
        try {
            DBContext db = new DBContext();
            connection = db.connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
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

// Lấy danh sách MovingStaff có RoleID = 2 và chưa có hợp đồng
public List<Staff> getAvailableMovingStaffForContract(int limit) {
    List<Staff> staffList = new ArrayList<>();
    String sql = "SELECT TOP " + limit + " u.UserID, u.FullName, u.Phone, u.Email "
               + "FROM Users u "
               + "WHERE u.RoleID = 2 "
               + "AND NOT EXISTS (SELECT 1 FROM Contracts c WHERE c.StaffID = u.UserID)";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
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
            staffList.add(staff);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return staffList;
}

}
