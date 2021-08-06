package daos;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/JDBC_Lab";
    public static final String USERNAME = "zach";
    public static final String PASSWORD = "zipcode0";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throw new RuntimeException("Could not connect to database", throwable);
        }
    }
}
