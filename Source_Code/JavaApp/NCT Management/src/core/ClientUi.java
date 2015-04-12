package core;

public interface ClientUi {
	
	public boolean createBooking(String query);
	public void viewBookings();
	public boolean modifyBooking(String RegNo);
	public boolean cancelBooking(String query);
	public boolean viewTestResults(String RegNo);
	public boolean modifyTestResults(String RegNo);

}
