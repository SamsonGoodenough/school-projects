package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author David Brown
 * @version 2021-05-11
 */
public class InstructionalAssistant extends Student {

    private String course = null;

    /**
     * @param lastName
     *            Student last name (surname): defined in Person
     * @param firstName
     *            Student first name (given name): defined in Person
     * @param id
     *            Student id number: defined in Student
     * @param course
     *            InstructionalAssistant course
     */
    public InstructionalAssistant(final String lastName, final String firstName,
	    final String id, final String course) {
	super(lastName, firstName, id);
	this.course = course;
    }

    /**
     * Getter for course.
     *
     * @return this.course
     */
    public String getCourse() {
	return this.course;
    }

    @Override
    public void print() {
	// Prints contents of InstructionalAssistant as formatted string.
	// Overrides Student print().
	System.out.println(
		this.lastName + ", " + this.firstName + ": " + this.id);
	System.out.println("Course: " + this.course);
    }
}
