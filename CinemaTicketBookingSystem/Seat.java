package CinemaTicketBookingSystem;

public class Seat {

	private int seatNumber;
	private boolean isBooked;
	
	
	Seat(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
	public int getSeatNumber() {
		return this.seatNumber;
	}

	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
	public boolean isBooked() {
		return this.isBooked;
	}
	
	public void isBooked(boolean input) throws WrongUserInputException {
		if (this.isBooked) {
			throw new WrongUserInputException("Sorry, the seat is not available.");
		} else {
			this.isBooked = input;
		}
	}
}