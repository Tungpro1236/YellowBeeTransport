/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.CheckingForm;
import Model.Staff;

public class CheckingFormDAO {

    private final Connection connection;

    public CheckingFormDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy danh sách CheckingForm theo trạng thái
    public List<CheckingForm> getCheckingFormsByStatus(String status) {
        List<CheckingForm> list = new ArrayList<>();
        String sql = "SELECT * FROM CheckingForm WHERE Status = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CheckingForm form = new CheckingForm(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getTimestamp("checkingTime"),
                        rs.getTimestamp("transportTime"),
                        rs.getInt("serviceId"),
                        rs.getInt("staffId"),
                        rs.getString("status"),
                        rs.getInt("checkingFormID"),
                        rs.getInt("customerID")
                );
                list.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách nhân viên rảnh từ bảng Users
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

    // Gán nhân viên cho CheckingForm
    public void assignStaffToCheckingForm(int checkingFormID, int staffID) {
        String sql = "UPDATE CheckingForm SET AssignedStaffID = ?, Status = 'Approved' WHERE CheckingFormID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, staffID);
            ps.setInt(2, checkingFormID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật trạng thái CheckingForm
    public void updateCheckingFormStatus(int checkingFormID, String status) {
        String sql = "UPDATE CheckingForm SET Status = ? WHERE CheckingFormID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, checkingFormID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
