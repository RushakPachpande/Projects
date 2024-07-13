
package main;

import java.sql.SQLException;
import java.util.Scanner;
import admin.Admin;
import dao.Accounts;
import dao.User;
import manager.Acc_Manager;

public class App {
	private Scanner scanner = new Scanner(System.in);

	public void display() throws SQLException {
		User user = new User();
		Admin admin = new Admin();
		Accounts accounts = new Accounts();
		Acc_Manager accountManager = new Acc_Manager();
		String email;
		long acc_no;
		while (true) {
			System.out.println("\n");
			System.out.println("*** WELCOME TO BANKING SYSTEM ***\n");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("9. Admin Login");
			System.out.print("\nEnter your choice : ");
			int choice1 = scanner.nextInt();
			switch (choice1) {
			case 1:
				user.register();
				break;

			case 2:
				email = user.login();
				if (email != null) {
					System.out.println("\nUser Logged In");
					if (!accounts.account_exist(email)) {
						System.out.println("\n1. Open a new Bank Account");
						System.out.println("2. Exit");
						System.out.print("\nEnter your choice : ");
						if (scanner.nextInt() == 1) {
							acc_no = accounts.open_account(email);
							System.out.println("Account Created Successfully");
							System.out.println("Your Account Number is : " + acc_no);
						} else {
							break;
						}
					}
					acc_no = accounts.getAccount_number(email);
					int choice2 = 0;
					while (choice2 != 6) {
						System.out.println();
						System.out.println("1. Debit Money");
						System.out.println("2. Credit Money");
						System.out.println("3. Transfer Money");
						System.out.println("4. Check Balance");
						System.out.println("5. Transactions");
						System.out.println("6. Exit");
						System.out.print("\nEnter your choice : ");
						choice2 = scanner.nextInt();
						switch (choice2) {
						case 1:
							accountManager.debit_money(acc_no);
							break;

						case 2:
							accountManager.credit_money(acc_no);
							break;

						case 3:
							accountManager.transfer_money(acc_no);
							break;

						case 4:
							accountManager.getBalance(acc_no);
							break;

						case 5:
							accountManager.getTransactions(acc_no);
							break;

						case 6:
							break;

						default:
							System.out.println("Enter Valid Choice");
							break;
						}
					}
				} else {
					System.out.println("Incorrect Username or Password");
				}
				break;

			case 3:
				System.out.println("THANK YOU FOR USING BANKING SYSTEM !!!");
				System.out.println("Exiting System.....\n");
				return;

			case 9:
				admin.login();
				break;

			default:
				System.out.println("Enter Valid Choice");
				break;
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		new App().display();
	}
}
