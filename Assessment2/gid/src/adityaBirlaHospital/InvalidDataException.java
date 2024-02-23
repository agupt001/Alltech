package adityaBirlaHospital;

@SuppressWarnings("serial")
public class InvalidDataException extends Exception {

	// Print the error msg
	public InvalidDataException() {
		super("Number of patients should be between 1 to 15");
	}
	
}
