package cp213;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Samson Goodenough, 190723380, good3380@mylaurier.ca
 * @version 2021-05-14
 */
public class A02_Main {
	// Constants
	public static final String SEP = "-".repeat(40);

	public static void main(String[] args) throws FileNotFoundException {

		Student s = new Student(123, "Goodenough", "Samson", LocalDate.of(2002, 8, 3), new Integer[] { 0, 1, 2 });
		Student t = new Student(234, "Goodenough", "Samson", LocalDate.of(2001, 8, 3), new Integer[] { 0, 4, 2 });
		Student j = new Student(345, "Goodenough", "Samson", LocalDate.of(2000, 8, 3), new Integer[] { 0, 1, 5 });

		System.out.println(s.majorsMenu());
		System.out.println(SEP);
		System.out.println(Arrays.toString(s.majorsStringToList("1,2,3")));
		// System.out.println(SEP);
		// System.out.println(s.majorsListToString()); // private
		System.out.println(SEP);
		System.out.println(s.compareTo(t));
		System.out.println(SEP);
		System.out.println(s.majorsListToNames());
		System.out.println(SEP);
		System.out.println(s.toString());
		System.out.println(SEP);
		s.write(System.out);

		System.out.println(SEP);

		// Scanner keyboard = new Scanner(System.in);
		// Student fromKeyboard = StudentUtilities.getStudent(keyboard);
		// fromKeyboard.write(System.out);

		System.out.println(SEP);

		// System.out.println(Arrays.toString(StudentUtilities.readMajors(keyboard)));

		ArrayList<Student> students = new ArrayList<Student>();
		students.add(s);
		students.add(t);
		students.add(j);
		System.out.println(Arrays.toString(StudentUtilities.majorCounts(students)));

		System.out.println(SEP);

		Student n = StudentUtilities.readStudent("123|Kennes|Montana|2001-04-30|1,2,5");
		System.out.println(n.toString());

		System.out.println(SEP);

		ArrayList<Student> list = StudentUtilities.getByMajors(students, new Integer[] { 0, 1 });
		for (Student stud : list) {
			System.out.println(stud.toString());
		}

		System.out.println(SEP + "getByBirthDate");

		ArrayList<Student> list2 = StudentUtilities.getByBirthDate(students, LocalDate.parse("2001-08-03"));
		for (Student stud : list2) {
			System.out.println(stud.toString());
		}

		System.out.println(SEP + "getByMajor");

		ArrayList<Student> list3 = StudentUtilities.getByMajor(students, 2);
		for (Student stud : list3) {
			System.out.println(stud.toString());
		}

		System.out.println(SEP);

		File f = new File("students.txt");
		Scanner scan = new Scanner(f);

		ArrayList<Student> newlist = StudentUtilities.readStudents(scan);
		for (Student stud : newlist) {
			System.out.println(stud);
		}

		System.out.println(SEP);

		File fs = new File("output.txt");
		PrintStream ps = new PrintStream(fs);

		StudentUtilities.writeStudents(students, ps);

	}
}