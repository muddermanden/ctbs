package CinemaTicketBookingSystem;

public class Employee extends User
{
	public Employee(String username, String password)
	{
		super(username, password);
	}
	
	public void checkBooking()
	{
		String bookingReference = Utility.promptUserInputString("Enter booking reference to look up");
		Booking.readBookingInfo(bookingReference);
	}
}
