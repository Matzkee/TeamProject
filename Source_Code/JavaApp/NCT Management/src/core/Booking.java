package core;

public class Booking {

	// Variables
	private String time;
	private String carReg;
	private String date;
	private String garage;
	private TestResults test;
	
	
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
	public String getGarage() {
		return garage;
	}
	public void setGarage(String garage) {
		this.garage = garage;
	}
	
	// Booking Methods
	public TestResults getTest(){
		return this.test;
	}
	public void setTest(int alignment, int suspension, int brakes, int eEmission, int headLights){
		this.test.setTestAlignment(alignment);
		this.test.setTestSuspension(suspension);
		this.test.setTestBrakes(brakes);
		this.test.setTestEEmission(eEmission);
		this.test.setTestHeadLights(headLights);
	}
	
}
