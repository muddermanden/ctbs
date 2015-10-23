package CinemaTicketBookingSystem;

import java.util.Arrays;
import java.util.Scanner;

public class Utility {
	public static final String centeredText(String text, int width) {
		return Utility.centeredText(text, width, false);
	}
	
	
	public static final String centeredText(String text, int width, boolean toUpperCase) {
		text = toUpperCase ? text.toUpperCase() : text;
		int beginText = (width - text.length()) / 2;
		char[] c = new char[beginText];
		Arrays.fill(c, ' ');
		String strReturn = new String(c);
		strReturn += text;
		return strReturn;
	}
	
	
	public static final int displayMenu(String[] menuItems) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Make a choice:");

		for (int i = 0; i < menuItems.length; i++) {
			System.out.printf("%2d) %s\n", i + 1, menuItems[i]);
		}

		System.out.println(" 0) Exit");
		System.out.print("Enter choice: ");
		int input = scanner.nextInt();
	
		//scanner.close();
		return input;
	}
}
