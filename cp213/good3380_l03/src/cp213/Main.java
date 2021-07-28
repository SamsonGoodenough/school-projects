package cp213;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Testing class in simple example of inheritance / polymorphism.
 *
 * @author Your name here
 * @version 2021-06-07
 */
public class Main {
	public static final String SEPARATOR = "-".repeat(40);

	/*
	 * Create examples of Person, Student, Professor, InstructionalAssistant.
	 */
	private static Person person1 = new Person("Snord", "Cranston");
	private static Student student1 = new Student("Sharma", "Priyanka", "7384737");
	private static Professor prof1 = new Professor("Hakim", "Abdul", "History");
	private static InstructionalAssistant ia1 = new InstructionalAssistant("Chin", "Li-meng", "9857478", "CP213");
	private static Person iaPerson1 = new InstructionalAssistant("Rodrigues", "Estevan", "6723564", "CP363");
	private static CAS cas1 = new CAS("Goodenough", "Samson", "Compsci", "202105");
	private static CAS cas2 = new CAS("Goodenough", "Samson", "Compsci", "202109");

	/**
	 * Test code.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		printExample1();
		printExample2();
		task1();
		task2();
		task3();
	}

	/**
	 * Adds data to and prints ArrayList.
	 */
	public static void printExample1() {
		// Defines an unsorted list of Person objects. (Uses generics).
		final ArrayList<Person> people = new ArrayList<Person>();

		// Shows polymorphism - since all have base class of Person, we are allowed to
		// store all of them in the same list.
		people.add(person1);
		people.add(student1);
		people.add(prof1);
		people.add(ia1);
		people.add(iaPerson1);

		System.out.println("Print Example 1 - People:");
		System.out.println();

		for (final Person person : people) {
			// Again shows polymorphism - calls print() appropriate to actual
			// object type, not just Person print().
			person.print();
			System.out.println();
		}
		System.out.println(SEPARATOR);
	}

	/**
	 * Adds data to and prints TreeSet.
	 */
	public static void printExample2() {
		// Defines a sorted set of Person objects. Sorted according to Person
		// compareTo method - sort applies to all child classes. (Uses generics)
		final TreeSet<Person> set = new TreeSet<Person>();

		// Shows polymorphism - since all objects have base class of Person, we are
		// allowed to store all of them in the same set.
		set.add(person1);
		set.add(student1);
		set.add(prof1);
		set.add(ia1);
		set.add(iaPerson1);

		System.out.println("Print Example 2 - Sorted People:");
		System.out.println();

		for (final Person person : set) {
			// Print all objects in sorted order by name.
			person.print();
			System.out.println();
		}
		System.out.println(SEPARATOR);
	}

	/**
	 * Add Task 1 code here.
	 */
	public static void task1() {
		// Defines a sorted set of Person objects. Sorted according to Person
		// compareTo method - sort applies to all child classes. (Uses generics)
		final TreeSet<Person> set = new TreeSet<Person>();

		// Shows polymorphism - since all objects have base class of Person, we are
		// allowed to store all of them in the same set.
		set.add(person1);
		set.add(student1);
		set.add(prof1);
		set.add(ia1);
		set.add(iaPerson1);
		set.add(cas1);

		System.out.println("Task 1 - Add CAS:");
		System.out.println();

		for (final Person person : set) {
			// Print all objects in sorted order by name.
			person.print();
			System.out.println();
		}
		System.out.println(SEPARATOR);
	}

	/**
	 * Add Task 2 code here.
	 */
	public static void task2() {
		// Define and print results of various comparisons here
		System.out.println("Task 2:");

		System.out.println("Compares names:");
		System.out.println("\tperson1.compareTo(cas1): "+person1.compareTo(cas1));
		System.out.println("\tcas1.compareTo(person1): "+cas1.compareTo(person1));
		System.out.println("Compares terms:");
		System.out.println("\tcas1.compareTo(cas2): "+cas1.compareTo(cas2));
		
		System.out.println(SEPARATOR);

	}

	/**
	 * Add Task 3 code here.
	 */
	public static void task3() {
		// Defines an sorted set of CAS objects. (Uses generics).
		System.out.println("Task 3:");
		final TreeSet<CAS> set = new TreeSet<CAS>();
//		set.add(person1);
//		set.add(student1);
//		set.add(prof1);
//		set.add(ia1);
//		set.add(iaPerson1);
		set.add(cas1);
		set.add(cas2);
		
		for (final CAS cas : set) {
			// Print all objects in sorted order by name.
			cas.print();
			System.out.println();
		}

		System.out.println(SEPARATOR);
	}

}
