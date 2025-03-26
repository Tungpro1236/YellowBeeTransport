package DAO;

import DBConnect.DBContext;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, \n"
                + "       u.GenderID, u.Image, u.AccountID, a.Username, a.Password, \n"
                + "       u.RoleID, r.RoleName \n"
                + "FROM Users u \n"
                + "JOIN Accounts a ON u.AccountID = a.AccountID \n"
                + "JOIN Roles r ON u.RoleID = r.RoleID \n"
                + "WHERE a.AccountStatusID = 1;";

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

    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET FullName=?, Phone=?, Address=?, GenderID=? WHERE UserID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getGenderId());
            ps.setInt(5, user.getUserId());

            int rowsUpdated = ps.executeUpdate();
            System.out.println("🔥 updateUser() - Rows affected: " + rowsUpdated);
            return rowsUpdated > 0; // Trả về true nếu update thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Lỗi thì trả về false
        }
    }

    public List<User> searchUserByName(String name) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, "
                + "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, "
                + "u.RoleID, r.RoleName "
                + "FROM Users u "
                + "JOIN Accounts a ON u.AccountID = a.AccountID "
                + "JOIN Roles r ON u.RoleID = r.RoleID "
                + "WHERE u.FullName LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");  // Tìm kiếm gần đúng theo tên
            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> getUsersByRole(int roleId) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT u.UserID, u.FullName, u.Email, u.Phone, u.Address, "
                + "u.GenderID, u.Image, u.AccountID, a.Username, a.Password, "
                + "u.RoleID, r.RoleName "
                + "FROM Users u "
                + "JOIN Accounts a ON u.AccountID = a.AccountID "
                + "JOIN Roles r ON u.RoleID = r.RoleID "
                + "WHERE u.RoleID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roleId);

            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean addUser(String fullName, String email, String phone, String address, int genderId,
            String image, int accountId, int roleId) {
        String query = "INSERT INTO Users (FullName, Email, Phone, Address, GenderID, Image, AccountID, RoleID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setInt(5, genderId);
            statement.setString(6, image);
            statement.setInt(7, accountId);
            statement.setInt(8, roleId);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu có hàng được thêm
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm người dùng: " + e.getMessage());
            return false;
        }
    }

    public Map<String, Integer> getUserCountByRole() {
        Map<String, Integer> roleCountMap = new HashMap<>();
        String query = "SELECT r.RoleName, COUNT(*) AS UserCount \n"
                + "               FROM Users u \n"
                + "               JOIN Roles r ON u.RoleID = r.RoleID \n"
                + "               JOIN Accounts a ON u.AccountID = a.AccountID\n"
                + "               WHERE a.AccountStatusID = 1\n"
                + "               GROUP BY r.RoleName";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                roleCountMap.put(resultSet.getString("RoleName"), resultSet.getInt("UserCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleCountMap;
    }

    // Đếm tổng số user
    public int getTotalUserCount() {
        String query = "SELECT COUNT(*) AS Total FROM Users u\n"
                + "               JOIN Accounts a ON u.AccountID = a.AccountID\n"
                + "               WHERE a.AccountStatusID = 1;";
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("Total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isMailExist(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneExist(String phone) {
        String sql = "SELECT COUNT(*) FROM Users WHERE Phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getUserIdByAccountId(int accountId) {
        String query = "SELECT UserID FROM Users WHERE AccountID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("userId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
