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
	public int[] logIn(){
		int[] success;
		success = clientDAO.verifyUsername(username, password);
		
		if (success[0] == 1){
			System.out.println("Admin Login Successful!" + success[0]);
			return success;
		}
		else if(success[0] == 2){
			System.out.println("Mechanic Login Successful!" + success[0]);
			return success;
		}
		else{
			System.out.println("Login Failed!" + success[0]);
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
	public boolean createBooking(String carReg, String date, String time, int garage) {
		boolean success;
		success = clientDAO.addBooking(carReg, date, time, garage);
		return success;
	}

	@Override
	public void viewBookings() {
		bookings = new ArrayList<Booking>();
		bookings = clientDAO.getBookings();
		System.out.println("Loaded bookings: "+bookings.size());
		
	}

	@Override
	public boolean modifyBooking(String RegNo) {
		boolean success;
		// Implement: query String
		String query = "";
		success = clientDAO.executeUpdate(query);
		return success;
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
