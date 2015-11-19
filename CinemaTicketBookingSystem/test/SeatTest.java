package CinemaTicketBookingSystem.test;

import static org.junit.Assert.*;

import org.junit.Test;

import CinemaTicketBookingSystem.Row;
import CinemaTicketBookingSystem.Seat;
import CinemaTicketBookingSystem.WrongUserInputException;

public class SeatTest {
    Row row = new Row(1);
    Seat seat;

    @Test
    public void testSeat() {
	int expected = 1;
	seat = new Seat(row, expected);
	int actual = seat.getRowNumber();
	assertEquals(expected, actual);
    }

    @Test
    public void testGetIsBooked() throws WrongUserInputException {
	seat = new Seat(row, 1);
	boolean expected = true;
	seat.setIsBooked(expected);
	boolean actual = seat.getIsBooked();
	assertTrue(actual == expected);
    }

    @Test
    public void testGetRowNumber() {
	seat = new Seat(row, 1);
	int expected = 1;
	int actual = seat.getRowNumber();
	assertEquals(expected, actual);
    }

    @Test
    public void testGetSeatNumber() {
	int expected = 1;
	seat = new Seat(row, expected);
	int actual = seat.getSeatNumber();
	assertEquals(expected, actual);
    }

}
