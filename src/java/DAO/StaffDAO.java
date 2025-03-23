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
 * @author regio
 */
public class StaffDAO extends DBContext {

    // Lấy danh sách nhân viên khảo sát (Survey Staff)
    public List<Staff> getSurveyStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT s.StaffID, s.UserID, s.PriceCostID, u.Name " +
                       "FROM Staff s JOIN Users u ON s.UserID = u.UserID";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
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
}
