/**
 * 
 */
package cp213;

import java.util.Scanner;

/**
 * CP213 Lab 1 - Task 1
 * 
 * @author Samson Goodenough
 * @version 2021-05-22
 */

public class Lab01 {

	/**
	 * @param args unused default variable
	 */
	public static void main(String args[]) {
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
    }
}
