package core;

import java.util.ArrayList;

public class Client implements ClientUi{
	
	// Variables
	private String username;
	private String password;
	
	private TestResults testResults;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	
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
	public void logIn(String uname, String pass){
		clientDAO.verifyUsername(uname, pass);
	}
	public void logOut(){
		
	}

	// Interface Methods
	@Override
	public void createBooking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBooking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyBooking(String RegNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(String RegNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewTestResults(String RegNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyTestResults(String RegNo) {
		// TODO Auto-generated method stub
		
	}
	
}
