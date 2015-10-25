package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {

	/**
	 * The booking reference is generated when the customer has confirmed the booking.
	 */
	private String bookingReference;
	private Presentation presentation;
	private Customer customer;
	private int numberOfSeats;
	
	public Booking() {
		customer = new Customer();
	}

	
	public String getBookingReference() {
		return this.bookingReference;
	}
	
	
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}
	
	
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	private Customer getCustomer() {
		return this.customer;
	}
	
	public Presentation getPresentation() {
		return this.presentation;
	}

	
	private void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	
	public String displayBookingInformation() {
		String bookingInfo = new String();
		
		bookingInfo = String.format("#### BOOKING INFORMATION ####\n\n"
				+ "Please confirm the details below\n"
				+ "Name:\t%s\nPhone:\t%s\n"
				+ "Movie title: %s\n"
				+ "Presentation date: %s\n"
				+ "Number of tickets: %d\n",
				this.getCustomer().getName(),
				this.getCustomer().getPhoneNumber(),
				this.getPresentation().getMovieTitle(),
				this.getPresentation().getDatetime(),
				this.getNumberOfSeats());
		
		return bookingInfo;
	}
	
	
	public Presentation promptForPresentationSelection() {

		do {
			Presentation selectedPresentation = this.displayPresentationMenu();
			this.setPresentation(selectedPresentation);
		} while (this.getPresentation() == null);
		return this.getPresentation();
	}
	
	
	public void promptForNumberOfSeats() {
		int numberOfSeats = 0;
		numberOfSeats = this.userInputIntegerPrompt("How many tickets do you want?");
		this.setNumberOfSeats(numberOfSeats);
	}
	
	
	public Presentation displayPresentationMenu() {
		MovieSchedule movieSchedule = MovieSchedule.getInstance();
		
		Presentation myPresentation = null;
		int input = 0;
		
		movieSchedule.displayMovieSchedule();
		
		input = this.userInputIntegerPrompt("Enter choice :");
		
		myPresentation = movieSchedule.matchPresentation(input);
		
		return myPresentation;
		
	}
	
	
	public void promptForSeatSelection() {
		
		int i = 1;
		
		do {
			
			try {
				int myRow = this.userInputIntegerPrompt("Enter row number for ticket #" + i +":");
				this.getPresentation().getAuditorium().getRow(myRow);
				int mySeat = this.userInputIntegerPrompt("Select seat number for ticket #" + i +":");
				this.getPresentation().getAuditorium().getRow(myRow).getSeat(mySeat).setIsBooked(true);
				this.getPresentation().getAuditorium().displayAuditoriumOverview();
			}
			catch (WrongUserInputException e) {
				e.printMessage();
				continue;
			}
			i++;
		} while (i <= this.getNumberOfSeats());
	}
	
	
	public void promptForCustomerInfo() {
		String fullName = this.userInputStringPrompt("Enter your full name:");
		this.getCustomer().setName(fullName);
		
		String phoneNumber = this.userInputStringPrompt("Enter your phone number:");
		this.getCustomer().setPhoneNumber(phoneNumber);;
	}

	
	public String userInputStringPrompt(String message) {
		Scanner scanner;
		String userInput;
		
		do {
			System.out.println(message);
			
			scanner = new Scanner(System.in);
			userInput = scanner.nextLine();
			
			if(userInput.length() > 3) {
				return userInput;
			} 
			else {
				System.out.println("Please enter a string of more than 3 characters.");
			}
		} while (true);
	}
	
	
	public int userInputIntegerPrompt(String message) {
		Scanner scanner;
		int userInput = 0;

		do {
			System.out.println(message);
			try {
				scanner = new Scanner(System.in);
				userInput = scanner.nextInt();
				
				if (userInput > 0) {
					//scanner.close();
					return userInput;
				}
				else {
					System.out.println("Please enter a positive number.");
				}
				
			} 
			catch (InputMismatchException e) {
				System.out.println("Please enter number.");
			}
		} while (true);
	}

}