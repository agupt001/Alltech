package bankChatBot;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		// Create a List for storing requests
		ArrayList<String> reqList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		// Get the BankOperations object
		BankOperations bko = new BankOperations();

		System.out.println("Welcome to the bank chatbot!");

		System.out.println("Enter the number of requests you would like to make");
		int numberOfReqs = sc.nextInt();
		sc.nextLine();

		// Iterate through the requests
		for (int i = 0; i < numberOfReqs; i++) {

			System.out.println("Enter request number " + (i + 1));

			// Get the user requests and store it in the List
			String userInput = sc.nextLine();
			reqList.add(userInput);

		}

		// Iterate through the request list
		for (String req : reqList) {

			// Process the requests
			bko.processOperation(req);

		}

		// Close the scanner
		sc.close();

	}

}
