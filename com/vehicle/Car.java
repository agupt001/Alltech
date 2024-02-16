package com.vehicle;

public class Car extends Vehicle {

	protected boolean powerSteering;
	protected String fuelType;
	
	public Car(int vehicleNo, String engineStatus, int currentGear, boolean powerSteering, String fuelType) {
		super(vehicleNo, engineStatus, currentGear);
		this.powerSteering = powerSteering;
		this.fuelType = fuelType;
	}
	
	public void showCarDetails() {
		
		System.out.println("--- The details for your car number "+super.vehicleNo+" are ---");
		System.out.println("Engine Status: "+super.engineStatus);
		System.out.println("Current Gear: "+super.currentGear);
		System.out.println("Power Steering: "+powerSteering);
		System.out.println("Fuel Type: "+fuelType);
		
	}

	@Override
	void ignite() {
		
		super.engineStatus = "on";
		super.currentGear = 1;
		
	}

	@Override
	void changeGear(int flag) {
		
		if(flag == 1 && super.currentGear < 6) {
			super.currentGear += 1;
		}else if(flag == -1 && super.currentGear > 1) {
			super.currentGear -= 1;
		}
		
	}

	@Override
	void stop() {
		
		super.engineStatus = "off";
		super.currentGear = 0;
		
	}

}
