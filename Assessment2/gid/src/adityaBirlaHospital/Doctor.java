package adityaBirlaHospital;

import java.util.ArrayList;

public class Doctor {

	private String docId;
	private String docName;

	private ArrayList<Appointment> apptList = new ArrayList<Appointment>();

	// Add Appointments in the list
	public void addAppointment(Appointment apt) {

		this.apptList.add(apt);

	}

	// Iterate and print the appointments
	public void printAppointment() {

		System.out.println("Doctor ID: " + docId);
		System.out.println("Doctor Name: " + docName);

		for (Appointment appointment : apptList) {
			System.out.println(appointment);
		}

	}

	// Getters and Setters
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public ArrayList<Appointment> getApptList() {
		return apptList;
	}

	public void setApptList(ArrayList<Appointment> apptList) {
		this.apptList = apptList;
	}

}
