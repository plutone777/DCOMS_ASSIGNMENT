package dcoms_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String DB_URL = "jdbc:derby://localhost:1527/DCOMS_ASSIGNMENT";
    public static final String USER = "DCOMS";
    public static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    

}

