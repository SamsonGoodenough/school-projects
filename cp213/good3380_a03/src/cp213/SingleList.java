package cp213;

import java.util.NoSuchElementException;

/**
 * COMPLETE THE CODE AT // your code here
 *
 * A single linked list structure of <code>Node T</code> objects. These value
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author // your name, id, email here
 * @version 2021-06-16
 * @param <T> this SingleList value type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The value to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {

		SingleNode<T> curr = this.front;

		if (curr != null) {
			if (curr.getValue().equals(key)) {
				SingleNode<T> node = new SingleNode<T>(null, curr);
				curr = node;
			} else {
				while (curr.getNext() != null && !curr.getNext().getValue().equals(key)) {
					curr = curr.getNext();
				}
				
				if (curr.getNext() == null) {
					curr = null;
				}
			}
		}

		return curr;
	}

	/**
	 * Appends value to the end of this SingleList.
	 *
	 * @param value The value to append.
	 */
	public void append(final T value) {

		SingleNode<T> node = new SingleNode<T>(value, null);

		if (this.front == null) {
			this.front = node;
			this.rear = this.front;
		} else {
			this.rear.setNext(node);
			this.rear = this.rear.getNext();
		}

		this.length++;

		return;
	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each value formerly present in this SingleList. The first occurrence of
	 * each value is preserved.
	 */
	public void clean() {

		SingleNode<T> curr = this.front;
		while (curr != null && curr.getNext() != null) {
			SingleNode<T> key = curr;

			while (key.getNext() != null) {
				if (key.getNext().getValue().equals(curr.getValue())) {
					key.setNext(key.getNext().getNext());
					this.length--;
				} else {
					key = key.getNext();
				}
			}

			curr = curr.getNext();
		}

		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: value must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {

		while (left.getLength() != 0 && right.getLength() != 0) {
			this.moveFrontToRear(left);
			this.moveFrontToRear(right);
		}

		while (left.getLength() != 0) {
			this.moveFrontToRear(left);
		}

		while (right.getLength() != 0) {
			this.moveFrontToRear(right);
		}

		return;
	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key value to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {

		SingleNode<T> node = this.linearSearch(key);

		return node == null ? false : true;
	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The value to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {

		SingleNode<T> curr = this.front;
		int count = 0;

		while (curr != null) {
			if (curr.getValue().equals(key)) {
				count++;
			}

			curr = curr.getNext();
		}

		return count;
	}

	/**
	 * Finds and returns the value in list that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value that matches key, null otherwise.
	 */
	public T find(final T key) {

		SingleNode<T> node = this.linearSearch(key);
		T value;

		if (node == null) {
			value = null;
		} else {
			value = node.getNext().getValue();
		}

		return value;
	}

	/**
	 * Get the nth item in this SingleList.
	 *
	 * @param n The index of the item to return.
	 * @return The nth item in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {

		SingleNode<T> node = this.front;

		if (n < 0 || n > this.getLength() || node == null) {
			throw new ArrayIndexOutOfBoundsException(n + " is not a valid index");
		}

		int i = n;
		while (i > 0) {
			node = node.getNext();
			i--;
		}

		return node.getValue();
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same values in the same order as
	 *         source, false otherwise.
	 */
	public boolean identical(final SingleList<T> source) {

		boolean identical = true;
		if (this.getLength() != source.getLength()) {
			identical = false;
		} else {
			SingleNode<T> currThis = this.front;
			SingleNode<T> currSource = source.front;

			while (currThis != null && currThis.getValue().equals(currSource.getValue())) {
				currThis = currThis.getNext();
				currSource = currSource.getNext();
			}

			if (currThis != null) {
				identical = false;
			}
		}

		return identical;
	}

	/**
	 * Finds the first location of a value by key in this SingleList.
	 *
	 * @param key The value to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {

		SingleNode<T> node = this.front;
		int index = 0;

		while (node != null && !node.getValue().equals(key)) {
			index++;
			node = node.getNext();
		}

		if (node == null) {
			index = -1;
		}

		return index;
	}

	/**
	 * Inserts value into this SingleList at index i. If i greater than the length
	 * of this SingleList, append value to the end of this SingleList.
	 *
	 * @param i     The index to insert the new value at.
	 * @param value The new value to insert into this SingleList.
	 */
	public void insert(int i, final T value) {

		if (i < 0) {
			throw new ArrayIndexOutOfBoundsException(i + " is not a valid index");
		}

		if (i == 0) {
			SingleNode<T> node = new SingleNode<T>(value, this.front);
			this.front = node;

			if (this.getLength() == 0) {
				this.rear = node;
			}
		} else if (i >= this.getLength()) {
			this.rear.setNext(new SingleNode<T>(value, null));
			this.rear = this.rear.getNext();

			if (this.getLength() == 0) {
				this.rear = this.front;
			}
		} else {
			SingleNode<T> node = this.front;
			for (int j = 1; j < i; j++) {
				node = node.getNext();
			}

			node.setNext(new SingleNode<T>(value, node.getNext()));
		}

		this.length++;

		return;
	}

	/**
	 * Creates an intersection of two other SingleLists into this SingleList. Copies
	 * value to this SingleList. left and right SingleLists are unchanged. Values
	 * from left are copied in order first, then values from right are copied in
	 * order.
	 *
	 * @param left  The first SingleList to create an intersection from.
	 * @param right The second SingleList to create an intersection from.
	 */
	public void intersection(final SingleList<T> left, final SingleList<T> right) {

		SingleNode<T> curr = left.front;

		while (curr != null) {
			if (!this.contains(curr.getValue()) && right.contains(curr.getValue())) {
				this.append(curr.getValue());
			}

			curr = curr.getNext();
		}

		return;
	}

	/**
	 * Finds the maximum value in this SingleList.
	 *
	 * @return The maximum value.
	 */
	public T max() {

		SingleNode<T> curr = this.front;
		T max = curr.getValue();
		curr = curr.getNext();

		while (curr != null) {
			if (curr.getValue().compareTo(max) > 0) {
				max = curr.getValue();
			}
			
			curr = curr.getNext();
		}

		return max;
	}

	/**
	 * Finds the minimum value in this SingleList.
	 *
	 * @return The minimum value.
	 */
	public T min() {

		SingleNode<T> curr = this.front;
		T min = curr.getValue();
		curr = curr.getNext();

		while (curr != null) {
			if (curr.getValue().compareTo(min) < 0) {
				min = curr.getValue();
			}

			curr = curr.getNext();
		}

		return min;

	}

	/**
	 * Inserts value into the front of this SingleList.
	 *
	 * @param value The value to insert into the front of this SingleList.
	 */
	public void prepend(final T value) {

		this.insert(0, value);

		return;
	}

	/**
	 * Finds, removes, and returns the value in this SingleList that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value matching key, null otherwise.
	 */
	public T remove(final T key) {

		SingleNode<T> node = this.linearSearch(key);
		T value = null;

		if (node != null) {
			value = node.getNext().getValue();
			node.setNext(node.getNext().getNext());

			this.length--;
		}

		return value;
	}

	/**
	 * Removes the value at the front of this SingleList.
	 *
	 * @return The value at the front of this SingleList.
	 */
	public T removeFront() {
		T value = null;

		if (this.front != null) {
			if (this.front == this.rear) {
				this.rear = null;
			}

			value = this.front.getValue();
			this.front = this.front.getNext();
			this.length--;
		}

		return value;
	}

	/**
	 * Finds and removes all values in this SingleList that match key.
	 *
	 * @param key The value to search for.
	 */
	public void removeMany(final T key) {

		SingleNode<T> curr = new SingleNode<T>(null, this.front);

		while (curr != null && curr.getNext() != null) {
			if (curr.getNext().getValue().equals(key)) {
				if (this.front == curr.getNext()) {
					this.removeFront();
				} else if (this.rear == curr.getNext()) {
					curr.setNext(null);
					this.rear = curr;
				} else {
					curr.setNext(curr.getNext().getNext());
				}

				this.length--;
			}

			curr = curr.getNext();
		}

		return;
	}

	/**
	 * Reverses the order of the values in this SingleList.
	 */
	public void reverse() {

		SingleNode<T> newFront = null;
		SingleNode<T> thisFront = null;

		while (this.front != null) {
			thisFront = this.front.getNext();
			this.front.setNext(newFront);
			
			newFront = this.front;
			this.front = thisFront;
		}
		
		this.front = newFront;

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * item than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {

		int rightLength = this.getLength() / 2;
		int leftLength = this.getLength() - rightLength;
		
		while (leftLength > 0) {
			left.moveFrontToRear(this);
			leftLength--;
		}
		
		while (rightLength > 0) {
			right.moveFrontToRear(this);
			rightLength--;
		}

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

		int i = 0;
		int currLength = this.getLength();
		
		while (currLength > 0) {
			if (i % 2 == 0) {	// even
				left.moveFrontToRear(this);
			} else {			// odd
				right.moveFrontToRear(this);
			}
			currLength--;
			i++;
		}

		return;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies value
	 * to this list. left and right SingleLists are unchanged. Values from left are
	 * copied in order first, then values from right are copied in order.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {

		SingleNode<T> currLeft = left.front;
		SingleNode<T> currRight = right.front;
		
		while (currLeft != null) {
			this.append(currLeft.getValue());
			currLeft = currLeft.getNext();
		}
		
		while (currRight != null) {
			this.append(currRight.getValue());
			currRight = currRight.getNext();
		}

		return;
	}
}
