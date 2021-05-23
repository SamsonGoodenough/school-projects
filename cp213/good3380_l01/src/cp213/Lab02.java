package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * CP213 Lab 1 - Task 2
 * 
 * @author Samson Goodenough
 * @version 2021-05-22
 */
public class Lab02 {

	/**
	 * @param args unused default variable
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("src/cp213/Lab02.java");
		readTokens(file);
	}
	
	/**
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

}
