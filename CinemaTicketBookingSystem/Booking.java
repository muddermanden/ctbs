package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {

	private Customer customer;
	private int numberOfSeats;
	private Presentation presentation;


	public Booking() {
		customer = new Customer();
	}


	private String displayBookingInformation() {
		String bookingInfo = new String();

		bookingInfo = String.format(
				"#### BOOKING INFORMATION ####\n\n" + "Please confirm the details below\n" + "Name:\t%s\nPhone:\t%s\n"
						+ "Movie title: %s\n" + "Presentation date: %s\n" + "Number of tickets: %d\n",
				getCustomer().getName(), getCustomer().getPhoneNumber(), getPresentation().getMovieTitle(),
				getPresentation().getDatetime(), getNumberOfSeats());

		System.out.println(bookingInfo);
		return bookingInfo;
	}


	private Presentation displayPresentationMenu() {
		MovieSchedule movieSchedule = MovieSchedule.getInstance();

		Presentation myPresentation = null;
		int input = 0;

		movieSchedule.displayMovieSchedule();

		input = promptUserInputInteger("Enter choice :");

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
		String fullName = promptUserInputString("Enter your full name:");
		getCustomer().setName(fullName);

		String phoneNumber = promptUserInputString("Enter your phone number:");
		getCustomer().setPhoneNumber(phoneNumber);
	}


	private void promptForNumberOfSeats() {
		int numberOfSeats = 0;
		numberOfSeats = promptUserInputInteger("How many tickets do you want?");
		setNumberOfSeats(numberOfSeats);
	}


	private Presentation promptForPresentationSelection() {

		do {
			Presentation selectedPresentation = displayPresentationMenu();
			setPresentation(selectedPresentation);
		} while (getPresentation() == null);
		return getPresentation();
	}


	private void promptForSeatSelection() {

		int i = 1;

		do {

			try {
				int myRow = promptUserInputInteger("Enter row number for ticket #" + i + ":");
				getPresentation().getAuditorium().getRow(myRow);
				int mySeat = promptUserInputInteger("Select seat number for ticket #" + i + ":");
				getPresentation().getAuditorium().getRow(myRow).getSeat(mySeat).isBooked(true);
				getPresentation().getAuditorium().displayAuditoriumOverview();
			} catch (WrongUserInputException e) {
				e.printMessage();
				continue;
			}
			i++;
		} while (i <= getNumberOfSeats());
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