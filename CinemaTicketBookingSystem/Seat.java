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

	
	public boolean getIsBooked() {
		return this.isBooked;
	}

	
	public void setIsBooked(boolean isBooked) throws WrongUserInputException {
		if (this.isBooked) {
			throw new WrongUserInputException("Sorry, the seat is not available.");
		} else {
			this.isBooked = isBooked;
		}
	}
}