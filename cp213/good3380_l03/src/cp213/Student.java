package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author David Brown
 * @version 2021-05-11
 */
public class Student extends Person {

    protected String id = null;

    /**
     * Student constructor
     *
     * @param lastName
     *            Student last name (surname): defined in Person
     * @param firstName
     *            Student first name (given name): defined in Person
     * @param id
     *            Student id number
     */
    public Student(final String lastName, final String firstName,
	    final String id) {
	super(lastName, firstName);
	this.id = id;
    }

    /**
     * Getter for id.
     *
     * @return this.id
     */
    public String getId() {
	return this.id;
    }

    @Override
    public void print() {
	// Prints contents of Student as formatted string. Overrides Person
	// print().
	System.out.println(
		this.lastName + ", " + this.firstName + ": " + this.id);
    }

}
