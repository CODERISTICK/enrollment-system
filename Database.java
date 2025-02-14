import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:4306/enrollment_system?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to get a new connection each time
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successfully connected to enrollment_system database!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found!");
            System.err.println("Please ensure mysql-connector-java.jar is in the lib folder.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Database connection failed!");
            System.err.println("Verify XAMPP is running and MySQL port is 4306.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                // Test query
                try (PreparedStatement ps = conn.prepareStatement("SELECT 1");
                     ResultSet rs = ps.executeQuery()) {
                    
                    if (rs.next()) {
                        System.out.println("Database connection test successful!");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Test connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
