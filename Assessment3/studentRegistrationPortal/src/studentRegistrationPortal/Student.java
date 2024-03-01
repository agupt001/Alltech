package studentRegistrationPortal;

public class Student {

	// Student Id and name
	private String id;
	private String name;

	// Getter and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Parameterized constructor
	public Student(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	// Modify to string method as shown in the example
	@Override
	public String toString() {
		return id + " " + name;
	}

	public Student() {
	}

}
