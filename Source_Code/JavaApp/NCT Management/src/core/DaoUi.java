package core;

public interface DaoUi {
	
	public boolean connectToDB();
	public boolean verifyUsername(String uname, String pass);
	public void closeConnection();
	public boolean executeQuery(String queryS);
	
}
