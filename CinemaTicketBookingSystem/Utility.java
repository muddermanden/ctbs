package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
	
	// the shared scanner object
	public static Scanner scanner = new Scanner(System.in);
	
	public static int promptUserInputInteger(String message)
	{
		Scanner scanner = Utility.scanner;
		int userInput = 0;

		do
		{
			System.out.println(message);
			try
			{
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


	public static String promptUserInputString(String message)
	{
		Scanner scanner;
		String userInput;

		do
		{
			System.out.println(message);

			scanner = Utility.scanner;
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


}
