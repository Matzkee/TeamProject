package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO implements DaoUi{

	//Variables
	private ArrayList<Booking> bookings;
	private TestResults tests;
	private ArrayList<User> users;
	
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public PreparedStatement ps;
	
	//Default Constructor
	public ClientDAO(){
		this.conn = null;
		this.stmt = null;
		this.rs = null;
		bookings = new ArrayList<Booking>();
		tests = new TestResults();
		users = new ArrayList<User>();
	}
	
	
	// ClientDAO Methods
	public ArrayList<Booking> getBookings(){
		//Implement: connect, query all bookings for next 2-7 days, 
		//return results as a list
		
		return this.bookings;
	}
	public TestResults getTestResults(){
		//Implement: connect, query Booking using the registration no &
		//based on that get TestResults, return as a single object
		
		return this.tests;
	}
	public ArrayList<User> getAllUsers(){
		//Implements: connect, query All Users within DB,
		//return as a list
		
		return this.users;
	}
	
	// Interface MEthods
	@Override
	public boolean verifyUsername(String uname, String pass) {
		
		// Temporary variables
		String query, password = null;
		
		//query the database for the user with these credentials 
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT Password FROM User WHERE LoginName = ?";
			this.ps = conn.prepareStatement(query);
			ps.setString(1, uname); // This binds the String to '?'
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				password = rs.getString(1);
			}
			// Check if the passwords match
			if(pass != password)
			{
				stmt.close();
				conn.close();
				return false;
			}
			else
			{
				stmt.close();
				conn.close();
				return true;
			}
		}
		catch(SQLException e){
			
			//Temporary System message
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( e != null ){
				System.out.println( "State  : " + e.getSQLState()  ) ;
				System.out.println( "Message: " + e.getMessage()   ) ;
				System.out.println( "Error  : " + e.getErrorCode() ) ;

				e = e.getNextException() ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	@Override
	public boolean executeUpdate(String queryS) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean testConnection() {
		
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" ) ;

			// Get a connection to the database
			this.conn = DriverManager.getConnection( "jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123" );
			
			// Get a statement from the connection
			Statement stmt = conn.createStatement();
			
			stmt.close();
			conn.close();
			
			return true;
		}
		
		catch(SQLException e){
			
			//Temporary System message
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( e != null ){
				System.out.println( "State  : " + e.getSQLState()  ) ;
				System.out.println( "Message: " + e.getMessage()   ) ;
				System.out.println( "Error  : " + e.getErrorCode() ) ;

				e = e.getNextException() ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
