package dao;

import java.util.Scanner;

import entity.Book;

public interface BookDAO {

	// Create CRUD abstract methods for implementations
	public void create(Scanner sc);
	public Book read(Scanner sc);
	public void readAll();
	public void update(Scanner sc);
	public void delete(Scanner sc);
	
}
