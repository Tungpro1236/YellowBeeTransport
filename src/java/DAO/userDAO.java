/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author regio
 */
public class UserDAO extends DBContext {

    public boolean isUsernameOrEmailTaken(String username, String email) {
        String query = "SELECT a.AccountID FROM Accounts a JOIN Users u "
                + "ON a.AccountID = u.AccountID WHERE a.Username = ? OR u.Email = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            result = statement.executeQuery();
            return result.next(); // Trả về true nếu tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int createAccount(String username, String password, int accountStatusID) {
        String query = "INSERT INTO Accounts (Username, Password, AccountStatusID) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, accountStatusID);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Lấy AccountID vừa tạo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean createUser(String fullName, String email, String phone, String address, int genderID, int accountID, int roleID) {
        String query = "INSERT INTO Users (FullName, Email, Phone, Address, GenderID, AccountID, RoleID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, genderID);
            ps.setInt(6, accountID);
            ps.setInt(7, roleID);
            return ps.executeUpdate() > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, "
                + "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, "
                + "u.RoleID, r.RoleName "
                + "FROM Users u "
                + "JOIN Accounts a ON u.AccountID = a.AccountID "
                + "JOIN Roles r ON u.RoleID = r.RoleID";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = User.builder()
                        .userId(resultSet.getInt("UserID"))
                        .fullName(resultSet.getString("FullName"))
                        .email(resultSet.getString("Email"))
                        .phone(resultSet.getString("Phone"))
                        .address(resultSet.getString("Address"))
                        .genderId(resultSet.getInt("GenderID"))
                        .image(resultSet.getString("Image"))
                        .accountId(resultSet.getInt("AccountID"))
                        .roleId(resultSet.getInt("RoleID"))
                        .roleName(resultSet.getString("RoleName"))
                        .build();

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, "
                + "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, "
                + "u.RoleID, r.RoleName "
                + "FROM Users u "
                + "JOIN Accounts a ON u.AccountID = a.AccountID "
                + "JOIN Roles r ON u.RoleID = r.RoleID "
                + "WHERE u.UserID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = User.builder()
                        .userId(resultSet.getInt("UserID"))
                        .fullName(resultSet.getString("FullName"))
                        .email(resultSet.getString("Email"))
                        .phone(resultSet.getString("Phone"))
                        .address(resultSet.getString("Address"))
                        .genderId(resultSet.getInt("GenderID"))
                        .image(resultSet.getString("Image"))
                        .accountId(resultSet.getInt("AccountID"))
                        .roleId(resultSet.getInt("RoleID"))
                        .roleName(resultSet.getString("RoleName"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
