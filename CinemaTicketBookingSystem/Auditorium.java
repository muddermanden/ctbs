package CinemaTicketBookingSystem;

import java.util.ArrayList;

public class Auditorium
{

	// constants; they never change while the programming is running
	static final int SEAT_CHARACTER_WIDTH = 3;
	static final int SPACE_BETWEEN_ISLES = 5;
	static final int NUMBER_OF_ROWS_IN_AUDITORIUM = 12;
	static final int NUMBER_OF_SEATS_IN_EACH_ROW = 10;

	/**
	 * private instance variables
	 */

	private final ArrayList<Row> rowsInAuditorium;
	private final int wallSize;


	/**
	 * The constructor initializes the internal fields of the instantiated
	 * Auditorium object.
	 * 
	 * @param numberOfRowsInAuditorium
	 *            expects an integer with the number of rows to create in the
	 *            auditorium
	 * @param numberOfSeatsInEachRow
	 *            expects an integer with the number of seats in each row
	 */
	Auditorium()
	{
		this.rowsInAuditorium = new ArrayList<Row>();
		this.wallSize = NUMBER_OF_SEATS_IN_EACH_ROW * SEAT_CHARACTER_WIDTH + 5;
		setupRows(NUMBER_OF_SEATS_IN_EACH_ROW);
		addMockData();
	}


	public int getNumberOfAvailableSeats()
	{
		int numberOfSeatsAvailable = 0;
		for (Row row : getRows())
		{
			for (Seat seat : row.getSeats())
			{
				if (seat.getIsBooked()) continue;
				numberOfSeatsAvailable++;
			}
		}
		return numberOfSeatsAvailable;
	}


	private void addMockData()
	{
		int a = (int) (Math.random() * NUMBER_OF_ROWS_IN_AUDITORIUM * NUMBER_OF_SEATS_IN_EACH_ROW);
		int i = 0;
		while (i < a)
		{
			int b = (int) (Math.random() * NUMBER_OF_ROWS_IN_AUDITORIUM) + 1;
			int c = (int) (Math.random() * NUMBER_OF_SEATS_IN_EACH_ROW) + 1;
			try
			{
				getRow(b).getSeat(c).setIsBooked(true);
				i++;
			}
			catch (WrongUserInputException e)
			{
				// e.printMessage();
				continue;
			}
		}
	}


	/**
	 * description
	 */
	public void displayAuditoriumOverview()
	{
		// draw a wall with a screen
		displayWall(true);

		for (Row row : this.rowsInAuditorium)
		{
			// make an extra line break to show where the isles are
			if (row.getRowNumber() % SPACE_BETWEEN_ISLES == 0) System.out.println();

			// write the seat numbers horizontally before the first row
			if (row.getRowNumber() == 1)
			{

				// add spaces to align numbers with seats
				System.out.print("   ");

				for (int k = 1; k <= NUMBER_OF_SEATS_IN_EACH_ROW; k++)
				{
					System.out.printf("%3d", k);
				}
				System.out.println();
			}

			// print the row number before showing the seats
			System.out.printf("%2d: ", row.getRowNumber());

			// show the seats based on booking status
			for (Seat seat : row.getSeats())
			{
				if (seat.getIsBooked())
				{
					System.out.print("[▓]");
				}
				else
				{
					System.out.print("[░]");
				}
			}
			System.out.println();
		}

		// draw a wall (without screen)
		displayPlainWall();
	}


	/**
	 * Display a plain wall (without the screen). Behaves like invoking
	 * displayWall(false) directly.
	 */
	private void displayPlainWall()
	{
		displayWall(false);
	}


	/**
	 * Display an ASCII representation of the auditorium.
	 * 
	 * @param hasScreen
	 *            indicates whether a screen should be drawn as part of the wall
	 */
	private void displayWall(boolean hasScreen)
	{
		// display the left corner
		System.out.print("+");

		// draw the wall itself one char at a time
		for (int i = 0; i < this.wallSize; i++)
		{

			// if the wall has a screen; draw it from the first seat until the
			// last seat
			if ((i < 4 || i > NUMBER_OF_SEATS_IN_EACH_ROW * SEAT_CHARACTER_WIDTH) && hasScreen)
			{
				System.out.print("=");
			}
			else
			{
				System.out.print("-");
			}
		}

		// if the method is invoked with hasScreen then append an extra newline
		// in order to make space between the wall and the first row of seats
		if (hasScreen) System.out.println("+\n");
		else
			System.out.println("+");
	}


	/**
	 * Accessor for a specific row. Prevents direct access to the values, which
	 * in this case allows us to add some business logic, e.g. by subtracting
	 * one from the row number in order to match the index number.
	 * 
	 * @param rowNumber
	 * @return
	 */
	public Row getRow(int rowNumber) throws WrongUserInputException
	{
		try
		{
			return getRows().get(rowNumber - 1);
		}
		catch (IndexOutOfBoundsException e)
		{
			throw new WrongUserInputException("The row does not exist.");
		}

	}


	/**
	 * Accessor for rows. Prevents direct access to values.
	 * 
	 * @return
	 */
	private ArrayList<Row> getRows()
	{
		return this.rowsInAuditorium;
	}


	private void setupRows(int numberOfSeatsInEachRow)
	{
		Row currentRow;
		for (int i = 1; i <= NUMBER_OF_ROWS_IN_AUDITORIUM; i++)
		{
			currentRow = new Row(i);
			rowsInAuditorium.add(currentRow);
			for (int j = 1; j <= NUMBER_OF_SEATS_IN_EACH_ROW; j++)
			{
				currentRow.getSeats().add(new Seat(currentRow, j));
			}
		}
	}
}