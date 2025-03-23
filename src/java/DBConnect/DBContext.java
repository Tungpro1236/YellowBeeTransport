package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author regio
 */
public class DBContext {

    public Connection connection;
    protected PreparedStatement statement;
    protected ResultSet result;

    public DBContext() {
        try {
            String username = "sa";
            String password = "12345";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=YellowBee;encrypt=true;trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
