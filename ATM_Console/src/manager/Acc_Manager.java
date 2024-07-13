
package manager;

import java.sql.*;
import java.util.Scanner;
import database.DBconn;
import util.Date_Time;

public class Acc_Manager {
	private Connection connection = DBconn.getConnection();
	private Scanner scanner = new Scanner(System.in);

	public void debit_money(long acc_no) throws SQLException {
		scanner.nextLine();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Security Pin : ");
		String security_pin = scanner.nextLine();
		try {
			connection.setAutoCommit(false);
			if (acc_no != 0) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM accounts WHERE acc_no = ? and security_pin = ? ");
				preparedStatement.setLong(1, acc_no);
				preparedStatement.setString(2, security_pin);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					double current_balance = resultSet.getDouble("balance");
					if (amount <= current_balance) {
						String debit_query = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
						PreparedStatement preparedStatement1 = connection.prepareStatement(debit_query);
						preparedStatement1.setDouble(1, amount);
						preparedStatement1.setLong(2, acc_no);
						int rowsAffected = preparedStatement1.executeUpdate();
						// Debit Money
						if (rowsAffected > 0) {
							System.out.println("Rs." + amount + " debited Successfully");
							String transaction = "INSERT INTO transactions(date, type, acc_no, amount, status, balance, security_pin) VALUES (?,?,?,?,?,?,?)";
							PreparedStatement preparedStatement2 = connection.prepareStatement(transaction);
							preparedStatement2.setString(1, new Date_Time().getDateTime());
							preparedStatement2.setString(2, "Debit");
							preparedStatement2.setLong(3, acc_no);
							preparedStatement2.setDouble(4, amount);
							preparedStatement2.setString(5, "Successfull");
							preparedStatement2.setDouble(6, current_balance - amount);
							preparedStatement2.setString(7, security_pin);
							preparedStatement2.executeUpdate();
							connection.commit();
							connection.setAutoCommit(true);
							return;
						} else {
							System.out.println("Transaction Failed!");
							String transaction = "INSERT INTO transactions(date, type, acc_no, amount, status, balance, security_pin) VALUES (?,?,?,?,?,?,?)";
							PreparedStatement preparedStatement2 = connection.prepareStatement(transaction);
							preparedStatement2.setString(1, new Date_Time().getDateTime());
							preparedStatement2.setString(2, "Debit");
							preparedStatement2.setLong(3, acc_no);
							preparedStatement2.setDouble(4, amount);
							preparedStatement2.setString(5, "Failed");
							preparedStatement2.setDouble(6, current_balance);
							preparedStatement2.setString(7, security_pin);
							preparedStatement2.executeUpdate();
							connection.rollback();
							connection.setAutoCommit(true);
						}
					} else {
						System.out.println("Insufficient Balance!");
					}
				} else {
					System.out.println("Invalid Pin!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}

	public void credit_money(long acc_no) throws SQLException {
//		scanner.nextLine();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Security Pin : ");
		String security_pin = scanner.nextLine();
		try {
			connection.setAutoCommit(false);
			if (acc_no != 0) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM accounts WHERE acc_no = ? and security_pin = ? ");
				preparedStatement.setLong(1, acc_no);
				preparedStatement.setString(2, security_pin);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					double current_balance = resultSet.getDouble("balance");
					String credit_query = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";
					PreparedStatement preparedStatement1 = connection.prepareStatement(credit_query);
					preparedStatement1.setDouble(1, amount);
					preparedStatement1.setLong(2, acc_no);
					int rowsAffected = preparedStatement1.executeUpdate();
					// Credit Money
					if (rowsAffected > 0) {
						System.out.println("Rs." + amount + " credited Successfully");
						String transaction = "INSERT INTO transactions(date, type, acc_no, amount, status, balance, security_pin) VALUES (?,?,?,?,?,?,?)";
						PreparedStatement preparedStatement2 = connection.prepareStatement(transaction);
						preparedStatement2.setString(1, new Date_Time().getDateTime());
						preparedStatement2.setString(2, "Credit");
						preparedStatement2.setLong(3, acc_no);
						preparedStatement2.setDouble(4, amount);
						preparedStatement2.setString(5, "Successfull");
						preparedStatement2.setDouble(6, current_balance + amount);
						preparedStatement2.setString(7, security_pin);
						preparedStatement2.executeUpdate();
						connection.commit();
						connection.setAutoCommit(true);
						return;
					} else {
						System.out.println("Transaction Failed!");
						String transaction = "INSERT INTO transactions(date, type, acc_no, amount, status, balance, security_pin) VALUES (?,?,?,?,?,?,?)";
						PreparedStatement preparedStatement2 = connection.prepareStatement(transaction);
						preparedStatement2.setString(1, new Date_Time().getDateTime());
						preparedStatement2.setString(2, "Credit");
						preparedStatement2.setLong(3, acc_no);
						preparedStatement2.setDouble(4, amount);
						preparedStatement2.setString(5, "Failed");
						preparedStatement2.setDouble(6, current_balance);
						preparedStatement2.setString(7, security_pin);
						preparedStatement2.executeUpdate();
						connection.rollback();
						connection.setAutoCommit(true);
					}
				} else {
					System.out.println("Invalid Security Pin!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}

	public void transfer_money(long sender_acc_no) throws SQLException {
		{
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT first_name,last_name,acc_no from users WHERE acc_no != ?");
			preparedStatement.setLong(1, sender_acc_no);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println();
				String formatter = "| %-20s | %10s |%n";
				System.out.format("+----------------------+------------+%n");
				System.out.format(formatter, "NAME", "ACC_NO");
				System.out.format("+----------------------+------------+%n");
				do {
					System.out.format(formatter,
							resultSet.getString("first_name") + " " + resultSet.getString("last_name"),
							resultSet.getLong("acc_no"));
					System.out.format("+----------------------+------------+%n");
				} while (resultSet.next());
			}
		}
		System.out.print("Enter Receiver Account Number (refer above table) : ");
		long receiver_acc_no = scanner.nextLong();
		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		System.out.print("Enter Security Pin : ");
		String security_pin = scanner.nextLine();
		try {
			connection.setAutoCommit(false);
			if (sender_acc_no != 0 && receiver_acc_no != 0) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM accounts WHERE acc_no = ? AND security_pin = ?");
				preparedStatement.setLong(1, sender_acc_no);
				preparedStatement.setString(2, security_pin);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					double sender_current_balance = resultSet.getDouble("balance");
					if (amount <= sender_current_balance) {
						// debit and credit queries
						String debit_query = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
						String credit_query = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";
						// Debit and Credit prepared Statements
						PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
						PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);
						// Set Values for debit and credit prepared statements
						debitPreparedStatement.setDouble(1, amount);
						debitPreparedStatement.setLong(2, sender_acc_no);
						creditPreparedStatement.setDouble(1, amount);
						creditPreparedStatement.setLong(2, receiver_acc_no);
						int rowsAffected1 = debitPreparedStatement.executeUpdate();
						int rowsAffected2 = creditPreparedStatement.executeUpdate();
						if (rowsAffected1 > 0 && rowsAffected2 > 0) {
							System.out.println("Transaction Successful!");
							System.out.println("Rs." + amount + " Transferred to " + receiver_acc_no + " Successfully");
							// Sender
							String send_transaction = "INSERT INTO transactions(date, type, acc_no, amount, transfer_acc, status, balance, security_pin) VALUES (?,?,?,?,?,?,?,?)";
							PreparedStatement sendpreparedStatement = connection.prepareStatement(send_transaction);
							sendpreparedStatement.setString(1, new Date_Time().getDateTime());
							sendpreparedStatement.setString(2, "Debit");
							sendpreparedStatement.setLong(3, sender_acc_no);
							sendpreparedStatement.setDouble(4, amount);
							sendpreparedStatement.setLong(5, receiver_acc_no);
							sendpreparedStatement.setString(6, "Successfull");
							sendpreparedStatement.setDouble(7, sender_current_balance - amount);
							sendpreparedStatement.setString(8, security_pin);
							sendpreparedStatement.executeUpdate();
							// Receiver
							PreparedStatement cpreparedStatement = connection
									.prepareStatement("SELECT balance, security_pin FROM accounts WHERE acc_no = ?");
							cpreparedStatement.setLong(1, receiver_acc_no);
							ResultSet cresultSet = cpreparedStatement.executeQuery();
							cresultSet.next();
							double receiver_current_balance = cresultSet.getDouble("balance");
							String receiver_security_pin = cresultSet.getString("security_pin");
							String receive_transaction = "INSERT INTO transactions(date, type, acc_no, amount, transfer_acc, status, balance, security_pin) VALUES (?,?,?,?,?,?,?,?)";
							PreparedStatement receivepreparedStatement = connection
									.prepareStatement(receive_transaction);
							receivepreparedStatement.setString(1, new Date_Time().getDateTime());
							receivepreparedStatement.setString(2, "Credit");
							receivepreparedStatement.setLong(3, receiver_acc_no);
							receivepreparedStatement.setDouble(4, amount);
							receivepreparedStatement.setLong(5, sender_acc_no);
							receivepreparedStatement.setString(6, "Successfull");
							receivepreparedStatement.setDouble(7, receiver_current_balance + amount);
							receivepreparedStatement.setString(8, receiver_security_pin);
							receivepreparedStatement.executeUpdate();
							connection.commit();
							connection.setAutoCommit(true);
							return;
						} else {
							System.out.println("Transaction Failed");
							String send_transaction = "INSERT INTO transactions(date, type, acc_no, amount, transfer_acc, status, balance, security_pin) VALUES (?,?,?,?,?,?,?,?)";
							PreparedStatement sendpreparedStatement = connection.prepareStatement(send_transaction);
							sendpreparedStatement.setString(1, new Date_Time().getDateTime());
							sendpreparedStatement.setString(2, "Debit");
							sendpreparedStatement.setLong(3, sender_acc_no);
							sendpreparedStatement.setDouble(4, amount);
							sendpreparedStatement.setLong(5, receiver_acc_no);
							sendpreparedStatement.setString(6, "Failed");
							sendpreparedStatement.setDouble(7, sender_current_balance);
							sendpreparedStatement.setString(8, security_pin);
							sendpreparedStatement.executeUpdate();
							connection.rollback();
							connection.setAutoCommit(true);
						}
					} else {
						System.out.println("Insufficient Balance!");
					}
				} else {
					System.out.println("Invalid Security Pin!");
				}
			} else {
				System.out.println("Invalid account number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		connection.setAutoCommit(true);
	}

	public void getBalance(long acc_no) {
		scanner.nextLine();
		System.out.print("Enter Security Pin :  ");
		String security_pin = scanner.nextLine();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ? AND security_pin = ?");
			preparedStatement.setLong(1, acc_no);
			preparedStatement.setString(2, security_pin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Balance :  " + resultSet.getDouble("balance"));
			} else {
				System.out.println("Invalid Pin!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTransactions(long acc_no) {
		scanner.nextLine();
		System.out.print("Enter Security Pin :  ");
		String security_pin = scanner.nextLine();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM transactions WHERE acc_no = ? AND security_pin = ?");
			preparedStatement.setLong(1, acc_no);
			preparedStatement.setString(2, security_pin);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				System.out.println();
				System.out.format(
						"+--------------------+--------+------------+------------+----------------+---------------+-----------------+%n");
				System.out.format(
						"│    DATE - TIME     │  TYPE  │    ACC_NO  │     AMOUNT │   TRANSFER_ACC │       STATUS  │         BALANCE │%n");
				System.out.format(
						"+--------------------+--------+------------+------------+----------------+---------------+-----------------+%n");
				String formatter = "│ %-18s │ %-6s │ %10s │ %10s │ %14s │ %13s │ %15s │%n";
				do {
					System.out.format(formatter, rs.getString("date"), rs.getString("type"), rs.getLong("acc_no"),
							rs.getDouble("amount"), rs.getLong("transfer_acc"), rs.getString("status"),
							rs.getDouble("balance"));
					System.out.format(
							"+--------------------+--------+------------+------------+----------------+---------------+-----------------+%n");
				} while (rs.next());
			} else {
				System.out.println("Invalid Pin!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showAllUsers() {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println();
//				id, username, first_name, last_name, address, phone, email, acc_no
				System.out.format(
						"+-----------+----------------+----------------+----------------+------------+------------+----------------------+------------+%n");
				System.out.format(
						"│  User_ID  │ USERNAME       │   First Name   │    Last Name   │   Address  │    PHONE   │         EMAIL        │    ACC_NO  │%n");
				System.out.format(
						"+-----------+----------------+----------------+----------------+------------+------------+----------------------+------------+%n");
				String formatter = "│ %-9d │ %-14s │ %14s │ %14s │ %10s │ %10s │ %20s │ %10d │%n";
				do {
					System.out.format(formatter, resultSet.getInt("id"), resultSet.getString("username"),
							resultSet.getString("first_name"), resultSet.getString("last_name"),
							resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("email"),
							resultSet.getLong("acc_no"));
					System.out.format(
							"+-----------+----------------+----------------+----------------+------------+------------+----------------------+------------+%n");
				} while (resultSet.next());
			} else {
				System.out.println("Invalid Pin!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showUserById() {
		System.out.print("\nEnter User_ID to search : ");
		int user_id = scanner.nextInt();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
			preparedStatement.setInt(1, user_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println();
				System.out.println("User_id : " + resultSet.getInt("id"));
				System.out.println("Username : " + resultSet.getString("username"));
				System.out.println("First Name : " + resultSet.getString("first_name"));
				System.out.println("Last Name : " + resultSet.getString("last_name"));
				System.out.println("Address : " + resultSet.getString("address"));
				System.out.println("Phone No, : " + resultSet.getString("phone"));
				System.out.println("Email : " + resultSet.getString("email"));
				System.out.println("Acc_No. : " + resultSet.getLong("acc_no"));
				System.out.println();
			} else {
				System.out.println("NO USER FOUND.....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser() {
		System.out.print("Enter User_ID to Delete : ");
		int user_id = scanner.nextInt();
		String queryUsers = "DELETE FROM users WHERE id = ?";
//		String queryAcc = "DELETE FROM accounts WHERE user_id = ?";
//		String queryTransc = "DELETE FROM transactions WHERE id = ?";
		;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryUsers);
			preparedStatement.setInt(1, user_id);
//			PreparedStatement preparedStatement1 = connection.prepareStatement(queryAcc);
//			preparedStatement1.setInt(1, user_id);
			int rowsAffected = preparedStatement.executeUpdate();
//			int rowsAffected1 = preparedStatement1.executeUpdate();
//			if (rowsAffected > 0 && rowsAffected1 > 0) {
			if (rowsAffected > 0) {
				System.out.println("User Deleted Succesfully.....");
			} else {
				System.out.println("User Deletion Failed.....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
