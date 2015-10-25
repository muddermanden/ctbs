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
	
	public ArrayList<Presentation> getPresentations() {
		return this.presentations;
	}
	
	public Presentation getPresentation(int index) {
		return this.getPresentations().get(index);
	}
	
	public void displayMovieSchedule() {
		System.out.println("Movie Schedule:");

		for (int i = 0; i < movieSchedule.getPresentations().size(); i++) {
			Presentation p = movieSchedule.getPresentation(i);
			System.out.printf("%2d) %s - %s\n", p.getPresentationID(), p.getDatetime(), p.getMovie());
		}
	}
	
	public Presentation matchPresentation(int presentationID) {
		Presentation match = null;
		for(Presentation presentation: this.getPresentations()) {
			if(presentation.getPresentationID() == presentationID) {
				match = presentation;
			}
		}
		return match;
	}
}