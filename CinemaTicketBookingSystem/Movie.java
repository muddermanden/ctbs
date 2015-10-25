package CinemaTicketBookingSystem;

public class Movie {

	private String title;
	private int runtime;

	Movie(String title, int runtime) {
		this.setTitle(title);
		this.setRuntime(runtime);
	}


	private void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	
	private int getRuntime() {
		return this.runtime;
	}


	private void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return title.toUpperCase() + " (" + getRuntime() + " minutes)";
	}
}