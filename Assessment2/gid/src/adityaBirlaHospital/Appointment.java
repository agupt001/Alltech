package adityaBirlaHospital;

import java.util.Date;

public class Appointment {

	private Date appDate;
	private int noOfPatients;

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public int getNoOfPatients() {
		return noOfPatients;
	}

	// Set the no of patients and throw error
	public void setNoOfPatients(int noOfPatients) throws InvalidDataException {
		if (noOfPatients < 1 || noOfPatients > 15)
			throw new InvalidDataException();
		this.noOfPatients = noOfPatients;
	}

	@Override
	public String toString() {
		return "Appointment Date: " + appDate + ", Number Of Patients: " + noOfPatients;
	}

}
