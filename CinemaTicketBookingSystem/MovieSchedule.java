package CinemaTicketBookingSystem;

import java.util.*;

/**
 * 
 * The MovieSchedule object contains a list of presentations that are scheduled to show in the cinema.
 * Only one MovieSchedule instance can exist. This is achieved by using a Singleton design pattern.
 *
 */
public class MovieSchedule{

	// This static field contains the movie schedule.
	private static MovieSchedule movieSchedule = new MovieSchedule();

	private ArrayList<Presentation> presentations = new ArrayList<Presentation>();

	// A private constructor restricts the use of new from outside the class itself
	private MovieSchedule() {
		// Exists only to defeat instantiation
	}
	
	/**
	 * Get the instance of MovieSchedule
	 * @return the MovieSchedule object
	 */
	public static MovieSchedule getInstance() {
		return movieSchedule;
	}

	/**
	 * Add a Presentation object to the MovieSchedule
	 * @param presentation as a Presentation object
	 */
	public void addPresentationToSchedule(Presentation presentation) {
		this.presentations.add(presentation);
		Collections.sort(this.presentations);
	}
	
	
	public Presentation selectPresentationMenu() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What would you like to see?");

		for (int i = 0; i < getInstance().presentations.size(); i++) {
			Presentation p = getInstance().presentations.get(i);
			System.out.printf("%2d) %s - %s\n", p.getPresentationID(), p.getDatetime(), p.getMovie());
		}

		System.out.println(" 0) Exit");
		System.out.print("Enter choice: ");
		int input = scanner.nextInt();
		
		Presentation userChoice = null;
		for(Presentation p: getInstance().presentations) {
			if (p.getPresentationID() == input) {
				userChoice = p;
			}
		}
		
		System.out.printf("You have selected the presentation of %s on %s\n", userChoice.getMovieTitle(), userChoice.getDatetime());
		
		//scanner.close();
		return userChoice;
	}
}