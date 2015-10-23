package CinemaTicketBookingSystem;

public class Movie {

	private String title;

	Movie(String title) {
		this.setTitle(title);
	}
	
	public String getTitle() {
		return this.title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}