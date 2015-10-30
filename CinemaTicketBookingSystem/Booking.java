package CinemaTicketBookingSystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

public class Booking
{

	private String bookingReference;
	private Customer customer;
	private boolean isConfirmed;
	private int numberOfSeats;
	private Presentation presentation;
	private ArrayList<Seat> seatsBooked;


	public Booking(Customer customer)
	{
		this.customer = customer;
		this.seatsBooked = new ArrayList<Seat>();
		this.isConfirmed = false;
		setBookingReference();
	}


	public void startNewBooking()
	{
		promptForPresentationSelection();
		promptForNumberOfSeats();
		getPresentation().getAuditorium().displayAuditoriumOverview();
		promptForSeatSelection();
		promptForCustomerInfo();
		displayBookingInformation();
		confirmBooking();
	}


	/**
	 * @param row
	 *        is the row that customer has selected
	 * @param seat
	 *        is the seat that customer has selected
	 */
	private void addSeatToBooking(Seat seat)
	{
		this.seatsBooked.add(seat);
	}


	private void confirmBooking()
	{

		char response = Utility.promptUserInputString("Type 'y' to confirm, or any key to cancel.").charAt(0);
		if (response == 'y')
		{
			// mark the booking as confirmed
			setIsConfirmed(true);
			saveBookingToFile();
			System.out.println("Your booking is complete! :D");
		}
		else
		{
			// release the seats again
			for (Seat seat : getSeatsBooked())
			{
				try
				{
					seat.setIsBooked(false);
				}
				catch (WrongUserInputException e)
				{
					e.printMessage();
				}
			}
			System.out.println("Your booking was cancelled!");
			System.exit(0);
		}
	}


	private String displayBookingInformation()
	{
		String bookingInfo = new String();

		//@formatter:off
		bookingInfo = String.format(
				"#### BOOKING INFORMATION ####\n\n" +  
				"Booking ref.:     %s\n" +
				"Name:             %s\n" + 
				"Phone:            %s\n" + 
				"Title:            %s\n" + 
				"Date:             %s\n" + 
				"Number of seats:  %d\n" +
				"Seats booked:\n%s",
				getBookingReference(), getCustomer().getName(), getCustomer().getPhoneNumber(), 
				getPresentation().getMovieTitle(), getPresentation().getDatetime(), getNumberOfSeats(), 
				getSeatsBookedAsString());
		//@formatter:on

		System.out.println(bookingInfo);

		if (!getIsConfirmed())
		{
			System.out.println("Please confirm the booking details!\n");
		}

		return bookingInfo;
	}


	private String getBookingReference()
	{
		return this.bookingReference;
	}


	private Customer getCustomer()
	{
		return this.customer;
	}


	private boolean getIsConfirmed()
	{
		return this.isConfirmed;
	}


	private int getNumberOfSeats()
	{
		return this.numberOfSeats;
	}


	private Presentation getPresentation()
	{
		return this.presentation;
	}


	private ArrayList<Seat> getSeatsBooked()
	{
		return this.seatsBooked;
	}


	private String getSeatsBookedAsString()
	{
		String str = new String();
		int i = 1;
		for (Seat seat : getSeatsBooked())
		{
			str += String.format("  Ticket #%-2d: On row %2d, seat %2d\n", i, seat.getRowNumber(),
					seat.getSeatNumber());
			i++;
		}
		return str;
	}


	private void promptForCustomerInfo()
	{
		Customer c = getCustomer();

		String name = Utility.promptUserInputString("Enter your name:");
		String phoneNumber = Utility.promptUserInputString("Enter your phone number:");
		
		c.setName(name);
		c.setPhoneNumber(phoneNumber);
	}


	private void promptForNumberOfSeats()
	{
		int numberOfSeats = 0;
		numberOfSeats = Utility.promptUserInputInteger("How many tickets do you want?");
		setNumberOfSeats(numberOfSeats);
	}


	private Presentation promptForPresentationSelection()
	{

		do
		{
			Presentation selectedPresentation = MovieSchedule.getInstance().displayPresentationMenu();
			setPresentation(selectedPresentation);
		}
		while (getPresentation() == null);
		return getPresentation();
	}


	private void promptForSeatSelection()
	{

		int i = 1;

		do
		{

			try
			{
				int myRowNumber = Utility.promptUserInputInteger("Enter row number for ticket #" + i + ":");
				Row myRow = getPresentation().getAuditorium().getRow(myRowNumber);

				int mySeatNumber = Utility.promptUserInputInteger("Select seat number for ticket #" + i + ":");
				Seat mySeat = myRow.getSeat(mySeatNumber);
				mySeat.setIsBooked(true);

				addSeatToBooking(mySeat);

				getPresentation().getAuditorium().displayAuditoriumOverview();

			}
			catch (WrongUserInputException e)
			{
				e.printMessage();
				continue;
			}
			i++;
		}
		while (i <= getNumberOfSeats());
	}


	private void saveBookingToFile()
	{
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(getBookingReference() + ".txt", "UTF-8");
			writer.println(displayBookingInformation());
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void setBookingReference()
	{
		this.bookingReference = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}


	private void setIsConfirmed(boolean value)
	{
		this.isConfirmed = value;
	}


	private void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}


	private void setPresentation(Presentation presentation)
	{
		this.presentation = presentation;
	}

}