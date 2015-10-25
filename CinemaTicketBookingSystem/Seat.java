package CinemaTicketBookingSystem;

public class Seat {

	private boolean isBooked;
	private int seatNumber;
	private Row row;


	Seat(Row row, int seatNumber) {
		setRow(row);
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

	
	private void setRow(Row row) {
		this.row = row;
	}
	
	
	private Row getRow() {
		return this.row;
	}
	
	public int getRowNumber() {
		return this.getRow().getRowNumber();
	}
	

	private void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	public int getSeatNumber() {
		return this.seatNumber;
	}
}