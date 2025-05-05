package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studentsystem";
    private static final String DB_USER = "admin1";
    private static final String DB_PASSWORD = "admin";

    // Phương thức để lấy kết nối đến database
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Không thể kết nối đến database.");
        }
    }
}