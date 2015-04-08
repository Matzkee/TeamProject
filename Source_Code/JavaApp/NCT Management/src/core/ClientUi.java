package core;

public interface ClientUi {
	
	public boolean createBooking(String carReg, String date, String time, int garage);
	public void viewBookings();
	public boolean modifyBooking(String RegNo);
	public boolean cancelBooking(String query);
	public boolean viewTestResults(String RegNo);
	public boolean modifyTestResults(String RegNo);

}
