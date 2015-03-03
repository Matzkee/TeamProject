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
		
		//temporary variables
		Booking booking = null;
		int count = 0;
		
		try{
			String query;
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT * FROM Booking WHERE DATEDIFF(BDate,curdate()) < 50";
			this.ps = conn.prepareStatement(query);
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				booking = new Booking();
				booking.setCarReg(rs.getString(2));
				booking.setDate(rs.getString(3));
				booking.setTime(rs.getString(4));
				bookings.add(booking);
				count ++;
			}
			ps.close();
			conn.close();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(count == 0)
		{
			return null;
		}
		else
		{
			return bookings;
		}
		
	}
	public TestResults getTestResults(String RegNo){
		// Temporary variables
		String query;
		int count = 0;
		//query the database for the user with these credentials 
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT * FROM TestResults WHERE Car_Reg=?";
			this.ps = conn.prepareStatement(query);
			ps.setString(1, RegNo); // This binds the String to '?'
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				tests = new TestResults();
				tests.setTestAlignment(rs.getInt(3));
				tests.setTestSuspension(rs.getInt(4));
				tests.setTestBrakes(rs.getInt(5));
				tests.setTestEEmission(rs.getInt(6));
				tests.setTestHeadLights(rs.getInt(7));
				count += 1;
			}
			ps.close();
			conn.close();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		if(count == 0)
		{
			return null;
		}
		else
		{
			return tests;
		}

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
		String query;
		int count = 0;
		//query the database for the user with these credentials 
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT * FROM Users WHERE LoginName =? and Password =?";
			this.ps = conn.prepareStatement(query);
			ps.setString(1, uname); // This binds the String to '?'
			ps.setString(2, pass);
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				count += 1;
				System.out.println("Found user in database ");
			}
			ps.close();
			conn.close();
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Check if the user was found
		if(count == 1){
			System.out.println("Found user within database");
			return true;
			}
		else if (count > 1)
		{
			System.out.println("Duplicate users within database");
			return false;
		}
		else{
			System.out.println("Username and password do not match");
			return false;
			}
	}
	@Override
	public boolean executeUpdate(String queryS) {

		
		
		return false;
	}


	@Override
	public boolean testConnection() {
		
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" ) ;

			// Get a connection to the database
			this.conn = DriverManager.getConnection( "jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123" );
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
