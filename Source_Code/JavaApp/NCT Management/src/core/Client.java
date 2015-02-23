package core;

import java.util.ArrayList;

public class Client {
	
	// Variables
	private String username;
	private String password;
	
	private ArrayList<TestResults> testResults = new ArrayList<TestResults>();
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	private ClientDAO clientDAO;
	
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
	public void logIn(){
		
	}
	public void logOut(){
		
	}
	
}
