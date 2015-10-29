package CinemaTicketBookingSystem;

public class Main
{
	private static MovieSchedule movieSchedule;


	/**
	 * Starts the program.
	 * 
	 * @param args
	 *        The arguments passed to the program. Takes no arguments.
	 */
	public static void main(String[] args)
	{
		// setup the application with mock data for demonstration purpose
		createMockData();

		User user = User.login();
		if (user instanceof Customer)
		{
			Booking myBooking = new Booking((Customer) user);
			myBooking.startNewBooking();
		}
		else if (user instanceof Employee)
		{
			((Employee) user).checkBooking();
		}
		else
		{
			System.out.println("Unknown username or password.");
		}
	}


	public static void createMockData()
	{
		// get the movie schedule instance
		movieSchedule = MovieSchedule.getInstance();

		// create some movies
		Movie terminator1 = new Movie("The Terminator", 107);
		Movie terminator2 = new Movie("Terminator 2: Judgment Day", 137);
		Movie frida = new Movie("Frække Frida og de frygtløse spioner", 75);

		// create some presentations of the movies
		movieSchedule.addPresentationToSchedule(terminator1, 2015, 11, 20, 15, 30);
		movieSchedule.addPresentationToSchedule(terminator2, 2015, 11, 21, 15, 30);
		movieSchedule.addPresentationToSchedule(frida, 2015, 11, 19, 11, 30);
		movieSchedule.addPresentationToSchedule(frida, 2015, 11, 25, 11, 30);
		movieSchedule.addPresentationToSchedule(terminator1, 2015, 11, 26, 11, 30);

		User.addUser(new Customer("John", "1234"));
		User.addUser(new Employee("Jane", "abc"));
	}
}