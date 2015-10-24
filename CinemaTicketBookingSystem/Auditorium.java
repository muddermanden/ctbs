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
	
	// constants; they never change while the programming is running
	static final int SEAT_CHARACTER_WIDTH = 3;
	static final int SPACE_BETWEEN_ISLES = 5;

	
	/**
	 * Accessor for rows. Prevents direct access to values.
	 * @return
	 */
	private ArrayList<Row> getRows() { return this.rowsInAuditorium; }
	
	
	/**
	 * Accessor for a specific row. Prevents direct access to the values, which in this case allows
	 * us to add some business logic, e.g. by subtracting one from the row number in order to match the index number.
	 * @param rowNumber
	 * @return
	 */
	private Row getRow(int rowNumber) { return getRows().get(rowNumber - 1); }
	

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
	

	/**
	 * description
	 */
	public void displayAuditoriumOverview() {
		
		// draw a wall with a screen
		this.displayWall(true);

		for(Row row: this.rowsInAuditorium) {
			// make an extra linebreak to show where the isles are
			if (row.getRowNumber() % SPACE_BETWEEN_ISLES == 0) System.out.println();
			
			// write the seat numbers horizontally before the first row
			if (row.getRowNumber() == 1) {
				
				// add spaces to align numbers with seats
				System.out.print("   ");
				
				for (int k = 1; k <= this.numberOfSeatsInEachRow; k++) {
					System.out.printf("%3d", k);
				}
				System.out.println();
			}
			
			// print the row number before showing the seats
			System.out.printf("%2d: ", row.getRowNumber());
			
			// show the seats based on booking status
			for(Seat seat: row.getSeats()) {
				if (seat.getIsBooked())
					System.out.print("[▓]");
				else
					System.out.print("[░]");
			}
			System.out.println();
		}
		
		// draw a wall (without screen)
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
			
			// if the wall has a screen; draw it from the first seat until the last seat
			if((i < 4 || i > this.numberOfSeatsInEachRow * this.SEAT_CHARACTER_WIDTH) & hasScreen) {
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