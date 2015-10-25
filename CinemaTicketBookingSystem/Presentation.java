package CinemaTicketBookingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Presentation implements Comparable<Presentation> {

	private static int maxPresentationID;
	private Auditorium auditorium;
	private LocalDateTime dateTime;
	private Movie movie;
	private int presentationID;


	Presentation(Movie movie, int year, int month, int day, int hour, int minute) {
		this.presentationID = maxPresentationID = maxPresentationID + 1;
		this.auditorium = new Auditorium();
		setDateTime(year, month, day, hour, minute);
		this.movie = movie;
	}


	@Override
	public int compareTo(Presentation o) {
		if (getDatetime() == null || o.getDatetime() == null) return 0;
		return getDatetime().compareTo(o.getDatetime());
	}

	
	private int getNumberOfAvailableSeats() {
		return getAuditorium().getNumberOfAvailableSeats();
	}
	

	public Auditorium getAuditorium() {
		return this.auditorium;
	}


	public String getDatetime() {
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}


	public Movie getMovie() {
		return this.movie;
	}


	public String getMovieTitle() {
		return getMovie().toString();
	}


	public int getPresentationID() {
		return presentationID;
	}


	private void setDateTime(int year, int month, int day, int hour, int minute) {
		this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
	}


	@Override
	public String toString() {
		return String.format("%2d) %s - %s (%d available seats)", getPresentationID(), getDatetime(), getMovie(), getNumberOfAvailableSeats());
	}
}