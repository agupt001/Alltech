package com.employee;

public class InvalidGradeException extends Exception {
	
	// Invalid Grade Constructor
	public InvalidGradeException() {
		super("Grades can only be in the specified values! Try again.");
	}
}
