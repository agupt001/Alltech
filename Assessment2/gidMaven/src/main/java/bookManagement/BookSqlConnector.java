package bookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BookSqlConnector {
	
	// Create attributes
	final String URL = "jdbc:mysql://localhost:3306/assess_schema";
	final String USERNAME = "root";
	final String PASSWORD = "root";
	
	// Create a method to return connection
	public Connection getConnection() {
		
		Connection con=null;
		
		try {
			
			// Get connection
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return con;
	}

}
