package CinemaTicketBookingSystem;

import java.util.ArrayList;

public class Auditorium {

	/**
	 * private instance variables
	 */
	private final int numberOfRowsInAuditorium;
	private final int numberOfSeatsInEachRow;
	private final ArrayList<Row> rowsInAuditorium;
	private final int wallSize;
	
	// constants
	static final int SEAT_CHARACTER_WIDTH = 3;
	static final int SPACE_BETWEEN_ISLES = 5;


	/**
	 * The constructor initializes the internal fields of the instantiated Auditorium object.
	 * @param numberOfRowsInAuditorium expects an integer with the number of rows to create in the auditorium
	 * @param numberOfSeatsInEachRow expects an integer with the number of seats in each row
	 */
	Auditorium(int numberOfRowsInAuditorium, int numberOfSeatsInEachRow) {
		
		this.rowsInAuditorium = new ArrayList<Row>();
		
		this.numberOfRowsInAuditorium = numberOfRowsInAuditorium;
		this.numberOfSeatsInEachRow = numberOfSeatsInEachRow;
		this.wallSize = numberOfSeatsInEachRow * SEAT_CHARACTER_WIDTH + 5;
		
		this.setupRows(this.numberOfSeatsInEachRow);
		
		// mock data, already booked seats		
		try {
			getRow(5).getSeat(4).setIsBooked(true);
			getRow(5).getSeat(5).setIsBooked(true);
			getRow(10).getSeat(6).setIsBooked(true);
			getRow(10).getSeat(7).setIsBooked(true);
			getRow(11).getSeat(3).setIsBooked(true);
			getRow(11).getSeat(4).setIsBooked(true);
			getRow(11).getSeat(5).setIsBooked(true);
			getRow(7).getSeat(5).setIsBooked(true);
			getRow(7).getSeat(6).setIsBooked(true);
			getRow(7).getSeat(7).setIsBooked(true);
			getRow(7).getSeat(8).setIsBooked(true);
			getRow(12).getSeat(12).setIsBooked(true);
			
		} catch (SeatUnavailableException e) {
			// TODO Auto-generated catch block
			e.printMessage();
		}
	}
	

	private ArrayList<Row> getRows() { return this.rowsInAuditorium; }
	private Row getRow(int rowNumber) { return getRows().get(rowNumber - 1); }

	/**
	 * description
	 */
	public void displayAuditoriumOverview() {
		this.displayWall(true);

		for(Row row: this.rowsInAuditorium) {
			if (row.getRowNumber() % SPACE_BETWEEN_ISLES == 0) System.out.println();
			if (row.getRowNumber() == 1) {
				System.out.print("   ");
				for (int k = 1; k <= this.numberOfSeatsInEachRow; k++) {
					System.out.printf("%3d", k);
				}
				System.out.println();
			}
			System.out.printf("%2d: ", row.getRowNumber());
			for(Seat seat: row.getSeats()) {
				if (seat.getIsBooked())
					System.out.print("[▓]");
				else
					System.out.print("[░]");
			}
			System.out.println();
		}
		this.displayWall();
	}
	
	
	private void setupRows(int numberOfSeatsInEachRow) {
		Row currentRow;
		for(int i = 1; i <= numberOfRowsInAuditorium; i++) {
			currentRow = new Row(i);
			rowsInAuditorium.add(currentRow);
			for (int j = 1; j <= numberOfSeatsInEachRow; j++) {
				currentRow.getSeats().add(new Seat(j));
			}
		}
	}
	
	/**
	 * Draw an ASCII representation of the auditorium.
	 * @param hasScreen indicates whether a screen should be drawn as part of the wall
	 */
	private void displayWall(boolean hasScreen) {
		// display the left corner
		System.out.print("+");
		
		// draw the wall itself one char at a time
		for(int i = 0; i < this.wallSize; i++) {
			
			// make the wall with the screen different than the rear wall
			if((i > 4 ^ i < this.numberOfSeatsInEachRow * 3) & hasScreen) {
				System.out.print("=");
			} else {
				System.out.print("-");
			}
		}
		
		// if the method is invoked with hasScreen then append an extra newline
		// in order to make space between the wall and the first row of seats
		if (hasScreen)
			System.out.println("+\n");
		else
			System.out.println("+");
	}
	
	/**
	 * Draw a regular wall. 
	 * Behaves like invoking displayWall(false) directly.
	 */
	private void displayWall() {
		this.displayWall(false);
	}	
}