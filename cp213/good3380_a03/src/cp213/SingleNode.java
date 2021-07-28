package cp213;

/**
 * DO NOT CHANGE THE CONTENTS OF THIS CLASS.
 *
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * value, in order to avoid copying or moving values between nodes. Data
 * structures must be updated by moving nodes, not by copying or moving value.
 *
 * @author David Brown
 * @version 2021-06-16
 */
public final class SingleNode<T> {

    /**
     * The generic value.
     */
    private T value = null;
    /**
     * Link to the next Node.
     */
    private SingleNode<T> next = null;

    /**
     * Creates a new node with value and link to next node. Not copy safe as it
     * accepts a reference to the value rather than a copy of the value.
     *
     * @param value the value to store in the node.
     * @param next  the next node to link to.
     */
    public SingleNode(final T value, final SingleNode<T> next) {
	this.value = value;
	this.next = next;
    }

    /**
     * Returns the node value. Not copy safe as it returns a reference to the value,
     * not a copy of the value.
     *
     * @return The value portion of the node.
     */
    public T getValue() {
	return this.value;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public SingleNode<T> getNext() {
	return this.next;
    }

    /**
     * Links this node to the next node.
     *
     * @param next The new node to link to.
     */
    public void setNext(final SingleNode<T> next) {
	this.next = next;
    }
}
