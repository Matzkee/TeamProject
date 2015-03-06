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
	public boolean logIn(){
		boolean success;
		success = clientDAO.verifyUsername(username, password);
		
		if (success == true){
			System.out.println("Login Successful!" + success);
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

	// Interface Methods
	@Override
	public void createBooking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookings() {
		bookings = new ArrayList<Booking>();
		Booking booking;
		bookings = clientDAO.getBookings();
		for(int i = 0; i < bookings.size(); i++)
		{
			booking = bookings.get(i);
			System.out.println("Car reg: "+booking.getCarReg()+" | Date: "+booking.getDate()+" | Time: "+booking.getTime());
		}
		
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
