package CinemaTicketBookingSystem;

import java.util.*;

/**
 * 
 * The MovieSchedule object contains a list of presentations that are scheduled
 * to show in the cinema. Only one MovieSchedule instance can exist. This is
 * achieved by using a Singleton design pattern.
 *
 */
public class MovieSchedule
{

	// This static field contains the movie schedule.
	private static MovieSchedule movieSchedule = new MovieSchedule();


	/**
	 * Get the instance of MovieSchedule
	 * 
	 * @return the MovieSchedule object
	 */
	public static MovieSchedule getInstance()
	{
		return movieSchedule;
	}

	private ArrayList<Presentation> presentations = new ArrayList<Presentation>();


	// A private constructor restricts the use of 'new' from outside the class
	// itself
	private MovieSchedule()
	{
		// Exists only to defeat instantiation
	}


	/**
	 * Adds a Presentation to the MovieSchedule.
	 * 
	 * @param movie The Movie that will be displayed on the Presentation.
	 * @param year The year part of the date when the Presentation is displayed.
	 * @param month The month part of the date when the Presentation is displayed.
	 * @param day The day part of the date when the Presentation is displayed.
	 * @param hour expects an integer. The hour of the day when the Presentation is displayed.
	 * @param minute expects an integer. The minute of the hour when the Presentation is displayed.
	 */
	public void addPresentationToSchedule(Movie movie, int year, int month, int day, int hour, int minute)
	{
		Presentation presentation = new Presentation(movie, year, month, day, hour, minute);
		this.presentations.add(presentation);

		// sort the collection of Presentations (by date)
		Collections.sort(this.presentations);
	}


	private void displayMovieSchedule()
	{
		System.out.println("Movie Presentation Schedule:");

		for (int i = 0; i < movieSchedule.getPresentations().size(); i++)
		{
			System.out.println(movieSchedule.getPresentation(i));
		}
	}
	
	
	public Presentation displayPresentationMenu()
	{
		// the users choice
		Presentation myPresentation = null;

		// ask the movie schedule to display a schedule
		displayMovieSchedule();

		// ask the user to enter a number of choice
		int input = Utility.promptUserInputInteger("Enter the number of the presentation you will see:");

		// get the presentation with the ID the user entered
		myPresentation = matchPresentation(input);

		return myPresentation;

	}


	private Presentation getPresentation(int index)
	{
		return getPresentations().get(index);
	}


	private ArrayList<Presentation> getPresentations()
	{
		return this.presentations;
	}


	private Presentation matchPresentation(int presentationID)
	{
		Presentation match = null;
		for (Presentation presentation : getPresentations())
		{
			if (presentation.getPresentationID() == presentationID)
			{
				match = presentation;
				break;
			}
		}
		return match;
	}
}