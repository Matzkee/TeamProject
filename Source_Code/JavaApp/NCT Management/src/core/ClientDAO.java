package core;

import java.util.ArrayList;

public class ClientDAO implements DaoUi{

	//Variables
	private ArrayList<Booking> bookings;
	private TestResults tests;
	private ArrayList<User> users;
	
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
	public boolean connectToDB() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean verifyUsername(String uname, String pass) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean executeQuery(String queryS) {
		// TODO Auto-generated method stub
		return false;
	}
}
