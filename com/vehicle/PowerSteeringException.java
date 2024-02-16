package com.vehicle;

public class PowerSteeringException extends Exception {
	
	// Constructor for Power Steering Exception
	public PowerSteeringException() {
		
		super("Power steering value can only be yes or no.");
	}
}
