package core;

import java.util.ArrayList;

public class Client implements ClientUi{
	
	// Variables
	private String username;
	private String password;
	
	private ArrayList<TestResults> testResults;
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
		
		if (success[1] == 1){
			System.out.println("Admin Login Successful!" + success[0]);
			return success;
		}
		else if(success[1] == 2){
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
	public ArrayList<TestResults> getTestResults(){
		return this.testResults;
	}

	// Interface Methods
	@Override
	public boolean createBooking(String query) {
		boolean success;
		success = clientDAO.executeUpdate(query);
		return success;
	}

	@Override
	public void viewBookings(int garage) {
		bookings = new ArrayList<Booking>();
		bookings = clientDAO.getBookings(garage);
		if(bookings != null){
			System.out.println("Loaded bookings: "+bookings.size());			
		}
		else{
			System.out.println("There are no bookings to load");
		}
	}

	@Override
	public boolean modifyBooking(String query) {
		boolean success;
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
	public boolean viewTestResults(String RegNo) {
		testResults = new ArrayList<TestResults>();
		testResults = clientDAO.getTestResults(RegNo);
		if(testResults != null){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean modifyTestResults(String query) {
		boolean success;
		success = clientDAO.executeUpdate(query);
		return success;
	}

	@Override
	public boolean addTestResults(String query) {
		boolean success;
		success = clientDAO.executeUpdate(query);
		return success;
	}

	@Override
	public boolean connectionTest() {
		boolean success;
		success = clientDAO.testConnection();
		return success;
	}
	
}
