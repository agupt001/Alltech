package com.employee;

public class Employee {
	
	// Employee attributes
	private int empId;
	private String empName;
	private String grade;
	
	// Constructor to initialize attributes
	public Employee(int empId, String empName, String grade) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.grade = grade;
	}
	
	// Getter methods
	public int getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public String getGrade() {
		return grade;
	}
	
	// To string overridden
	@Override
	public String toString() {
		
		String empDetails = "Custom output can be set here.";
		return empDetails;
	}
	
}
