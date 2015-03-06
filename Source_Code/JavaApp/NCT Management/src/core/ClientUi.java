package core;

public interface ClientUi {
	
	public void createBooking();
	public void viewBookings();
	public void modifyBooking(String RegNo);
	public boolean cancelBooking(String query);
	public void viewTestResults(String RegNo);
	public void modifyTestResults(String RegNo);

}
