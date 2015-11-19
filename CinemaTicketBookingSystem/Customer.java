package CinemaTicketBookingSystem;

/**
 * A customer is a representation of the person who is making the booking.
 */
public class Customer extends User {
    public Customer(String username, String password) {
	super(username, password);
    }

    /**
     * The name of the customer is used to identify the booking if a customer
     * has forgotten his booking reference.
     */
    private String name;
    /**
     * The phone number is used to contact the customer in case of canceling a
     * presentation, and for identification.
     */
    private String phoneNumber;

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPhoneNumber() {
	return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    @Override
    protected void showLoginStatus() {
	System.out.println("You are now logged in as " + super.getUsername() + "!");
    }
}