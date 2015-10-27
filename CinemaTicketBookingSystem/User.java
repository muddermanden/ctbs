package CinemaTicketBookingSystem;

public abstract class User 
{
	private String username;
	private String password;
	
	
	protected void setUsername(String username) 
	{
		this.username = username; 
	}
	
	protected String getUsername()
	{
		return this.username;
	}
	
	protected void setPassword(String password)
	{
		this.password = password;
	}

	protected String getPassword() {
		return this.password;
	}

	protected User(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}

	protected User() 
	{
		// this is empty!
	}
}
