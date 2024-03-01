package studentRegistrationPortal;

import java.util.List;

public interface RegPortalInterface {

	// Abstract methods to implement
	public RegistrationPortal getRegistrationPortal();

	public void register(Student student);

	public List<Student> getRegisteredStudents();

}
