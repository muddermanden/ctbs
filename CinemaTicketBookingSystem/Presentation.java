package CinemaTicketBookingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Presentation implements Comparable<Presentation> {

	private int presentationID;
	public int getPresentationID() {
		return presentationID;
	}

	private static int maxPresentationID;
	private Auditorium auditorium;
	private LocalDateTime dateTime;
	private Movie movie;

	
	Presentation(Auditorium auditorium, Movie movie) {
		this.presentationID = maxPresentationID = maxPresentationID + 1;
		this.auditorium = auditorium;
		this.movie = movie;
	}

	public Auditorium getAuditorium() {
		return this.auditorium;
	}
	
	public Movie getMovie() {
		return this.movie;
	}
	
	public String getMovieTitle() {
		return this.movie.getTitle();
	}
	
	public String getDatetime() {
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	
	public void setDateTime(int year, int month, int day, int hour, int minute) {
		this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
	}

	@Override
	public int compareTo(Presentation o) {
		if (getDatetime() == null || o.getDatetime() == null)
			return 0;
		return getDatetime().compareTo(o.getDatetime());
	}

	@Override
	public String toString() {
		return String.format("%s - %s", this.getDatetime());
	}
	
	public boolean equals(Presentation p) {
		return this.presentationID == p.presentationID;
	}
}