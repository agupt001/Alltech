package com.vehicle;

public class FuelTypeException extends Exception {
	
	// Constructor for Fuel Type Exception
	public FuelTypeException() {
		super("Fuel type can only be Gas/ Petrol/ Diesel");
	}
}
