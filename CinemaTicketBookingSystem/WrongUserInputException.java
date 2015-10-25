package CinemaTicketBookingSystem;

@SuppressWarnings("serial")
public class WrongUserInputException extends Exception {

	// TODO: Change class to inherit from Error instead of Exception.
	
	public WrongUserInputException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(this.getMessage());
	}
}
