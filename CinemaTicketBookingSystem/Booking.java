package CinemaTicketBookingSystem;

public class Booking {

	/**
	 * The booking reference is generated when the customer has confirmed the booking.
	 */
	private String bookingReference;
	private Presentation presentation;

	public String getBookingReference() {
		return this.bookingReference;
	}

	public Presentation getPresentation() {
		return this.presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

}