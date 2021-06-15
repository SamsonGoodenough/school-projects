package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Utilities for working with Student objects.
 *
 * @author Samson Goodenough, 190723380, good3380@mylaurier.ca
 * @version 2021-05-14
 */
public class StudentUtilities {

    /**
     * Creates a list of Students whose birthDates are equal to or later than
     * birthDate.
     *
     * @param students  list of students
     * @param birthDate to compare against
     * @return list of students for birthDate
     */
    public static ArrayList<Student> getByBirthDate(final ArrayList<Student> students, final LocalDate birthDate) {
    	final ArrayList<Student> bStudents = new ArrayList<>();

    	for (Student s : students) {
	    	if (s.getBirthDate().compareTo(birthDate) >= 0) {
	    		bStudents.add(s);
	    	}
	    }

    	return bStudents;
    }

    /**
     * Creates a list of Students whose list of majors include major.
     *
     * @param students list of students
     * @param major    major to compare against
     * @return list of students for major
     */
    public static ArrayList<Student> getByMajor(final ArrayList<Student> students, final int major) {
    	final ArrayList<Student> mStudents = new ArrayList<>();
    	boolean found = false;
    	int i = 0; // since I don't think we can use break

    	for (Student s : students) {
    		found = false;
    		i = 0;
    		Integer[] m = s.getMajors();
	    	while (!found && i < m.length) {
	    		if (m[i] == major) {
	    			mStudents.add(s);
	    			found = true;
	    		}
	    		
	    		i++;
	    	}
	    }

    	return mStudents;
    }

    /**
     * Creates a list of Students whose list of majors include all the major codes
     * in majors.
     *
     * @param students list of students
     * @param majors   majors list to compare against
     * @return list of students for majors
     */
    public static ArrayList<Student> getByMajors(final ArrayList<Student> students, final Integer[] majors) {
    	final ArrayList<Student> gStudents = new ArrayList<>();

    	for (Student s : students) {
    		if (Arrays.asList(s.getMajors()).containsAll(Arrays.asList(majors))) {
    			gStudents.add(s);
    		}
    	}

    	return gStudents;
    }

    /**
     * Creates a list of Students from a particular birth year.
     *
     * @param students list of students
     * @param year birth date year of students
     * @return list of students for birthDate
     */
    public static ArrayList<Student> getByYear(final ArrayList<Student> students, final int year) {
		final ArrayList<Student> yStudents = new ArrayList<>();
	
		for (Student s : students) {
			if (year == s.getBirthDate().getYear()) {
				yStudents.add(s);
			}
		}
	
		return yStudents;
    }

    /**
     * Creates a Student object by requesting data from a user.
     *
     * @param keyboard a keyboard Scanner
     * @return a Student object
     */
    public static Student getStudent(final Scanner keyboard) {
    	System.out.println("Type the prompted information followed by pressing Enter.");

    	// id
    	System.out.print("ID: ");
    	int id = Integer.parseInt(keyboard.nextLine());
    	
	    // name
    	System.out.print("Surname: ");
    	String surname = keyboard.nextLine();
    	System.out.print("Forename: ");
    	String forname = keyboard.nextLine();
    	
    	// birthdate
    	System.out.print("BirthDate: ");
    	LocalDate birthdate = LocalDate.parse(keyboard.nextLine());
    	
    	// majors
    	Integer[] majors = readMajors(keyboard);
    	
    	return new Student(id, surname, forname, birthdate, majors);
    }

    /**
     * Counts the number of students in each major given in Student.MAJORS.
     *
     * @param students list of students
     * @return Number of majors across all Students.
     */
    public static int[] majorCounts(final ArrayList<Student> students) {

	    int[] count = new int[Student.MAJORS.length];
	    
	    for (Student s : students) {
	    	for (Integer major : s.getMajors()) {
	    		count[major]++;
	    	}
	    }
	    
	    return count;

    }

    /**
     * Asks a user to select majors from a list of majors and returns an integer
     * list of the majors chosen.
     *
     * @param keyboard Keyboard input.
     * @return An integer list of major codes.
     */
    public static Integer[] readMajors(final Scanner keyboard) {
		final ArrayList<Integer> majors = new ArrayList<>();
	
		System.out.println(Student.majorsMenu()+"\n");
	    System.out.print("Enter a major number ('q' to quit): ");
	    String s = keyboard.nextLine();
	    int m;
	    
	    while (!s.equals("q")) {
	    	try {
	    		m = Integer.parseInt(s);
	    		
	    		if (!majors.contains(m)) {
	    			if (m >= Student.MAJORS.length) {
	    				System.out.println(String.format("Major code must be less than %d", Student.MAJORS.length));
	    			} else if (m < 0) {
	    				System.out.println("Major code must be 0 or greater");
	    			} else {
	    				majors.add(m);
	    			}
	    		} else {
	    			System.out.println("Major code already in list");
	    		}
	    	} catch (NumberFormatException e) {
	    		System.out.println("Not a number");
	    	}

		    System.out.print("Enter a major number ('q' to quit): ");
	    	s = keyboard.nextLine();
	    }
	
		return majors.toArray(new Integer[1]);
    }

    /**
     * Creates and returns a Student object from a line of formatted string data.
     *
     * @param line a vertical bar-delimited line of student data in the format
     *             id|surname|forename|birthDate|majorCodes
     * @return the data from line as a Student object
     */
    public static Student readStudent(final String line) {

	    String[] args = line.split("[|]");
	    Student s;
	    
	    if (args.length == 5) { // number of parameters
		    int id = Integer.parseInt(args[0]);
		    String surname = args[1];
		    String forename = args[2];
		    LocalDate birthDate = LocalDate.parse(args[3]);
		    Integer[] majorCodes = Student.majorsStringToList(args[4]);
		    
		    s = new Student(id, surname, forename, birthDate, majorCodes);
	    } else {
	    	s = null;
	    }
	    
	    return s;

    }

    /**
     * Reads a list of Students from a file.
     *
     * @param fileScanner The file to read.
     * @return A list of Student objects.
     * @throws FileNotFoundException file not found
     */
    public static ArrayList<Student> readStudents(final Scanner fileScanner) throws FileNotFoundException {
		final ArrayList<Student> students = new ArrayList<>();
	
		String line;
		Student s;
		
		while (fileScanner.hasNextLine()) {
			line = fileScanner.nextLine();
			
			s = readStudent(line);
			if (s != null) {
				students.add(s);
			}
		}
	
		return students;
    }

    /**
     * Writes the contents of students to a file. Overwrites or creates a new file of
     * Student objects converted to strings.
     *
     * @param students List of Students.
     * @param ps       PrintStream to write Student data to.
     */
    public static void writeStudents(final ArrayList<Student> students, PrintStream ps) {

	    for (Student s : students) {
	    	s.write(ps);
	    }

    }

}