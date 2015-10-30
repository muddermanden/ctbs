package CinemaTicketBookingSystem;

public class WrongUserInputException extends Exception
{
	private static final long serialVersionUID = 1L;


	public WrongUserInputException(String message)
	{
		super(message);
	}


	public void printMessage()
	{
		System.out.println(getMessage());
	}
}
