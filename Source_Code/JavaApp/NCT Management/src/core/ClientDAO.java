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
	private ArrayList<TestResults> tests;
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
		tests = new ArrayList<TestResults>();
		users = new ArrayList<User>();
	}
	
	
	// ClientDAO Methods
	public ArrayList<Booking> getBookings(int garage){
		//temporary variables
		bookings = new ArrayList<Booking>();
		Booking booking = null;
		int count = 0;
		
		try{
			String query;
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT * FROM Booking WHERE DATEDIFF(BDate,curdate()) < 60 AND Garage_Id =? ORDER BY BDate";
			this.ps = conn.prepareStatement(query);
			ps.setInt(1, garage);
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				booking = new Booking(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				bookings.add(booking);
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
			return bookings;
		}
		
	}
	public ArrayList<TestResults> getTestResults(String RegNo){
		// Temporary variables
		String query;
		tests = new ArrayList<TestResults>();
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
				TestResults tempTest;
				tempTest = new TestResults();
				tempTest.setTestAlignment(rs.getInt(3));
				tempTest.setTestSuspension(rs.getInt(4));
				tempTest.setTestBrakes(rs.getInt(5));
				tempTest.setTestEEmission(rs.getInt(6));
				tempTest.setTestHeadLights(rs.getInt(7));
				tempTest.setTestDate(rs.getString(8));
				tests.add(tempTest);
				count += 1;
			}
			ps.close();
			conn.close();
		}
		catch(SQLException e){
					
			// Temporary System message
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
				
		if(count == 0){
			return null;
		}
		else{
			return tests;
		}

	}
	public ArrayList<User> getAllUsers(){
		// Temporary variables
		String query;
		int count = 0;
		User tempUser;
		users = new ArrayList<User>();
		//query the database for the user with these credentials 
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			query = "SELECT Name FROM Users";
			this.ps = conn.prepareStatement(query);
			//Execute Query
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				tempUser = new User();
				tempUser.setName(rs.getString(3));
				users.add(tempUser);
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
		
		if(count == 0){
			return null;
		}
		else{
			return users;
		}
	}
	// Interface MEthods
	@Override
	public int[] verifyUsername(String uname, String pass) {
		
		// Temporary variables
		String query;
		int[] user = {0,0,0};
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
				user[0] = rs.getInt(1); // User Id
				user[1] = rs.getInt(2); // User Type
				user[2] = rs.getInt(6); // Garage Id
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
			return user;
			}
		else if (count > 1){
			System.out.println("Duplicate users within database");
			return user;
		}
		else{
			System.out.println("Username and password do not match");
			return user;
			}
	}
	@Override
	public boolean executeUpdate(String queryS) {

		//query the database for the user with these credentials 
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" );
			// Get a connection to the database
			this.conn = DriverManager.getConnection("jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123");
			//Prepare statement
			stmt = conn.createStatement();
			stmt.executeUpdate(queryS);
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
