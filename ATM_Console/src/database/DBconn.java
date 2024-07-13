
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {
	private static final String url = "jdbc:mysql://localhost:3307/atm";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Connected to the database");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading driver: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Error connecting to the database: " + e.getMessage());
			return null;
		}
	}
}