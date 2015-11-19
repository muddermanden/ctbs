package CinemaTicketBookingSystem;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Auditorium class is used to create Auditorium objects.
 */
public class Auditorium {

    // class constants; they never change while the application is running
    static final int SEAT_CHARACTER_WIDTH = 3;
    static final int SPACE_BETWEEN_ISLES = 5;
    static final int NUMBER_OF_ROWS_IN_AUDITORIUM = 12;
    static final int NUMBER_OF_SEATS_IN_EACH_ROW = 10;
    static final int WALL_SIZE = NUMBER_OF_SEATS_IN_EACH_ROW * SEAT_CHARACTER_WIDTH + 5;
    static final char BOOKED_SYMBOL = '▓';
    static final char UNBOOOKED_SYMBOL = '░';

    /**
     * Collection of rows in the auditorium.
     */
    private final ArrayList<Row> rowsInAuditorium;

    /**
     * Class constructor.
     * 
     * @param numberOfRowsInAuditorium Number of rows to create in this auditorium.
     * @param numberOfSeatsInEachRow Number of seats to create in each row.
     */
    public Auditorium() {
	this.rowsInAuditorium = new ArrayList<Row>();
	setupSeatsInRows();
	createMockData();
    }

    /**
     * Returns the number of seats in the auditorium that are not booked.
     * @return The number of seats.
     */
    public int getNumberOfAvailableSeats() {
	int numberOfSeatsAvailable = 0;
	for (Row row : getRows()) {
	    for (Seat seat : row.getSeats()) {
		if (seat.getIsBooked())
		    continue;
		numberOfSeatsAvailable++;
	    }
	}
	return numberOfSeatsAvailable;
    }

    /**
     * Generates mock data. Seats are randomly marked as booked.
     */
    private void createMockData() {
	Random random = new Random();

	// loop through the rows in the auditorium
	for (int i = 1; i <= NUMBER_OF_ROWS_IN_AUDITORIUM; i++) {
	    // loop through the seats in each row
	    for (int j = 1; j <= NUMBER_OF_SEATS_IN_EACH_ROW; j++) {
		try {
		    // generate a random boolean value
		    boolean value = random.nextBoolean();
		    getRow(i).getSeat(j).setIsBooked(value);
		} catch (WrongUserInputException e) {
		    e.printStackTrace();
		}
	    }

	}
    }

    /**
     * Prints an overview of the auditorium that shows the seat availability. 
     * The auditorium is printed in ASCII characters as a 2-by-2 matrix with seat numbers in the top
     * and row numbers in the left side.
     */
    public void displayAuditoriumOverview() {
	// draw a wall (with screen)
	displayWall(true);

	for (Row row : this.rowsInAuditorium) {
	    // make an extra line break to show where the isles are
	    if (row.getRowNumber() % SPACE_BETWEEN_ISLES == 0)
		System.out.println();

	    // write the seat numbers horizontally before the first row
	    if (row.getRowNumber() == 1) {

		// add spaces to align numbers with seats
		System.out.print("   ");

		for (int k = 1; k <= NUMBER_OF_SEATS_IN_EACH_ROW; k++) {
		    System.out.printf("%3d", k);
		}
		System.out.println();
	    }

	    // print the row number before showing the seats
	    System.out.printf("%2d: ", row.getRowNumber());

	    // show the seats based on booking status
	    for (Seat seat : row.getSeats()) {
		char status = seat.getIsBooked() ? BOOKED_SYMBOL : UNBOOOKED_SYMBOL;
		System.out.print("[" + status + "]");
	    }
	    System.out.println();
	}

	// draw a wall (without screen)
	displayWall(false);
    }

    /**
     * Prints a wall as a line of characters.
     * @param hasScreen True if the drawn wall contains a movie screen.
     */
    private void displayWall(boolean hasScreen) {
	// display the left corner
	System.out.print("+");

	// draw the wall itself one char at a time
	for (int i = 0; i < WALL_SIZE; i++) {

	    // if the wall has a screen; draw it from the first seat until the
	    // last seat
	    if ((i < 4 || i > NUMBER_OF_SEATS_IN_EACH_ROW * SEAT_CHARACTER_WIDTH) && hasScreen) {
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
     * Returns a row object from the row number passed as an argument.
     * @param rowNumber The number of the row. Counting from 1.
     * @return The Row object with the <code>rowNumber</code>
     * @throws WrongUserInputException Is thrown if the row number does not exist.
     */
    public Row getRow(int rowNumber) throws WrongUserInputException {
	try {
	    return getRows().get(rowNumber - 1);
	} catch (IndexOutOfBoundsException e) {
	    throw new WrongUserInputException("The row does not exist.");
	}

    }

    /**
     * Returns the collection of row objects in this auditorium.
     * @return
     */
    private ArrayList<Row> getRows() {
	return this.rowsInAuditorium;
    }

    /**
     * Populates all the rows in this auditorium with <code>Seat</code> objects.
     */
    private void setupSeatsInRows() {
	Row currentRow;
	for (int i = 1; i <= NUMBER_OF_ROWS_IN_AUDITORIUM; i++) {
	    currentRow = new Row(i);
	    rowsInAuditorium.add(currentRow);
	    for (int j = 1; j <= NUMBER_OF_SEATS_IN_EACH_ROW; j++) {
		currentRow.getSeats().add(new Seat(currentRow, j));
	    }
	}
    }
}