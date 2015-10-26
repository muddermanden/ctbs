package CinemaTicketBookingSystem;

public class WrongUserInputException extends Exception
{
	public WrongUserInputException(String message)
	{
		super(message);
	}


	public void printMessage()
	{
		System.out.println(getMessage());
	}
}
