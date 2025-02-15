import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:4306/enrollment_system";
    private static final String USER = "root";  // Default username for XAMPP
    private static final String PASSWORD = "";  // Default password for XAMPP

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicit driver loading
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = connect();
        if (connection != null) {
            System.out.println("Database connection is active.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
