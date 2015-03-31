package core;

import java.util.ArrayList;

public class Client implements ClientUi{
	
	// Variables
	private String username;
	private String password;
	
	private TestResults testResults;
	private ArrayList<Booking> bookings;
	
	private ClientDAO clientDAO = null;
	
	//Constructor
	public Client(){
		this.clientDAO = new ClientDAO();
	}
	
	// Getters & setters
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String uname){
		this.username = uname;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
	
	//Client Methods
	public int logIn(){
		int success;
		success = clientDAO.verifyUsername(username, password);
		
		if (success == 1){
			System.out.println("Admin Login Successful!" + success);
			return success;
		}
		else if(success == 2){
			System.out.println("Mechanic Login Successful!" + success);
			return success;
		}
		else{
			System.out.println("Login Failed!" + success);
			return success;
		}
	}
	public void logOut(){
		
		//reset the username and password
		this.username = null;
		this.password = null;
	}
	
	public ArrayList<Booking> getBookings(){
		return this.bookings;
	}

	// Interface Methods
	@Override
	public void createBooking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookings() {
		bookings = new ArrayList<Booking>();
		bookings = clientDAO.getBookings();
		System.out.println("Loaded bookings: "+bookings.size());
		
	}

	@Override
	public void modifyBooking(String RegNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean cancelBooking(String query) {
		boolean success;
		success = clientDAO.executeUpdate(query);
		return success;
	}

	@Override
	public void viewTestResults(String RegNo) {
		testResults = new TestResults();
		testResults = clientDAO.getTestResults(RegNo);
		System.out.println("Alignment: "+testResults.getTestAlignment()+" | Suspension: "+testResults.getTestSuspension()+
				" | Brakes: "+testResults.getTestBrakes()+" | Exhause Emission: "+testResults.getTestEEmission()+
				" | Head Lights: "+testResults.getTestHeadLights());
	}

	@Override
	public void modifyTestResults(String RegNo) {
		// TODO Auto-generated method stub
		
	}
	
}
