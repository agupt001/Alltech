package com.employee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetEmployee {
	
	@SuppressWarnings("resource")
	public Employee getEmployeeData() {
		
		// Initialize Employee object
		Employee emp = new Employee(0, "", "");
		Scanner sc = new Scanner(System.in);
		
		// Boolean value to keep track of validity
		boolean checkVal = true;
		
		do{
			// Reinitialize scanner for loop
			sc = new Scanner(System.in);
			try {
				// Get user input
				System.out.println("Please enter employee ID: ");
				// Throw input mismatch exception here
				int empId = sc.nextInt();
				sc.nextLine(); // This is needed for spaced inputs
				
				System.out.println("Please enter employee Name: ");
				String empName = sc.nextLine();
				
				System.out.println("Please enter Grade (U1, U2, U3, U4, U5, P1, P2): ");
				String tempGrade = sc.nextLine();
				String grade = "";
				
				// Throw custom exception
				if(tempGrade.equals("U1") || tempGrade.equals("U2") || tempGrade.equals("U3") || tempGrade.equals("U4") || tempGrade.equals("U5") || tempGrade.equals("P1") || tempGrade.equals("P2"))
					grade = tempGrade;
				else
					throw new InvalidGradeException();
				
				// Create employee object
				emp = new Employee(empId, empName, grade);
				
				// Validation complete, get out of the loop
				checkVal = false;
				
			} catch (InputMismatchException e) {
				
				System.out.println("Employee IDs can only be an integer value! Try again.");
				
			} catch (InvalidGradeException e) {
				
				System.out.println(e);
			}
			
			
		}while(checkVal);
		
		// Return employee object
		return emp;
	}
}
