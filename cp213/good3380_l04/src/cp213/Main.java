package cp213;

import java.util.Scanner;

public class Main {

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		System.out.println("Test scannerTest");
		System.out.println();
		int total = scannerTest();
		System.out.println("Total: " + total);
		System.out.println();

		System.out.println("Test stringPrinter");
		// provide the try/catch block to call:

		// start block
		String output;
		try {
			output = stringPrinter(5, "*");
			System.out.println(output);
			output = stringPrinter(-5, "*");
			System.out.println(output);
		} catch (Exception e) {
			System.out.println(e.toString()); // prints the exception as link and message
			System.out.println(e.getMessage()); // prints just the message
			e.printStackTrace();
		}
		// end block
	}

	/**
	 * Uses exceptions to capture bad input from a keyboard Scanner.
	 *
	 * @return The total of all the integers entered.
	 */
	public static int scannerTest() {

		Scanner scan = new Scanner(System.in);
		int total = 0;
		boolean run = true;
		
		while (run) {
			System.out.print("Enter an integer ('Quit' to stop): ");
			try {
				int num = scan.nextInt();
				total += num;
			} catch (Exception e) {
				String s = scan.nextLine();
				if (s.toLowerCase().equals("quit")) {
					run = false;
				} else {
					System.out.println("That is not an Integer!");
				}
			}
		}
		scan.close();

		return total;
		
	}

	/**
	 * Repeats a string.
	 *
	 * @param n   Number of times to repeat a string.
	 * @param str The string to print.
	 * @return The repeated string.
	 * @throws Exception If n is negative.
	 */
	public static String stringPrinter(int n, String str) throws Exception {
		String result = "";

		if (n >= 0) {
			for (int i = 0; i < n; i++) {
				result += str;
			}
		} else {
			throw new Exception("Non-negative value for variable 'n' expected");
		}

		return result;
	}

}
