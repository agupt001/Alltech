package com.vehicle;

public abstract class Vehicle {

	// Keep the attributes protected
	protected int vehicleNo;
	protected String engineStatus;
	protected int currentGear;
	
	// Constructor to initialize attributes
	public Vehicle(int vehicleNo, String engineStatus, int currentGear) {
		super();
		this.vehicleNo = vehicleNo;
		this.engineStatus = engineStatus;
		this.currentGear = currentGear;
	}
	
	// Abstract methods
	abstract void ignite();
	abstract void changeGear(int flag);
	abstract void stop();
	
}
