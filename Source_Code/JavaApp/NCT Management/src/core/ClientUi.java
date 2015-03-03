package core;

public interface ClientUi {
	
	public void createBooking();
	public void viewBookings();
	public void modifyBooking(String RegNo);
	public void cancelBooking(String RegNo);
	public void viewTestResults(String RegNo);
	public void modifyTestResults(String RegNo);

}
