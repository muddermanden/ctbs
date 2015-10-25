package CinemaTicketBookingSystem;

import java.util.*;

public class Row {

	private int rowNumber;
	private ArrayList<Seat> seats;


	Row(int rowNumber) {
		setRowNumber(rowNumber);
		seats = new ArrayList<Seat>();
	}


	/**
	 * Get a specific row by its row number.
	 * 
	 * @return Row object.
	 */
	public int getRowNumber() {
		return rowNumber;
	}


	/**
	 * Get a specific seat by its seat number.
	 * 
	 * @param seatNumber
	 *            as integer.
	 * @return Seat object.
	 * @throws WrongUserInputException
	 *             when seat number is not in the row.
	 */
	public Seat getSeat(int seatNumber) throws WrongUserInputException {
		try {
			return getSeats().get(seatNumber - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new WrongUserInputException("Sorry, the seat does not exist.");
		}
	}


	/**
	 * Get the collection of seats in the row.
	 * 
	 * @return ArrayList of Seat objects.
	 */
	public ArrayList<Seat> getSeats() {
		return this.seats;
	}


	/**
	 * 
	 * @param rowNumber
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

}