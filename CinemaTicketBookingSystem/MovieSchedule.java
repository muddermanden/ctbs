package CinemaTicketBookingSystem;

import java.util.*;

/**
 * 
 * The MovieSchedule object contains a list of presentations that are scheduled
 * to show in the cinema. Only one MovieSchedule instance can exist. This is
 * achieved by using a Singleton design pattern.
 *
 */
public class MovieSchedule {

	// This static field contains the movie schedule.
	private static MovieSchedule movieSchedule = new MovieSchedule();


	/**
	 * Get the instance of MovieSchedule
	 * 
	 * @return the MovieSchedule object
	 */
	public static MovieSchedule getInstance() {
		return movieSchedule;
	}

	private ArrayList<Presentation> presentations = new ArrayList<Presentation>();


	// A private constructor restricts the use of new from outside the class
	// itself
	private MovieSchedule() {
		// Exists only to defeat instantiation
	}


	/**
	 * Add a Presentation object to the MovieSchedule
	 * 
	 * @param presentation
	 *            as a Presentation object
	 */
	public void addPresentationToSchedule(Movie movie, int year, int month, int day, int hour, int minute) {
		Presentation presentation = new Presentation(movie, year, month, day, hour, minute);
		this.presentations.add(presentation);
		Collections.sort(this.presentations);
	}


	public void displayMovieSchedule() {
		System.out.println("Movie Presentation Schedule:");

		for (int i = 0; i < movieSchedule.getPresentations().size(); i++) {
			System.out.println(movieSchedule.getPresentation(i));
		}
	}


	private Presentation getPresentation(int index) {
		return getPresentations().get(index);
	}


	private ArrayList<Presentation> getPresentations() {
		return this.presentations;
	}


	public Presentation matchPresentation(int presentationID) {
		Presentation match = null;
		for (Presentation presentation : getPresentations()) {
			if (presentation.getPresentationID() == presentationID) {
				match = presentation;
			}
		}
		return match;
	}
}