package CinemaTicketBookingSystem;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {

	private Customer customer;
	private int numberOfSeats;
	private Presentation presentation;
	private HashMap<Row, Seat> seatsBooked;


	public Booking() {
		customer = new Customer();
		seatsBooked = new HashMap<Row, Seat>();
	}


	/**
	 * @param row
	 *            is the row that customer has selected
	 * @param seat
	 *            is the seat that customer has selected
	 */
	private void addSeatToBooking(Row row, Seat seat) {
		this.seatsBooked.put(row, seat);
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

		input = promptUserInputInteger("Enter the number of the movie you will see :");

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


	/**
	 * @return the seatsBooked
	 */
	private HashMap<Row, Seat> getSeatsBooked() {
		return seatsBooked;
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
				int myRowNumber = promptUserInputInteger("Enter row number for ticket #" + i + ":");
				Row myRow = getPresentation().getAuditorium().getRow(myRowNumber);

				int mySeatNumber = promptUserInputInteger("Select seat number for ticket #" + i + ":");
				Seat mySeat = myRow.getSeat(mySeatNumber);
				mySeat.isBooked(true);

				addSeatToBooking(myRow, mySeat);

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