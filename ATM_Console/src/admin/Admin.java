
package admin;

import java.util.Scanner;
import dao.User;
import manager.Acc_Manager;

public class Admin {
	private Scanner scanner = new Scanner(System.in);
	User user = new User();
	Acc_Manager accountManager = new Acc_Manager();

	public void login() {
		System.out.print("Enter Username : ");
		String username = scanner.nextLine();
		System.out.print("Enter Password : ");
		String password = scanner.nextLine();
		if (username.equals("admin") && password.equals("admin")) {
			System.out.println("Admin Login Successfull.....");
			int choice1;
			do {
				System.out.println("\n");
				System.out.println("*** Admin Controls ***\n");
				System.out.println("1. Display all Users");
				System.out.println("2. Register new User");
				System.out.println("3. Register new User");
				System.out.println("4. Delete a User");
				System.out.println("5. Exit");
				System.out.print("\nEnter your choice : ");
				choice1 = scanner.nextInt();
				switch (choice1) {
				case 1:
					accountManager.showAllUsers();
					break;

				case 2:
					accountManager.showUserById();
					break;

				case 3:
					user.register();
					break;

				case 4:
					accountManager.deleteUser();
					break;

				case 5:
					break;

				default:
					System.out.println("Enter Valid Choice");
					break;
				}
			} while (choice1 != 5);
		} else {
			System.out.println("Invaild Username or Password.....");
		}
	}
}
