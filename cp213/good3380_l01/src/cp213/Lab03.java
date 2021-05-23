package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CP213 Lab 1 - Task 3
 * 
 * @author Samson Goodenough
 * @version 2021-05-22
 */
public class Lab03 {

	/**
	 * @param args unused default variable
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("src/cp213/Lab03.java");
		countLines(file);
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