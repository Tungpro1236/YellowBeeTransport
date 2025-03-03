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
public class userDAO extends DBContext {

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

    // Thêm người dùng mới
    public boolean createUser(String username, String email, String phone, int genderId, String address, String password) {
        String accountQuery = "INSERT INTO Accounts (Username,"
                + " Password, AccountStatusID) VALUES (?, ?, 1)";
        String userQuery = "INSERT INTO Users (Email, Phone, "
                + "Address, GenderID, AccountID) VALUES (?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(accountQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            // Lấy AccountID vừa tạo
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int accountId = -1;
            if (generatedKeys.next()) {
                accountId = generatedKeys.getInt(1);
            } else {
                return false;
            }

            // Thêm vào bảng Users
            statement = connection.prepareStatement(userQuery);
            statement.setString(1, email);
            statement.setString(2, phone);
            statement.setString(3, address);
            statement.setInt(4, genderId);
            statement.setInt(5, accountId);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, " +
                       "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, " +
                       "u.RoleID, r.RoleName " +
                       "FROM Users u " +
                       "JOIN Accounts a ON u.AccountID = a.AccountID " +
                       "JOIN Roles r ON u.RoleID = r.RoleID";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

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
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, " +
                       "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, " +
                       "u.RoleID, r.RoleName " +
                       "FROM Users u " +
                       "JOIN Accounts a ON u.AccountID = a.AccountID " +
                       "JOIN Roles r ON u.RoleID = r.RoleID " +
                       "WHERE u.UserID = ?";

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
