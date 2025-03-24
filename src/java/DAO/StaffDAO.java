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
    
    public List<Staff> getAllSurveyStaff() {
    List<Staff> staffList = new ArrayList<>();
    String query = "SELECT s.StaffID, u.FullName, u.Phone, u.Email, s.IsAvailable, "
                 + "(SELECT TOP 1 c.ContractID FROM ArrangeStaff ast "
                 + "JOIN Contracts c ON ast.ContractID = c.ContractID "
                 + "WHERE ast.StaffID = s.StaffID ORDER BY c.ContractDate DESC) AS CurrentContractID, "
                 + "(SELECT TOP 1 cf.CheckingFormID FROM CheckingForm cf "
                 + "WHERE cf.StaffID = s.StaffID ORDER BY cf.CheckingTime DESC) AS CurrentCheckingFormID "
                 + "FROM Staff s "
                 + "JOIN Users u ON s.UserID = u.UserID "
                 + "WHERE u.RoleID = 1";

    try (PreparedStatement statement = connection.prepareStatement(query);
         ResultSet result = statement.executeQuery()) {
        while (result.next()) {
            Integer currentContractID = result.getObject("CurrentContractID") != null ? result.getInt("CurrentContractID") : null;
            Integer currentCheckingFormID = result.getObject("CurrentCheckingFormID") != null ? result.getInt("CurrentCheckingFormID") : null;

            Staff staff = new Staff(
                result.getInt("StaffID"),
                0, // userID không cần thiết
                0, // priceCostID không cần thiết
                result.getBoolean("IsAvailable"),
                2, // RoleID = 2 cho Moving Staff
                result.getString("FullName"),
                result.getString("Phone"),
                result.getString("Email"),
                currentContractID,
                currentCheckingFormID
            );
            staffList.add(staff);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return staffList;
}
    
    public List<Staff> getAllMovingStaff() {
    List<Staff> movingStaffList = new ArrayList<>();
    String query = "SELECT s.StaffID, u.FullName, u.Phone, u.Email, s.IsAvailable, "
                 + "(SELECT TOP 1 c.ContractID FROM ArrangeStaff ast "
                 + "JOIN Contracts c ON ast.ContractID = c.ContractID "
                 + "WHERE ast.StaffID = s.StaffID ORDER BY c.ContractDate DESC) AS CurrentContractID, "
                 + "(SELECT TOP 1 cf.CheckingFormID FROM CheckingForm cf "
                 + "WHERE cf.StaffID = s.StaffID ORDER BY cf.CheckingTime DESC) AS CurrentCheckingFormID "
                 + "FROM Staff s "
                 + "JOIN Users u ON s.UserID = u.UserID "
                 + "WHERE u.RoleID = 2";

    try (PreparedStatement statement = connection.prepareStatement(query);
         ResultSet result = statement.executeQuery()) {
        while (result.next()) {
            Integer currentContractID = result.getObject("CurrentContractID") != null ? result.getInt("CurrentContractID") : null;
            Integer currentCheckingFormID = result.getObject("CurrentCheckingFormID") != null ? result.getInt("CurrentCheckingFormID") : null;

            Staff staff = new Staff(
                result.getInt("StaffID"),
                0, // userID không cần thiết trong DAO này
                0, // priceCostID không cần thiết trong DAO này
                result.getBoolean("IsAvailable"),
                2, // RoleID = 2 cho Moving Staff
                result.getString("FullName"),
                result.getString("Phone"),
                result.getString("Email"),
                currentContractID,
                currentCheckingFormID
            );
            movingStaffList.add(staff);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return movingStaffList;
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
                + "AND NOT EXISTS (SELECT 1 FROM CheckingForm cf WHERE cf.AstaffID = u.UserID)";
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

    public List<Staff> getAvailableMovingStaff() {
    List<Staff> staffList = new ArrayList<>();
    String sql = "SELECT s.StaffID, u.UserID, u.FullName, u.Phone, u.Email " +
                 "FROM Staff s " +
                 "JOIN Users u ON s.UserID = u.UserID " +  // Liên kết bảng Users để lấy thông tin nhân viên
                 "WHERE u.RoleID = 2 " +  // Chỉ lấy nhân viên có RoleID = 2
                 "AND s.IsAvailable = 1";  // Chỉ lấy nhân viên đang rảnh

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Staff staff = new Staff(
                rs.getInt("StaffID"),  // Lấy StaffID
                rs.getInt("UserID"),
                0,  // Không cần PriceCostID
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
