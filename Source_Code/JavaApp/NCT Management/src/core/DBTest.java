package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * This is a test class
 * Any tests on the system should be done within this class
 * Any functionality you consider important, is working or still not implemented
 * should be commented out.
 * 
 *  -Mateusz Pietraszewski
 */

public class DBTest {/*
	public static void main( String args[] )
	{
		String userName, password;
		userName = "admin";
		password = "admin";
		
		Client testClient = new Client();
		testClient.setUsername(userName);
		testClient.setPassword(password);
		testClient.logIn();
		/*
		try{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" ) ;

			// Get a connection to the database
			Connection conn = DriverManager.getConnection( "jdbc:mysql://83.212.127.2:3306/NCT", "user", "TeamGravity123" ) ;



			// Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			
			//Sample Delete Statement
			//stmt.executeUpdate("DELETE FROM Owner WHERE Owner_Id IN (222222);");
			
			// Execute the query
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Owner" ) ;

			// Loop through the result set
			while( rs.next() )
				System.out.println( rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)) ;

			// Close the result set, statement and the connection
			rs.close() ;
			stmt.close() ;
			conn.close() ;
			
		}
		catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
	}*/
}
