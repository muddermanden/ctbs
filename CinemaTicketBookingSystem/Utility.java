package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
	
	// the shared scanner object
	private static final Scanner scanner = new Scanner(System.in);
	
	public static int promptUserInputInteger(String message)
	{
		int input;
		do
		{
			System.out.println(message);
			
			try
			{
				input = scanner.nextInt();
				if (input > 0)
				{
					return input;
				}
				else
				{
					
					System.out.println("Please enter a positive number!");
				}

			}
			catch (InputMismatchException e)
			{
				System.out.println("Please enter a number!");
				scanner.next(); // clear the wrong input
			}
		}
		while (true);
	}


	public static String promptUserInputString(String message)
	{
		String userInput;

		do
		{
			try {
				System.out.println(message);
	
				userInput = scanner.nextLine();
	
				if (userInput.length() > 0)
				{
					return userInput;
				}
				else
				{
					System.out.println("Please enter a string!");
				}
				// scanner.close();
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		while (true);
	}
}
