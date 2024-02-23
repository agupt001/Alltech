package bookManagement;

import java.util.Scanner;

public interface BookDAO {

	// Abstract CRUD methods for implementations
	public void create(Scanner sc);
	public void read(Scanner sc);
	public void readAll();
	public void update(Scanner sc);
	public void delete(Scanner sc);
	
}
