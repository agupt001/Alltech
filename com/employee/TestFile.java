package com.employee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestFile {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Ask the user for number of records to be created
		System.out.println("Enter the total number of Employees you want to create.");
		int num_employees = sc.nextInt();
		
		// Create an employee array
		Employee emp_arr[] = new Employee[num_employees];
		
		// Loop through the total number of employees
		for(int i=0; i<num_employees; i++) {
			
			// Create GetEmployee class object
			GetEmployee ge = new GetEmployee();
			
			// Get employee record
			Employee empRecord = ge.getEmployeeData();
			
			// Store in employee array
			emp_arr[i] = empRecord;
		}
		
		try {
			// Write into file
			FileWriter fw = new FileWriter("/Users/ankitgupta/eclipse-workspace/HelloWorld/src/com/employee/empdetails.txt");
			for(int i=0; i<num_employees; i++) {
				
				// Get the values from array
				int tempEmpId = emp_arr[i].getEmpId();
				String tempEmpName = emp_arr[i].getEmpName();
				String tempgrade = emp_arr[i].getGrade();
				
				// Write into file
				fw.write("\n"+tempEmpId+"\t"+tempEmpName+"\t"+tempgrade);
				
			}
			// Don't forget to close the file
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			// Read the file
			FileReader fr = new FileReader("/Users/ankitgupta/eclipse-workspace/HelloWorld/src/com/employee/empdetails.txt");
			
			int ch;
			while((ch = fr.read()) != -1) {
				// Type cast into character and print on command line
				System.out.print((char)ch);
			}
			// Close the file
			fr.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
