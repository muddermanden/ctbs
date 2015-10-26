package CinemaTicketBookingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Presentation implements Comparable<Presentation>
{

	private static int maxPresentationID;
	private Auditorium auditorium;
	private LocalDateTime dateTime;
	private Movie movie;
	private int presentationID;


	Presentation(Movie movie, int year, int month, int day, int hour, int minute)
	{
		this.presentationID = maxPresentationID = maxPresentationID + 1;
		this.auditorium = new Auditorium();
		setDateTime(year, month, day, hour, minute);
		this.movie = movie;
	}


	@Override
	public int compareTo(Presentation other)
	{
		// check that both Presentation objects have a date/time before
		// comparing
		if (getDatetime() == null || other.getDatetime() == null) throw new NullPointerException();

		// compare the date/time of the Presentations
		return getDatetime().compareTo(other.getDatetime());
	}


	public Auditorium getAuditorium()
	{
		return this.auditorium;
	}


	public String getDatetime()
	{
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}


	public Movie getMovie()
	{
		return this.movie;
	}


	public String getMovieTitle()
	{
		return getMovie().toString();
	}


	private int getNumberOfAvailableSeats()
	{
		return getAuditorium().getNumberOfAvailableSeats();
	}


	public int getPresentationID()
	{
		return presentationID;
	}


	private void setDateTime(int year, int month, int day, int hour, int minute)
	{
		this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
	}


	@Override
	public String toString()
	{
		return String.format("%2d) %s - %-50s - %d seats left", getPresentationID(), getDatetime(), getMovie(),
				getNumberOfAvailableSeats());
	}
}