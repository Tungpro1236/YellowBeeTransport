package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private static final String SERVER_NAME = "localhost";
    private static final String DB_NAME = "YellowBee";
    private static final String PORT_NUMBER = "1433";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "12345";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER + ";databaseName=" + DB_NAME + ";encrypt=true;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }
} 