package CinemaTicketBookingSystem;

public class Main {

	private static MovieSchedule movieSchedule;


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup the application with mock data for demonstration purpose
		Main.setupMockData();

		Booking myBooking = new Booking();
		myBooking.startNewBooking();
	}


	public static void setupMockData() {

		// get the movie schedule instance
		Main.movieSchedule = MovieSchedule.getInstance();

		// create some movies
		Movie terminator1 = new Movie("Terminator I");
		Movie terminator2 = new Movie("Terminator II");
		Movie frida = new Movie("Frække Frida");

		// create some presentations of the movies
		movieSchedule.addPresentationToSchedule(terminator1, 2015, 11, 20, 15, 30);
		movieSchedule.addPresentationToSchedule(terminator2, 2015, 11, 21, 15, 30);
		movieSchedule.addPresentationToSchedule(frida, 2015, 11, 19, 11, 30);
		movieSchedule.addPresentationToSchedule(frida, 2015, 11, 25, 11, 30);
		movieSchedule.addPresentationToSchedule(terminator1, 2015, 11, 26, 11, 30);
	}
}