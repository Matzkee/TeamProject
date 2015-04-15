package core;

public interface ClientUi {
	
	public boolean createBooking(String query);
	public void viewBookings();
	public boolean modifyBooking(String query);
	public boolean cancelBooking(String query);
	public boolean addTestResults(String query);
	public boolean viewTestResults(String query);
	public boolean modifyTestResults(String query);

}
