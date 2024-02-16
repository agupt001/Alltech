package com.vehicle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestVehicle {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// Create the scanner object for inputs
		Scanner sc = new Scanner(System.in);

		// Ask the user his name for a personalized experience
		System.out.println("Hi, What is your name?");
		String name = sc.next();

		// Welcome the user
		System.out.println("Hi " + name + ", welcome to you personalized vehicle!");

		/* Get vehicle details */

		// Initialize variables with dummy values to catch exceptions
		boolean checkValidity = true;
		int vehicleNo = 0;
		boolean powerSteering = false;
		String fuelType = "";
		Vehicle vehicle = new Car(vehicleNo, "", 0, powerSteering, fuelType);
		Car car = (Car) vehicle;

		do {
			try {
				sc = new Scanner(System.in);
				// Get vehicle number from user
				System.out.println("Please provide the vehicle number.");
				vehicleNo = sc.nextInt();

				// Get power steering input
				System.out.println("Do you have power steering in the car? (yes/no)");
				String powerS = sc.next();

				// Validate user power steering input
				if (powerS.equals("yes") || powerS.equals("no"))
					powerSteering = powerS.equals("yes");
				else
					throw new PowerSteeringException();

				// Get the fuel type
				System.out.println("What is the fuel type of the car? (Gas/ Petrol/ Diesel)");
				String userFuelType = sc.next();

				// Validate user fuel type input
				if (userFuelType.equals("Gas") || userFuelType.equals("Petrol") || userFuelType.equals("Diesel"))
					fuelType = userFuelType;
				else
					throw new FuelTypeException();

				// Set the engine to off and current gear to 0 at first
				String engineStatus = "off";
				int currentGear = 0;

				// Initialize vehicle type using car class and then type cast for car object
				vehicle = new Car(vehicleNo, engineStatus, currentGear, powerSteering, fuelType);
				car = (Car) vehicle;
				checkValidity = false;
				
				
			} catch (InputMismatchException e) {
				System.out.println("Vehicle numbers can only be an integer value!");
			} catch (PowerSteeringException e) {
				System.out.println(e);
			} catch (FuelTypeException e) {
				System.out.println(e);
			}
			
		} while (checkValidity);

		/* Vehicle details section end */

		int userInput = 0;

		do {
			
			sc = new Scanner(System.in);
			
			// Car menu to get user input
			System.out.println("******* Welcome to you car menu *******");
			System.out.println("What do you want to do with the car?");
			System.out.println("1. Start the car");
			System.out.println("2. Stop the car");
			System.out.println("3. Increase the gear");
			System.out.println("4. Decrease the gear");
			System.out.println("5. Exit");

			try {
				userInput = sc.nextInt();

				// Switch case for menu based on user input
				switch (userInput) {
				
				// Ignite the vehicle
				case 1:
					if(car.engineStatus.equals("on")) {
						System.out.println("Car is already started, please select a different input.");
					}else {
						vehicle.ignite();
						car.showCarDetails();
					}
					break;

				// Stop the vehicle
				case 2:
					if(car.engineStatus.equals("off")) {
						System.out.println("Car is already stopped, please select a different input.");
					}else {
						vehicle.stop();
						car.showCarDetails();
					}
					break;

				// Increase the gear
				case 3:
					if(car.engineStatus.equals("off")) {
						System.out.println("Car is not started, please start the car first to change gears.");
					}else {
						vehicle.changeGear(1);
						car.showCarDetails();
					}
					break;

				// Decrease the gear
				case 4:
					if(car.engineStatus.equals("off")) {
						System.out.println("Car is not started, please start the car first to change gears.");
					}else {
						vehicle.changeGear(-1);
						car.showCarDetails();
					}
					break;

				// Exit
				case 5:
					if(car.engineStatus.equals("on")) {
						System.out.println("Please turn the car off before leaving.");
					}else {
						System.out.println("Thank you for using the automated car service! Have a good day.");
						System.exit(0);
					}

				// Show a default message if user selects input 1 < x < 5
				default:
					System.out.println("Valid inputs are numbers from 1 to 5. Please try again");

				}

			} catch (InputMismatchException e) {
				// Input mismatch error for String to Integer conversion
				System.out.println("Only integers are allowed! Valid inputs are numbers from 1 to 5. Please try again");
			}

		} while (userInput != 5);

	}

}
