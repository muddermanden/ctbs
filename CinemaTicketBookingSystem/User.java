package CinemaTicketBookingSystem;

import java.util.ArrayList;

public abstract class User
{
	private static ArrayList<User> users = new ArrayList<User>();


	public static void addUser(User user)
	{
		users.add(user);
	}


	public static User login()
	{
		String username = Utility.promptUserInputString("Type username:");
		String password = Utility.promptUserInputString("Enter password:");

		User match = null;
		for (User user : users)
		{
			if (user.getUsername().equals(username) && user.getPassword().equals(password))
			{
				match = user;
			}
		}
		return match;
	}

	
	// private fields
	private String password;
	private String username;


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
	 * @param username
	 *        The username the user is created with.
	 * @param password
	 *        The password the user is created with.
	 */
	protected User(String username, String password)
	{
		this(); // call the default constructor
		this.username = username;
		this.password = password;
	}


	protected String getPassword()
	{
		return this.password;
	}


	protected String getUsername()
	{
		return this.username;
	}
}
