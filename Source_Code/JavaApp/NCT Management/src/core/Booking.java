package core;

public class Booking {

	// Variables
	private String carReg;
	private String date;
	private String time;
	private int garage;
	
	//Constructor
	public Booking(String carReg, String date, String time, int garage){
		this.carReg = carReg;
		this.date = date;
		this.time = time;
		this.garage = garage;
	}
	
	
	// Getters & Setters
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCarReg() {
		return carReg;
	}
	public void setCarReg(String carReg) {
		this.carReg = carReg;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getGarage() {
		return garage;
	}
	public void setGarage(int garage) {
		this.garage = garage;
	}
}
