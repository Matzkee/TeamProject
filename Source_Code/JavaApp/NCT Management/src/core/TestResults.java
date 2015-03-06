package core;

public class TestResults {
	
	// Variables
	private String testAlignment;
	private String testSuspension;
	private String testBrakes;
	private String testEEmission;
	private String testHeadLights;
	
	// Getters & Setters
	public String getTestAlignment() {
		return testAlignment;
	}
	public void setTestAlignment(int testAlignment) {
		if (testAlignment == 1)
			this.testAlignment = "passed";
		else
			this.testAlignment = "failed";
	}
	public String getTestSuspension() {
		return testSuspension;
	}
	public void setTestSuspension(int testSuspension) {
		if (testSuspension == 1)
			this.testSuspension = "passed";
		else
			this.testSuspension = "failed";
	}
	public String getTestBrakes() {
		return testBrakes;
	}
	public void setTestBrakes(int testBrakes) {
		if (testBrakes == 1)
			this.testBrakes = "passed";
		else
			this.testBrakes = "failed";
	}
	public String getTestEEmission() {
		return testEEmission;
	}
	public void setTestEEmission(int testEEmission) {
		if (testEEmission == 1)
		this.testEEmission = "passed";
	else
		this.testEEmission = "failed";
	}
	public String getTestHeadLights() {
		return testHeadLights;
	}
	public void setTestHeadLights(int testHeadLights) {
		if (testHeadLights == 1)
			this.testHeadLights = "passed";
		else
			this.testHeadLights = "failed";
	}

}
