package core;

public interface DaoUi {

	public int[] verifyUsername(String uname, String pass);
	public boolean executeUpdate(String queryS);
	public boolean testConnection();
	
}
