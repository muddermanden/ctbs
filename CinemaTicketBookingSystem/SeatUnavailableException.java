package CinemaTicketBookingSystem;

public class SeatUnavailableException extends Exception {

	public SeatUnavailableException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.err.println(this.getMessage());
	}
}
