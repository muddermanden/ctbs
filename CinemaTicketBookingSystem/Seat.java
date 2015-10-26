package CinemaTicketBookingSystem;

public class Seat
{

	private boolean isBooked;
	private int seatNumber;
	private Row row;


	Seat(Row row, int seatNumber)
	{
		setRow(row);
		setSeatNumber(seatNumber);
	}


	public boolean getIsBooked()
	{
		return this.isBooked;
	}


	public void setIsBooked(boolean value) throws WrongUserInputException
	{
		if (this.isBooked && value)
		{
			throw new WrongUserInputException("Sorry, the seat is not available.");
		}
		else
		{
			this.isBooked = value;
		}
	}


	private void setRow(Row row)
	{
		this.row = row;
	}


	private Row getRow()
	{
		return this.row;
	}


	public int getRowNumber()
	{
		return this.getRow().getRowNumber();
	}


	private void setSeatNumber(int seatNumber)
	{
		this.seatNumber = seatNumber;
	}


	public int getSeatNumber()
	{
		return this.seatNumber;
	}
}