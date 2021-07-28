package cp213;

/**
 * Stores a value and an occurrence count for that value.
 *
 * @author David Brown
 * @version 2021-07-05
 */
public class CountedValue<T extends Comparable<T>> {

    private int count = 0; // value count
    // Attributes.
    private T value = null; // value

    /**
     * Copy constructor.
     *
     * @param source Another CountedCharacter object.
     */
    public CountedValue(final CountedValue<T> source) {
	this.value = source.value;
	this.count = source.count;
    }

    /**
     * Constructor for key version of object. (value count defaults to 0)
     *
     * @param value The value to be counted.
     */
    public CountedValue(final T value) {
	this.value = value;
    }

    /**
     * Constructor.
     *
     * @param value  The value to be counted.
     * @param count The value count.
     */
    public CountedValue(final T value, final int count) {
	this.value = value;
	this.count = count;
    }

    /**
     * Comparison method.
     *
     * @param target Object to compare against.
     * @return less than 0 if this value comes before target value, greater than 0 if
     *         this value comes after target value, 0 if the values are the same.
     */
    public int compareTo(final CountedValue<T> target) {
	return this.value.compareTo(target.value);
    }

    /**
     * Decrements the value count.
     */
    public void decrementCount() {
	this.count--;
    }

    /**
     * Returns this value count.
     *
     * @return this value count.
     */
    public int getCount() {
	return this.count;
    }

    /**
     * Returns this value.
     *
     * @return this value.
     */
    public T getValue() {
	return this.value;
    }

    /**
     * Increments the value count.
     */
    public void incrementCount() {
	this.count++;
    }

    /**
     * Sets the value count.
     *
     * @param count the new value count.
     */
    public void setCount(final int count) {
	this.count = count;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{" + this.value + ": " + this.count + "}";
    }

}
