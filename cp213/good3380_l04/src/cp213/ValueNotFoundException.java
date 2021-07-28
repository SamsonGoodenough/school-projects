package cp213;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

// ---------------------------------------------------------------------
/**
 * @author David Brown
 * @version 2021-07-06
 *
 *          Custom exception for the failure of an array search.
 */
@SuppressWarnings("serial")
public class ValueNotFoundException extends Exception {
    private final Object value;

    // ---------------------------------------------------------------------
    /**
     * @param newValue
     *            The object being searched for in the array.
     */
    public ValueNotFoundException(Object newValue) {
	this.value = newValue;
    }

    // ---------------------------------------------------------------------
    /**
     * @param newValue
     *            The object being searched for in the array.
     * @param errorLog
     *            The error log file to record the search failure in.
     */
    public ValueNotFoundException(Object newValue, File errorLog) {
	this.value = newValue;

	try {
	    // Open an existing error log file (or create a new one if
	    // non-existent) and append the error message to it.
	    PrintStream logFile = new PrintStream(errorLog);
	    logFile.println("Could not find " + this.value.toString());
	    logFile.close();
	} catch (IOException e) {
	    // Could not open the error log file.
	    System.err
	    .println("Could not open error log " + errorLog.getName());
	}
    }

    // ---------------------------------------------------------------------
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#getMessage() Overrides parent method.
     */
    @Override
    public String getMessage() {
	return ("Could not find " + this.value.toString() + " in array.");
    }

    // ---------------------------------------------------------------------
}
