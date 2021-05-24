/**
 * 
 */
package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CP213 Lab 1 - Task 1-3
 * 
 * @author Samson Goodenough
 * @version 2021-05-22
 */

public class ScannerTest {

	/**
	 * @param args unused default variable
	 * @throws FileNotFoundException 
	 */
	public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Enter a series of integers. Press 'q' to quit.");
        
        // Read from the keyboard.
        Scanner s = new Scanner(System.in);
        int result = 0;

        while (!s.hasNext("q")) {
        	if (s.hasNextInt()) {
                result += s.nextInt();
        	} else {
                System.out.println("Please enter a valid integer, or type 'q' to quit.");
                s.next();
        	}
        }

        s.close();
        System.out.println("The total is " + result);
        
        // Task 2 main
        System.out.print("\n-------- Task 2 --------\n");
        File file = new File("src/cp213/ScannerTest.java");
		readTokens(file);
		
		// Task 3 main
        System.out.print("\n-------- Task 3 --------\n");
		countLines(file);
    }
	
	/**
	 * Reads from its own source code file and prints
	 * every token in the file on its own line
	 * 
	 * @param f open file stream to read from
	 * @throws FileNotFoundException
	 */
	public static void readTokens(File f) throws FileNotFoundException {
		Scanner source = new Scanner(f);
		
		while (source.hasNext()) {
			System.out.println(source.next());
		}
		
		source.close();
	}
	
	/**
	 * Prints out all of the tokens in the file, also
	 * counts and prints the number of lines in the file
	 * 
	 * @param f open file stream to read from
	 * @throws FileNotFoundException
	 */
	public static void countLines(File f) throws FileNotFoundException {
		Scanner source = new Scanner(f);
		Scanner lines = new Scanner(f);
		String in;
		int numLines = 0;
		
		while (source.hasNext()) {
			
			in = source.next();
			System.out.println(in);
			
			if (lines.hasNextLine()) {
				numLines++;
				lines.nextLine();
			}
		}
		
		System.out.println("\nThe file has "+numLines+" lines.");
		source.close();
		lines.close();
	}
}
