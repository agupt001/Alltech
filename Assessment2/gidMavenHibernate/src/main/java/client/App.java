package client;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.BookDAOImpl;

public class App {

	public static void main(String[] args) {
		
		// Create BookDAOImpl class object
		BookDAOImpl bookDao = new BookDAOImpl();		
		
		Scanner sc = new Scanner(System.in);
		
		int count = 0;
		
		do {
			// Get user choice
			sc = new Scanner(System.in);
			
			System.out.println("================= Menu ====================");
			System.out.println("1-Create a Book");
			System.out.println("2-Read a Book");
			System.out.println("3-Read all Books");
			System.out.println("4-Update a Book");
			System.out.println("5-Delete Book");
			System.out.println("6-Exit");
			System.out.println("Please enter your choice.");
			
			try {
				count = sc.nextInt();
			} catch (InputMismatchException e) {
				count = 0;
			}			
			
			try {
				
				// Create switch case
				switch(count) {
				case 1:
					
					bookDao.create(sc);
					break;
					
				case 2:
					
					bookDao.read(sc);
					break;
					
				case 3:
					
					bookDao.readAll();
					break;
				
				case 4:
					
					bookDao.update(sc);
					break;
					
				case 5:
					
					bookDao.delete(sc);
					break;
				
				case 6:
					System.exit(0);
					
				default:
					System.out.println("Please select a valid option.");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Valid entries only");
			}
			
		}while(count!=6);
		
		sc.close();

	}

}
