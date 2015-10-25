package CinemaTicketBookingSystem;

public class Main {

	private static Auditorium auditorium;
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

		// create an auditorium
		Main.auditorium = new Auditorium(12, 10);

		// create some movies
		Movie terminator1 = new Movie("Terminator I");
		Movie terminator2 = new Movie("Terminator II");
		Movie frida = new Movie("Frække Frida");

		// create some presentations of the movies
		Presentation p1 = new Presentation(Main.auditorium, terminator1);
		p1.setDateTime(2015, 11, 20, 15, 30);
		Presentation p2 = new Presentation(Main.auditorium, terminator2);
		p2.setDateTime(2015, 11, 21, 15, 30);
		Presentation p3 = new Presentation(Main.auditorium, frida);
		p3.setDateTime(2015, 11, 19, 11, 30);
		Presentation p4 = new Presentation(Main.auditorium, frida);
		p4.setDateTime(2015, 11, 25, 11, 30);
		Presentation p5 = new Presentation(Main.auditorium, terminator1);
		p5.setDateTime(2015, 11, 26, 11, 30);

		// add the movies to the movie schedule
		Main.movieSchedule.addPresentationToSchedule(p1);
		Main.movieSchedule.addPresentationToSchedule(p2);
		Main.movieSchedule.addPresentationToSchedule(p3);
		Main.movieSchedule.addPresentationToSchedule(p4);
		Main.movieSchedule.addPresentationToSchedule(p5);
	}
}