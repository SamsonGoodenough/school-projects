package cp213;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Samson Goodenough (190723380)
 * @version 2021-05-20
 */
public class A01 {

    // Constants
    public static final String VOWELS = "aeiouAEIOU";
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered text
     */
    public static String shift(String s, int n) {
    	String result = "";
    	n = n%ALPHA_LENGTH;
    	
    	if (n < 0) {
    		n += ALPHA_LENGTH;
    	}

    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (Character.isLetter(c)) {
    			if (Character.isUpperCase(c)) {
        			int l = (int)c - (int)'A';
        			l = (l + (n) >= ALPHA_LENGTH) ? (l + (n) - 26) : (l + (n));
        			c = (char)(l + (int)'A');
        		} else {
        			int l = (int)c - (int)'a';
        			l = (l + (n) >= ALPHA_LENGTH) ? (l + (n) - 26) : (l + (n));
        			c = (char)(l + (int)'a');
        		}
    		}
    		
    		result += c;
    	}
    	
		return result;

    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered text
     */
    public static String substitute(String s, String ciphertext) {
    	
    	String result = "";
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (Character.isLetter(c)) {
    			if (Character.isUpperCase(c)) {
        			c = ciphertext.charAt((int)c - (int)'A');
        		} else {
        			c = Character.toLowerCase(ciphertext.charAt((int)c - (int)'a'));
        		}
    		}
    		
    		result += c;
    	}
    	
		return result;

    }

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(String str) {

    	for (int i = 0; i < str.length(); i++) {
    		if (!Character.isDigit(str.charAt(i))) {
    			return false;
    		}
    	}
    	
    	return true;

    }

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(double target, double v1, double v2) {

    	double dist1 = (target - v1 < 0) ? (target - v1)*-1 : (target - v1);
    	double dist2 = (target - v2 < 0) ? (target - v2)*-1 : (target - v2);
    	
    	return (dist1 <= dist2) ? v1 : v2;

    }

    /**
     * Looks for a integer in an array of integers.
     *
     * @param values array of int
     * @param target value to search for
     * @return true if target in values, false otherwise
     */
    public static boolean contains(int[] values, int target) {

    	for	(int i = 0; i < values.length; i++) {
    		if (values[i] == target) {
    			return true;
    		}
    	}
    	
    	return false;

    }

    /**
     * Generates a sorted list of unique lottery numbers.
     *
     * @param n    number of lottery numbers to generate
     * @param low  low value of the lottery number range
     * @param high high value of the lottery number range
     * @return a list of unique random lottery numbers
     */
    public static int[] getLottoNumbers(int n, int low, int high) {

    	// assuming inclusive
    	int[] numbers = new int[n];
    	Random rand = new Random();
    	for (int i = 0; i < n; i++) {
    		numbers[i] = rand.nextInt(high - low + 1) + low;
    	}
    	
    	return numbers;

    }

    /**
     * Determines whether or not a year is a leap year.
     *
     * @param year The year to test (int greater than 0)
     * @return true if year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(final int year) {
    	
    	if (year % 4 == 0) {
    		if (year % 100 == 0) {
    			if (year % 400 == 0) {
    				return true;
    			}
    		} else {
    			return true;
    		}
    	}
    	
    	return false;

    }

    /**
     * Determines if s is a palindrome. Ignores case, spaces, digits, and
     * punctuation in the string parameter s. Examples of valid palindromes:
     * <ul>
     *   <li>racecar</li>
     *  <li>Otto</li>
     *  <li>Able was I ere I saw Elba</li>
     *  <li>A man, a plan, a canal, Panama!</li>
     * </ul>
     *
     * @param s a string
     * @return true if s is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String s) {
    	
    	int left = 0;
    	int right = s.length()-1;

    	while (left < right) {
    		while (!Character.isAlphabetic(s.charAt(left))) {
    			left++;
    		}
    		while (!Character.isAlphabetic(s.charAt(right))) {
    			right--;
    		}
    		
    		if (s.charAt(left) != s.charAt(right)) {
    			return false;
    		} else {
    			left++;
    			right--;
    		}
    	}
    	
    	return true;

    }

    /**
     * Determines if n is a prime number.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(int n) {
    	boolean result = true;

    	if (n <= 1) {
    		result = false;
    	} else {
    		int i = 2;
    		while (i <= n/2 && result) {
    			if (n % i == 0) {
    				result = false;
    			}
    			
    			i++;
    		}
    	}
    	
    	return result;

    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

    	boolean valid = true;
    	if (name.length() <= 0) {
    		valid = false;
    	} else if (name.length() == 1) {
    		valid = (Character.isAlphabetic(name.charAt(0)));
    	} else {
    		int i = 0;
    		while (i < name.length() && valid) {
    			if (i == 0) {
    				if (!Character.isAlphabetic(name.charAt(0)) && name.charAt(0) != '_'){
    					valid = false;
    				}
    			} else {
    				if (!Character.isAlphabetic(name.charAt(i)) && !Character.isDigit(name.charAt(i)) && name.charAt(i)!= '_') {
    					valid = false;
    				}
    			}
    			
    			i++;
    		}
    	}
    	
    	return valid;

    }

    /**
     * Determines the smallest, largest, total, and average of the values in the 2D
     * list a. You may assume there is at least one value in a and that a is a
     * square matrix - i.e. the number of columns per row is the same. a must be
     * unchanged.
     *
     * @param a - a 2D list of numbers (2D list of double)
     *
     * @return a list of four double values containing the smallest number in a,the
     *         largest number in a, the total of all numbers in a, and the average
     *         of all numbers in a, in that order.
     */
    public static double[] matrixStats(double[][] a) {
    	double[] result = new double[4]; // min, max, sum, avg

    	result[0] = a[0][0]; // min
    	result[1] = a[0][0]; // max
    	for (int i = 0; i < a.length; i++) {
    		for (int j = 0; j < a.length; j++) {
    			// check new min
    			if (a[i][j] < result[0]) {
    				result[0] = a[i][j];
    			}
    			
    			// check new max
    			if (a[i][j] > result[1]) {
    				result[1] = a[i][j];
    			}
    			
    			// add to sum
    			result[2] += a[i][j];
    			
    		}
    	}
    	result[3] = result[2]/(a.length * a.length);
    	
    	return result;

    }

    /**
     * Counts and returns how may positives, negatives, and zeroes there are in an
     * array of integers.
     *
     * @param numbers array of int
     * @return number of negative values, number of zero values, number of positive
     *         values
     */
    public static int[] numCategories(int[] numbers) {

    	int[] result = new int[] {0, 0, 0};
    	
    	for (int i = 0; i < numbers.length; i++) {
    		if (numbers[i] < 0) {
    			result[0]++;
    		} else if (numbers[i] == 0) {
    			result[1]++;
    		} else {
    			result[2]++;
    		}
    	}
    	
    	return result;

    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
    	
    	String newword = word;
    	boolean leadingCap;
    	
    	if (word.length() >= 0){
    		if ((VOWELS+"yY").indexOf(word.charAt(0)) != -1) { // starts with vowel
    			newword += "way";
    		} else { // starts with consonant
    			while ((VOWELS+"yY").indexOf(newword.charAt(0)) == -1) { // while first is consonant
    				leadingCap = false;
    				if (Character.isUpperCase(newword.charAt(0))) {
    					leadingCap = true;
    				}
    				
    				newword += Character.toLowerCase(newword.charAt(0));
    				newword = newword.substring(1);
    				
    				if (leadingCap) {
    					newword = Character.toUpperCase(newword.charAt(0)) + newword.substring(1);
    				}
    			}
    			
    			newword += "ay";
    		}
    	}

    	return newword;

    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive).
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(int n) {

    	double h = 0;
    	
    	for (int i = 1; i <= n; i++) {
    		h += (1f/i);
    	}
    	
    	return h;

    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(String sn) {
    	boolean valid = false;

    	if (sn.length() == "SN/nnnn-nnn".length()) {
    		if ((sn.substring(0, 3) + sn.charAt(7)).equals("SN/-")) {
    			String numbers = sn.substring(3, 7) + sn.substring(8, sn.length());
    			valid = true;
    			for (int i = 0; i < numbers.length(); i++) {
    				if (!Character.isDigit(numbers.charAt(i))) {
    					valid = false;
    				}
    			}
    		}
    	}
    	
    	return valid;

    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(Scanner fileIn, PrintStream goodSns, PrintStream badSns) {
    	String sn;
    	while (fileIn.hasNextLine()) {
    		sn = fileIn.nextLine();
    		
    		if (validSn(sn)) {
    			goodSns.println(sn);
    		} else {
    			badSns.println(sn);
    		}
    	}
    	
    	goodSns.flush();
    	badSns.flush();

    }

}