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
                    rs.getInt("CheckingFormID"),
                    rs.getInt("UserID"),
                    rs.getString("Name"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getString("Address"),
                    rs.getTimestamp("CheckingTime"),
                    rs.getTimestamp("TransportTime"),
                    rs.getInt("ServiceID"),
                    rs.getString("Status"),
                    rs.getInt("StaffID")
                );
                list.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy CheckingForm theo ID
    public CheckingForm getCheckingFormByID(int checkingFormID) {
    CheckingForm form = null;
    String sql = "SELECT * FROM CheckingForm WHERE CheckingFormID = ?";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, checkingFormID);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            form = CheckingForm.builder()
                .checkingFormID(rs.getInt("CheckingFormID"))
                .userID(rs.getInt("UserID"))
                .name(rs.getString("Name"))
                .phone(rs.getString("Phone"))
                .email(rs.getString("Email"))
                .address(rs.getString("Address"))
                .checkingTime(rs.getTimestamp("CheckingTime"))
                .transportTime(rs.getTimestamp("TransportTime"))
                .serviceID(rs.getInt("ServiceID"))
                .status(rs.getString("Status"))
                .staffID(rs.getInt("StaffID"))
                .build();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return form;
}



    // Lấy danh sách nhân viên rảnh từ bảng Users
    public List<Staff> getAvailableSurveyStaff() {
    List<Staff> staffList = new ArrayList<>();
    String sql = "SELECT s.StaffID, u.FullName FROM Staff s "
               + "JOIN Users u ON s.UserID = u.UserID "
               + "WHERE u.RoleID = 1 AND s.IsAvailable = 1";
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Staff staff = new Staff(
                rs.getInt("StaffID"),
                0, 0, true, 1, // RoleID = 1 (Survey Staff)
                rs.getString("FullName"), "", "", null, null
            );
            staffList.add(staff);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return staffList;
}


    // Gán nhân viên cho CheckingForm
    public void assignStaffToCheckingForm(int checkingFormID, int staffID) {
    String updateCheckingForm = "UPDATE CheckingForm SET StaffID = ?, Status = 'Approved' WHERE CheckingFormID = ?";
    String updateStaffStatus = "UPDATE Staff SET IsAvailable = 0 WHERE StaffID = ?"; // Thêm cập nhật trạng thái

    try (PreparedStatement ps1 = connection.prepareStatement(updateCheckingForm);
         PreparedStatement ps2 = connection.prepareStatement(updateStaffStatus)) {

        // Cập nhật CheckingForm
        ps1.setInt(1, staffID);
        ps1.setInt(2, checkingFormID);
        ps1.executeUpdate();

        // Cập nhật trạng thái nhân viên
        ps2.setInt(1, staffID);
        ps2.executeUpdate();

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