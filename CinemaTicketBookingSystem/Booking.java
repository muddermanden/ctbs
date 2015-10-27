package CinemaTicketBookingSystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Booking
{
	// declare private fields
	private Customer customer;
	private boolean isConfirmed;
	private int numberOfSeats;
	private Presentation presentation;
	private ArrayList<Seat> seatsBooked;
	private String bookingReference;

	// the constructor; called when creating new object instance of Booking
	public Booking()
	{
		// 
		this.customer = new Customer();

		// initialize the 
		this.seatsBooked = new ArrayList<Seat>();
		
		// not confirmed as default
		this.isConfirmed = false;

		// generate a booking reference number
		setBookingReference();
	}


	/**
	 * @param row
	 *            is the row that customer has selected.
	 * @param seat
	 *            is the seat that customer has selected.
	 */
	private void addSeatToBooking(Seat seat)
	{
		this.seatsBooked.add(seat);
	}


	private void setBookingReference()
	{
		this.bookingReference = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}


	private String getBookingReference()
	{
		return this.bookingReference;
	}


	private void confirmBooking()
	{
		char response = promptUserInputString("Type 'y' to confirm, or any key to cancel.").charAt(0);
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


	private Presentation displayPresentationMenu()
	{
		// get the movie schedule
		MovieSchedule movieSchedule = MovieSchedule.getInstance();

		// the users choice
		Presentation myPresentation = null;

		// ask the movie schedule to display a schedule
		movieSchedule.displayMovieSchedule();

		// ask the user to enter a number of choice
		int input = promptUserInputInteger("Enter the number of the presentation you will see:");

		// get the presentation with the ID the user entered
		myPresentation = movieSchedule.matchPresentation(input);

		return myPresentation;

	}


	private Customer getCustomer()
	{
		return this.customer;
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


	private void setIsConfirmed(boolean value)
	{
		this.isConfirmed = value;
	}


	private boolean getIsConfirmed()
	{
		return this.isConfirmed;
	}


	private void promptForCustomerInfo()
	{
		String fullName = promptUserInputString("Enter your full name:");
		getCustomer().setName(fullName);

		String phoneNumber = promptUserInputString("Enter your phone number:");
		getCustomer().setPhoneNumber(phoneNumber);
	}


	private void promptForNumberOfSeats()
	{
		int numberOfSeats = 0;
		numberOfSeats = promptUserInputInteger("How many tickets do you want?");
		setNumberOfSeats(numberOfSeats);
	}


	private Presentation promptForPresentationSelection()
	{
		// repeat the loop until displayPresentationMenu returns a Presentation object
		do
		{
			Presentation selectedPresentation = displayPresentationMenu();
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
				int myRowNumber = promptUserInputInteger("Enter row number for ticket #" + i + ":");
				Row myRow = getPresentation().getAuditorium().getRow(myRowNumber);

				int mySeatNumber = promptUserInputInteger("Select seat number for ticket #" + i + ":");
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


	private int promptUserInputInteger(String message)
	{
		Scanner scanner;
		int userInput = 0;

		do
		{
			System.out.println(message);
			try
			{
				scanner = new Scanner(System.in);
				userInput = scanner.nextInt();

				if (userInput > 0)
				{
					return userInput;
				}
				else
				{
					System.out.println("Please enter a positive number.");
				}

			}
			catch (InputMismatchException e)
			{
				System.out.println("Please enter number.");
			}
		}
		while (true);
	}


	private String promptUserInputString(String message)
	{
		Scanner scanner;
		String userInput;

		do
		{
			System.out.println(message);

			scanner = new Scanner(System.in);
			userInput = scanner.nextLine();

			if (userInput.length() > 0)
			{
				return userInput;
			}
			else
			{
				System.out.println("Please enter a string.");
			}
		}
		while (true);
	}


	private void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}


	private void setPresentation(Presentation presentation)
	{
		this.presentation = presentation;
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

}