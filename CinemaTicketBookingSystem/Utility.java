package CinemaTicketBookingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
	public static int promptUserInputInteger(String message)
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


	public static String promptUserInputString(String message)
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


}
