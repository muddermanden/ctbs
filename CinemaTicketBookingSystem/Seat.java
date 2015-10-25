package CinemaTicketBookingSystem;

public class Seat {

	private boolean isBooked;
	private int seatNumber;


	Seat(int seatNumber) {
		setSeatNumber(seatNumber);
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


	private void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}