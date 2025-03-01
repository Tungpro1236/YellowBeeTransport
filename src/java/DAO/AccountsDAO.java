/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnect.DBContext;
import Model.Accounts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String query = "SELECT r.RoleName " +
                       "FROM Accounts a " +
                       "JOIN Users u ON a.AccountID = u.AccountID " +
                       "JOIN Roles r ON u.RoleID = r.RoleID " +
                       "WHERE a.Username = ? AND a.Password = ? AND a.AccountStatusID = 1";

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
}
