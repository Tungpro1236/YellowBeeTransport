/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DBConnect.DBContext;
import Model.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleDAO extends DBContext {
    public List<Roles> getAllRoles() {
    List<Roles> roleList = new ArrayList<>();
    String query = "SELECT * FROM Roles"; 

    try {
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();

        while (result.next()) {
            int roleId = result.getInt("RoleID");
            String roleName = result.getString("RoleName");

            Roles role = Roles.builder()
                    .RoleID(roleId)
                    .RoleName(roleName)
                    .build();

            roleList.add(role);
        }
        return roleList;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

}