package bookManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookDAOImpl implements BookDAO{
	
	// Establish connection one time to use every time
	final BookSqlConnector bsc = new BookSqlConnector();
	final Connection con = bsc.getConnection();

	@Override
	public void create(Scanner sc) throws InputMismatchException {
		
		try {
			
			// Generate query for creation of records
			String query = "Insert into books(book_id, book_title, book_author) values(?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			// Get details from user
			System.out.println("Enter Book ID");
			int bookID = sc.nextInt();
			
			System.out.println("Enter Book Title");
			String bookTitle = sc.next();
			
			System.out.println("Enter Book Author");
			String bookAuthor = sc.next();
			
			// Set parameter values in the query
			ps.setInt(1, bookID);
			ps.setString(2, bookTitle);
			ps.setString(3, bookAuthor);
			
			// Execute query and store in a variable
			int rs = ps.executeUpdate();
			
			// Print for user
			if(rs==1)
				System.out.println("Record created successfully!");
			else
				System.out.println("Record was not created!");
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void read(Scanner sc) throws InputMismatchException {
		
		try {
			
			// Generate query for reading records
			String query = "Select * from books where book_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			// Get book id
			System.out.println("Enter Book ID");
			int bookID = sc.nextInt();
			
			ps.setInt(1, bookID);
			
			ResultSet rs = ps.executeQuery();
			
			// Loop over rs to print book details
			while(rs.next()) {
				System.out.println("Book ID: "+rs.getInt("book_id"));
				System.out.println("Book Title: "+rs.getString("book_title"));
				System.out.println("Book Author: "+rs.getString("book_author"));
			}
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void readAll() {
		
		try {
			
			// Query
			String query = "Select * from books";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			System.out.println("*** Book Details ***");
			System.out.println("");
			
			// Loop over to print results
			while(rs.next()) {
				System.out.println("Book ID: "+rs.getInt("book_id"));
				System.out.println("Book Title: "+rs.getString("book_title"));
				System.out.println("Book Author: "+rs.getString("book_author"));
			}
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Scanner sc) throws InputMismatchException {
		
		try {
			
			// Query
			String query = "Update books set book_title=?, book_author=? where book_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			// Get details from user
			System.out.println("Enter Book ID to Update");
			int bookID = sc.nextInt();
			
			System.out.println("Enter new Book Title");
			String bookTitle = sc.next();
			
			System.out.println("Enter new Book Author");
			String bookAuthor = sc.next();
			
			// Set the parameters in query
			ps.setInt(3, bookID);
			ps.setString(1, bookTitle);
			ps.setString(2, bookAuthor);
			
			int rs = ps.executeUpdate();
			
			// Print msg for user
			if(rs==1)
				System.out.println("Record was updated successfully!");
			else
				System.out.println("Record was not updated!");
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Scanner sc) throws InputMismatchException {
		
		try {
			
			// Query
			String query = "Delete from books where book_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			// Get id from user
			System.out.println("Enter Book ID to Delete");
			int bookID = sc.nextInt();
			
			ps.setInt(1, bookID);
			
			int rs = ps.executeUpdate();
			
			// Display msg to user
			if(rs==1)
				System.out.println("Record was deleted successfully!");
			else
				System.out.println("Record was not deleted!");
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
