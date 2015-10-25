package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {

	/**
	 * The booking reference is generated when the customer has confirmed the
	 * booking.
	 */
	private Customer		customer;
	private int				numberOfSeats;
	private Presentation	presentation;


	public Booking() {
		customer = new Customer();
	}


	private String displayBookingInformation() {
		String bookingInfo = new String();

		bookingInfo = String.format(
				"#### BOOKING INFORMATION ####\n\n" + "Please confirm the details below\n" + "Name:\t%s\nPhone:\t%s\n"
						+ "Movie title: %s\n" + "Presentation date: %s\n" + "Number of tickets: %d\n",
				this.getCustomer().getName(), this.getCustomer().getPhoneNumber(),
				this.getPresentation().getMovieTitle(), this.getPresentation().getDatetime(), this.getNumberOfSeats());
		System.out.println(bookingInfo);
		return bookingInfo;
	}


	private Presentation displayPresentationMenu() {
		MovieSchedule movieSchedule = MovieSchedule.getInstance();

		Presentation myPresentation = null;
		int input = 0;

		movieSchedule.displayMovieSchedule();

		input = this.promptUserInputInteger("Enter choice :");

		myPresentation = movieSchedule.matchPresentation(input);

		return myPresentation;

	}


	private Customer getCustomer() {
		return this.customer;
	}


	private int getNumberOfSeats() {
		return this.numberOfSeats;
	}


	private Presentation getPresentation() {
		return this.presentation;
	}


	private void promptForCustomerInfo() {
		String fullName = this.promptUserInputString("Enter your full name:");
		this.getCustomer().setName(fullName);

		String phoneNumber = this.promptUserInputString("Enter your phone number:");
		this.getCustomer().setPhoneNumber(phoneNumber);
		;
	}


	private void promptForNumberOfSeats() {
		int numberOfSeats = 0;
		numberOfSeats = this.promptUserInputInteger("How many tickets do you want?");
		this.setNumberOfSeats(numberOfSeats);
	}


	private Presentation promptForPresentationSelection() {

		do {
			Presentation selectedPresentation = this.displayPresentationMenu();
			this.setPresentation(selectedPresentation);
		} while (this.getPresentation() == null);
		return this.getPresentation();
	}


	private void promptForSeatSelection() {

		int i = 1;

		do {

			try {
				int myRow = this.promptUserInputInteger("Enter row number for ticket #" + i + ":");
				this.getPresentation().getAuditorium().getRow(myRow);
				int mySeat = this.promptUserInputInteger("Select seat number for ticket #" + i + ":");
				getPresentation().getAuditorium().getRow(myRow).getSeat(mySeat).isBooked(true);
				getPresentation().getAuditorium().displayAuditoriumOverview();
			} catch (WrongUserInputException e) {
				e.printMessage();
				continue;
			}
			i++;
		} while (i <= this.getNumberOfSeats());
	}


	private int promptUserInputInteger(String message) {
		Scanner scanner;
		int userInput = 0;

		do {
			System.out.println(message);
			try {
				scanner = new Scanner(System.in);
				userInput = scanner.nextInt();

				if (userInput > 0) {
					// scanner.close();
					return userInput;
				} else {
					System.out.println("Please enter a positive number.");
				}

			} catch (InputMismatchException e) {
				System.out.println("Please enter number.");
			}
		} while (true);
	}


	private String promptUserInputString(String message) {
		Scanner scanner;
		String userInput;

		do {
			System.out.println(message);

			scanner = new Scanner(System.in);
			userInput = scanner.nextLine();

			if (userInput.length() > 3) {
				return userInput;
			} else {
				System.out.println("Please enter a string of more than 3 characters.");
			}
		} while (true);
	}


	private void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	private void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}


	public void startNewBooking() {
		promptForPresentationSelection();
		promptForNumberOfSeats();
		getPresentation().getAuditorium().displayAuditoriumOverview();
		promptForSeatSelection();
		promptForCustomerInfo();
		displayBookingInformation();
	}

}