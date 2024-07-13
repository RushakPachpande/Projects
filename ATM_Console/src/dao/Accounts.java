
package dao;

import java.sql.*;
import java.util.Scanner;
import database.DBconn;
import util.Date_Time;

public class Accounts {
	private Connection connection = DBconn.getConnection();
	private Scanner scanner = new Scanner(System.in);

	public long open_account(String email) {
		if (!account_exist(email)) {
			String open_account_query = "INSERT INTO accounts(acc_no, email, balance, security_pin, user_id) VALUES(?,?,?,?,?)";
			String insert_accno_users = "UPDATE users SET acc_no = ? WHERE email = ?";
			System.out.print("Enter Initial Amount : ");
			double balance = scanner.nextDouble();
			int security_pin = 0;
			while (String.valueOf(security_pin).length() != 4) {
				System.out.print("Enter 4 digit Security Pin : ");
				security_pin = scanner.nextInt();
			}
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT id from users WHERE email = ?");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				rs.next();
				int user_id = rs.getInt("id");
				long acc_no = generateAccountNumber();
				PreparedStatement preparedStatement = connection.prepareStatement(open_account_query);
				preparedStatement.setLong(1, acc_no);
				preparedStatement.setString(2, email);
				preparedStatement.setDouble(3, balance);
				preparedStatement.setInt(4, security_pin);
				preparedStatement.setInt(5, user_id);
				int rowsAffected = preparedStatement.executeUpdate();
				PreparedStatement preparedStatement1 = connection.prepareStatement(insert_accno_users);
				preparedStatement1.setLong(1, acc_no);
				preparedStatement1.setString(2, email);
				int rowsAffected1 = preparedStatement1.executeUpdate();
				if (rowsAffected > 0 && rowsAffected1 > 0) {
					String transaction = "INSERT INTO transactions(date, type, acc_no, amount, status, balance, security_pin) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement2 = connection.prepareStatement(transaction);
					preparedStatement2.setString(1, new Date_Time().getDateTime());
					preparedStatement2.setString(2, "Credit");
					preparedStatement2.setLong(3, acc_no);
					preparedStatement2.setDouble(4, balance);
					preparedStatement2.setString(5, "Successfull");
					preparedStatement2.setDouble(6, balance);
					preparedStatement2.setInt(7, security_pin);
					preparedStatement2.executeUpdate();
					return acc_no;
				} else {
					throw new RuntimeException("Account Creation failed!!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		throw new RuntimeException("Account Creation failed!!");
	}

	public long getAccount_number(String email) {
		String query = "SELECT acc_no from accounts WHERE email = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getLong("acc_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Account Number Doesn't Exist!");
	}

	private long generateAccountNumber() {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT acc_no from accounts ORDER BY acc_no DESC LIMIT 1");
			if (resultSet.next()) {
				long last_acc_no = resultSet.getLong("acc_no");
				return last_acc_no + 1;
			} else {
				return 10000100;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 10000100;
	}

	public boolean account_exist(String email) {
		String query = "SELECT acc_no FROM accounts WHERE email = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
