
package DAO;

import DBConnect.DBContext;
import Model.Accounts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountsDAO extends DBContext {

    public Accounts getAccount(String username, String password) {
        Accounts accounts = null;
        String query = "select * from Accounts\n"
                + "where Username = ? and Password = ? and AccountStatusID =1";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            //Attempt to get user information from database
            while (result.next()) {
                int AccountID = result.getInt("AccountID");
                String Username = result.getString("Username");
                String Password = result.getString("Password");
                int AccountStatusID = result.getInt("AccountStatusID");

                accounts = Accounts.builder().
                        AccountID(AccountID).
                        Username(Username).
                        Password(Password).
                        AccountStatusID(AccountStatusID)
                        .build();
            }
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRoleByUsernameAndPassword(String username, String password) {
        String role = null;
        String query = "SELECT r.RoleName "
                + "FROM Accounts a "
                + "JOIN Users u ON a.AccountID = u.AccountID "
                + "JOIN Roles r ON u.RoleID = r.RoleID "
                + "WHERE a.Username = ? AND a.Password = ? AND a.AccountStatusID = 1";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password); // Chỉnh sửa lỗi đặt trùng index
            result = statement.executeQuery();

            if (result.next()) {
                role = result.getString("RoleName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng ServicesDAO
        AccountsDAO aDAO = new AccountsDAO();

        Accounts acc = aDAO.getAccount("userI", "password123");

        // In kết quả để kiểm tra
        if (acc != null) {
            System.out.println("Service ID: " + acc);
        } else {
            System.out.println("Không tìm thấy dịch vụ với ID: ");
        }
    }

    public Accounts getAccountById(int accountId) {
        Accounts accounts = null;
        String query = "SELECT * FROM Accounts WHERE AccountID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            result = statement.executeQuery();

            if (result.next()) {
                accounts = Accounts.builder()
                        .AccountID(result.getInt("AccountID"))
                        .Username(result.getString("Username"))
                        .Password(result.getString("Password")) // Chỉ dùng nếu cần
                        .AccountStatusID(result.getInt("AccountStatusID"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public boolean isAccountActive(int accountID) {
        String query = "SELECT AccountStatusID FROM Accounts WHERE AccountID = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int statusID = rs.getInt("AccountStatusID");
                return statusID == 1; // Giả sử 1 là trạng thái 'Active'
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Accounts> getAllAccounts() {
        List<Accounts> accountList = new ArrayList<>();
        String query = "SELECT a.AccountID, a.Username, a.Password, s.Description , u.FullName "
                + "FROM Accounts a "
                + "JOIN AccountStatus s ON a.AccountStatusID = s.AccountStatusID "
                + "LEFT JOIN Users u ON a.AccountID = u.AccountID where a.AccountStatusID = 1";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Accounts account = Accounts.builder()
                        .AccountID(resultSet.getInt("AccountID"))
                        .Username(resultSet.getString("Username"))
                        .Password(resultSet.getString("Password")) // Chỉ dùng nếu cần
                        .Description(resultSet.getString("Description")) // Lấy tên trạng thái
                        .FullName(resultSet.getString("FullName")) // Lấy tên người dùng (có thể NULL)
                        .build();
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public boolean deleteAccount(int accountId) {
        String query = "UPDATE Accounts SET AccountStatusID = 2 WHERE AccountID = ? AND AccountStatusID = 1";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Accounts getAccountDashboard(String username, String password) {
        Accounts accounts = null;
        String query = "SELECT a.*, r.RoleName FROM Accounts a "
                + "JOIN Roles r ON a.RoleID = r.RoleID "
                + "WHERE a.Username = ? AND a.Password = ? AND a.AccountStatusID = 1";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            result = statement.executeQuery();

            // Lấy dữ liệu tài khoản từ database
            while (result.next()) {
                int AccountID = result.getInt("AccountID");
                String Username = result.getString("Username");
                String Password = result.getString("Password");
                int AccountStatusID = result.getInt("AccountStatusID");
                String Role = result.getString("RoleName"); // Lấy role từ bảng Roles

                // Xây dựng đối tượng Accounts
                accounts = Accounts.builder()
                        .AccountID(AccountID)
                        .Username(Username)
                        .Password(Password)
                        .AccountStatusID(AccountStatusID)
                        .Role(Role) // Gán Role vào đối tượng
                        .build();
            }
            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Accounts> getAllAccountsDashboard() {
        List<Accounts> accountList = new ArrayList<>();
        String query = "SELECT a.AccountID, a.Username, a.Password, s.Description, \n"
                + "       u.FullName, r.RoleName \n"
                + "FROM Accounts a\n"
                + "JOIN AccountStatus s ON a.AccountStatusID = s.AccountStatusID\n"
                + "JOIN Users u ON a.AccountID = u.AccountID  \n"
                + "LEFT JOIN Roles r ON u.RoleID = r.RoleID  \n"
                + "WHERE a.AccountStatusID = 1;";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Accounts account = Accounts.builder()
                        .AccountID(resultSet.getInt("AccountID"))
                        .Username(resultSet.getString("Username"))
                        .Password(resultSet.getString("Password")) // Chỉ dùng nếu cần
                        .Description(resultSet.getString("Description")) // Lấy trạng thái tài khoản
                        .FullName(resultSet.getString("FullName")) // Lấy tên người dùng (có thể NULL)
                        .Role(resultSet.getString("RoleName")) // Lấy Role của tài khoản
                        .build();
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

}
