package studentRegistrationPortal;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPortal implements RegPortalInterface, Runnable {

	private List<Student> stdList = new ArrayList<Student>();
	// Store the object in static and final to provide singleton functionality
	private static final RegistrationPortal regPortalInstance = new RegistrationPortal();
	private Student studentToRegister; // Student to register by this thread

	private RegistrationPortal() {
	} // Private to keep the singleton nature

	@Override
	public RegistrationPortal getRegistrationPortal() {

		return regPortalInstance;

	}

	// Synchronized method to ensure thread safety
	@Override
	public synchronized void register(Student student) {

		// Sleep added to thread to check the functionality
		// Uncomment to check the thread in action
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		stdList.add(student);

	}

	// Get student list
	@Override
	public List<Student> getRegisteredStudents() {

		return stdList;

	}

	// Setter method for studentToRegister
	public void setStudentToRegister(Student student) {
		this.studentToRegister = student;
	}

	@Override
	public void run() {

		// Register the students in thread
		if (studentToRegister != null) {
			register(studentToRegister);

		}

	}

	// Get the only Singleton object using static
	public static RegistrationPortal getRegPortal() {

		return regPortalInstance;

	}

}
