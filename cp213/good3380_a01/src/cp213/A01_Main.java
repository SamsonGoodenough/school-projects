package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Samson Goodenough (190723380)
 * @version 2021-05-18
 */
public class A01_Main {
	// Constants
	public static final String SEP = "-".repeat(40);
	public static final String CIPHERTEXT = "AVIBROWNZCEFGHJKLMPQSTUXYD"; // for testing substitute method

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println(SEP);
		System.out.println("A01.shift(\"abcdefghijklmnopqrstuvwxyz.123ABCDEFGHIJKLMNOPQRSTUVWXYZ\", 27)");
		System.out.println(" > "+A01.shift("abcdefghijklmnopqrstuvwxyz.123ABCDEFGHIJKLMNOPQRSTUVWXYZ", -27));
		
		System.out.println(SEP);
		System.out.println("A01.substitute(\"abcdefghijklmnopqrstuvwxyz.123ABCDEFGHIJKLMNOPQRSTUVWXYZ\", CIPHERTEXT)");
		System.out.println(" > "+A01.substitute("abcdefghijklmnopqrstuvwxyz.123ABCDEFGHIJKLMNOPQRSTUVWXYZ", CIPHERTEXT));
		
		System.out.println(SEP);
		System.out.println("A01.allDigits(\"123\")");
		System.out.println(" > "+A01.allDigits("123"));
		System.out.println("A01.allDigits(\"12.3\")");
		System.out.println(" > "+A01.allDigits("12.3"));
		System.out.println("A01.allDigits(\"12a3\")");
		System.out.println(" > "+A01.allDigits("12a3"));
		
		System.out.println(SEP);
		System.out.println("A01.closest(5, 3, 9)");
		System.out.println(" > "+A01.closest(5, 3, 9));
		System.out.println("A01.closest(5, -5, 25.5)");
		System.out.println(" > "+A01.closest(5, -5, 25.5));
		System.out.println("A01.closest(0, -5, 5)");
		System.out.println(" > "+A01.closest(0, -5, 5));

		System.out.println(SEP);
		System.out.println("A01.contains(new int[]{1, 2, 3}, 2)");
		System.out.println(" > "+A01.contains(new int[]{1, 2, 3}, 2));
		System.out.println("A01.contains(new int[]{1, 2, 3}, 4)");
		System.out.println(" > "+A01.contains(new int[]{1, 2, 3}, 4));
		
		System.out.println(SEP);
		System.out.println("A01.getLottoNumbers(5, 1, 3) (inclusive)");
		System.out.println(" > "+Arrays.toString(A01.getLottoNumbers(5, 1, 3)));
		
		System.out.println(SEP);
		System.out.println("A01.isLeapYear(1992)");
		System.out.println(" > "+A01.isLeapYear(1992));
		System.out.println("A01.isLeapYear(1990)");
		System.out.println(" > "+A01.isLeapYear(1990));
		System.out.println("A01.isLeapYear(1900)");
		System.out.println(" > "+A01.isLeapYear(1900));
		
		System.out.println(SEP);
		System.out.println("A01.isPalindrome(\"taco cat!\")");
		System.out.println(" > "+A01.isPalindrome("taco cat!"));
		System.out.println("A01.isPalindrome(\"not cool :(\")");
		System.out.println(" > "+A01.isPalindrome("not cool :("));
		
		System.out.println(SEP);
    	System.out.println("A01.isPrime(2)");
    	System.out.println(" > "+A01.isPrime(2));
    	System.out.println("A01.isPrime(5)");
    	System.out.println(" > "+A01.isPrime(5));
    	System.out.println("A01.isPrime(15)");
    	System.out.println(" > "+A01.isPrime(15));
    	
    	System.out.println(SEP);
    	System.out.println("A01.isValid(\"_\")");
    	System.out.println(" > "+A01.isValid("_"));
    	System.out.println("A01.isValid(\"1abc\")");
    	System.out.println(" > "+A01.isValid("1abc"));
    	System.out.println("A01.isValid(\"_abc1\")");
    	System.out.println(" > "+A01.isValid("_abc1"));
    	System.out.println("A01.isValid(\"_abc.1\")");
    	System.out.println(" > "+A01.isValid("_abc.1"));
    	
    	System.out.println(SEP);
    	System.out.println("A01.sumPartialHarmonic(1)");
    	System.out.println(" > "+A01.sumPartialHarmonic(1));
    	System.out.println("A01.sumPartialHarmonic(100)");
    	System.out.println(" > "+A01.sumPartialHarmonic(100));
    	
    	System.out.println(SEP);
    	System.out.println("A01.matrixStats(new double[][] {{3, 2.5, 1}, {4, 5, 6}, {7, 8, 9}})");
    	System.out.println(" > "+Arrays.toString(A01.matrixStats(new double[][] {{3, 2.5, 1}, {4, 5, 6}, {7, 8, 9}})));
    	
    	System.out.println(SEP);
    	System.out.println("A01.numCategories(new int[] {-1, -3, 2, 0, 5, 0, 3})");
    	System.out.println(" > "+Arrays.toString(A01.numCategories(new int[] {-1, -3, 2, 0, 5, 0, 3})));
    	
    	System.out.println(SEP);
    	System.out.println("A01.pigLatin(\"Hello\")");
    	System.out.println(" > "+A01.pigLatin("Hello"));
    	System.out.println("A01.pigLatin(\"All\")");
    	System.out.println(" > "+A01.pigLatin("All"));
    	System.out.println("A01.pigLatin(\"hello\")");
    	System.out.println(" > "+A01.pigLatin("hello"));
    	System.out.println("A01.pigLatin(\"all\")");
    	System.out.println(" > "+A01.pigLatin("all"));
    	
    	System.out.println(SEP);
    	System.out.println("A01.validSn(\"SN/1234-567\")");
    	System.out.println(" > "+A01.validSn("SN/1234-567"));
    	System.out.println("A01.validSn(\"sn/1234-567\")");
    	System.out.println(" > "+A01.validSn("sn/1234-567"));
    	System.out.println("A01.validSn(\"SN/1234-57\")");
    	System.out.println(" > "+A01.validSn("SN/1234-57"));
    	System.out.println("A01.validSn(\"SN/12a4-57\")");
    	System.out.println(" > "+A01.validSn("SN/12a4-57"));
    	
    	Scanner sn = new Scanner(new File("src/cp213/serials.txt"));
    	PrintStream good = new PrintStream(new File("src/cp213/good.txt"));
    	PrintStream bad = new PrintStream(new File("src/cp213/bad.txt"));
    	System.out.println(SEP);
    	System.out.println("A01.validSnFile(sn, good, bad)");
    	A01.validSnFile(sn, good, bad);
    	System.out.println(" > check output files");

	}

}