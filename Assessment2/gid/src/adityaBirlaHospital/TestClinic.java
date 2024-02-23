package adityaBirlaHospital;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestClinic {

	public static void main(String[] args) {

		boolean val = true;
		Scanner sc = new Scanner(System.in);

		// Create a doctor list
		ArrayList<Doctor> docList = new ArrayList<Doctor>();

		System.out.println("Welcome to Doctor's Appointment Portal");
		System.out.println("");
		System.out.println("Please enter 2 Doctor details");

		// Loop for 2 doctors input
		for (int i = 0; i < 2; i++) {

			System.out.println("Enter Doctor ID");
			String docId = sc.nextLine();

			System.out.println("Enter Doctor Name");
			String docName = sc.nextLine();

			// Store the doctors
			Doctor doc = new Doctor();
			doc.setDocId(docId);
			doc.setDocName(docName);

			docList.add(doc);

		}

		System.out.println("");
		System.out.println("Let's add the Appointment details for each Doctor");
		System.out.println("");

		do {

			// Loop through the doctor list
			for (Doctor doctor : docList) {
				
				System.out.println("Doctor Name: "+doctor.getDocName());
				System.out.println("");
				
				// Loop 2 times for appointments
				for (int i = 0; i < 2; i++) {
					
					// Get date input and convert into date format
					System.out.println("Enter the Appointment Date (mm-dd-yyyy)");
					String aptDate = sc.next();

					SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
					Date aptDateConv = null;

					// Catch date parsing exceptions
					try {
						aptDateConv = dateFormat.parse(aptDate);
					} catch (ParseException e) {
						System.out.println("Please enter a valid date! Try again");
						break;
					}

					System.out.println("Enter the Number of Patients");
					int noOfPat = 0;

					// Catch input mismatch exception
					try {
						noOfPat = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Only numbers are allowed!");
						break;
					}

					Appointment apt = new Appointment();

					// Catch custom exception for data
					try {
						apt.setNoOfPatients(noOfPat);
					} catch (InvalidDataException e) {
						System.out.println(e + " Try Again!");
						break;
					}

					apt.setAppDate(aptDateConv);

					doctor.addAppointment(apt);
				}
				val = false;

			}

		} while (val);

		boolean readVal = true;

		// Loop for reading docs
		do {
			
			sc.nextLine();
			System.out.println("Enter Doctor ID");
			String docId = sc.nextLine();
			boolean docFound = false;
			
			// Loop through the docs and find the id
			for (Doctor docs : docList) {

				if (docs.getDocId().equals(docId)) {

					// Print appointments
					docs.printAppointment();
					docFound = true;
					break;

				}

			}

			if (!docFound)
				System.out.println("Doctor ID does not exists");

			System.out.println("");
			System.out.println("Want to read another Doctor Appointment? (yes/no) ");
			String readAnother = sc.next();

			// Exit if user says no
			if (readAnother.equals("no")) {
				readVal = false;
			}

		} while (readVal);

		// Close the scanner
		sc.close();

	}

}
