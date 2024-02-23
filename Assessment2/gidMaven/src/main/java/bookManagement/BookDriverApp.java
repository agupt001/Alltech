package bookManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookDriverApp {

	public static void main(String[] args) {
		
		// Initialize variables
		Scanner sc = new Scanner(System.in);
		BookDAOImpl bkdl = new BookDAOImpl();
		int choice = 0;
		
		System.out.println("Welcome to Book Management using JDBC");
		System.out.println("");
		
		// Loop through the menu
		do {
			
			sc = new Scanner(System.in);
			
			System.out.println("What would you like to do?");
			System.out.println("1. Insert Book record");
			System.out.println("2. Read a Book record");
			System.out.println("3. Read all Book records");
			System.out.println("4. Update a Book record");
			System.out.println("5. Delete a Book record");
			System.out.println("6. Exit");
			
			try {
				// Get choice from user
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Only integers are allowed!");
				choice = 0;
			}
			
			// Switch case
			try {
				switch (choice) {
				
				case 1:
					bkdl.create(sc);
					break;

				case 2:
					bkdl.read(sc);
					break;
					
				case 3:
					bkdl.readAll();
					break;
					
				case 4:
					bkdl.update(sc);
					break;
					
				case 5:
					bkdl.delete(sc);
					break;
					
				case 6:
					System.exit(0);
					
				default:
					System.out.println("Please select a valid input!");
				}
			} catch (InputMismatchException  e) {
				System.out.println("Only integers are allowed!");
			}
			
		}while(choice!=6);
		
	}
}
