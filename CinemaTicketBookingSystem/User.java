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


	protected String getPassword()
	{
		return this.password;
	}


	/**
	 * Default constructor.
	 */
	protected User()
	{
		// empty
	}


	/**
	 * Creates a user with username and password.
	 * 
	 * @param username The username the user is created with.
	 * @param password The password the user is created with.
	 */
	protected User(String username, String password)
	{
		this(); // call the default constructor
		this.username = username;
		this.password = password;
	}

}
