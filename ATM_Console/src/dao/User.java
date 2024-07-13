
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import database.DBconn;

public class User {
	private Connection connection = DBconn.getConnection();
	private Scanner scanner = new Scanner(System.in);

	public int user_exist(String username, String email) {
		String query = "SELECT * FROM users WHERE username = ? OR email = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
			if (resultSet.next()) {
				if (resultSet.getString("username") == username) return 1;
				else if (resultSet.getString("email") == email) return 2;
			} else {
				return 0;
			}
		}
//		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void register() {
		scanner.nextLine();
		System.out.print("First Name : ");
		String fname = scanner.nextLine();
		System.out.print("Last Name : ");
		String lname = scanner.nextLine();
		System.out.print("Username : ");
		String username = scanner.nextLine();
		System.out.print("Address : ");
		String address = scanner.nextLine();
		System.out.print("Phone No. : ");
		String phone = scanner.nextLine();
		System.out.print("Email : ");
		String email = scanner.nextLine();
		System.out.print("Password : ");
		String password = scanner.nextLine();
		int user_exist = user_exist(username, email);
		if (user_exist == 1) {
			System.out.println("Username already exists....\nPlease use different Username");
			return;
		} else if (user_exist == 2) {
			System.out.println("Email already exists....\nPlease use different email");
			return;
		}
		String register_query = "INSERT INTO users(username, first_name, last_name, address, phone, email, password) VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(register_query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, fname);
			preparedStatement.setString(3, lname);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, password);
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("\nRegistration Successfull");
			} else {
				System.out.println("\nRegistration Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String login() {
		scanner.nextLine();
		System.out.print("Username : ");
		String username = scanner.nextLine();
		System.out.print("Password : ");
		String password = scanner.nextLine();
		String login_query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(login_query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("email");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
