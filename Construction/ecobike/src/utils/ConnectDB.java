package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/ecobike";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "12345";
    
    private static Connection connect;
   
    
    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Connect databse successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connect;
    }
    
    public static void main(String[] args) {
        ConnectDB.getConnection();
    }
}
