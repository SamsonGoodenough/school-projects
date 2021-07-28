package cp213;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Sample testing for Assignment 3 Data Structures.
 *
 * @author David Brown
 * @version 2021-06-17
 */
public class A03Main {
    private static final String LINE = "-".repeat(40);
    private static final String TEST_LINE = "=".repeat(80);

    /**
     * Fills a SingleList with the contents of values.
     *
     * @param target SingleList to write to
     * @param values array of Integer values
     */
    private static void fillSingleList(SingleList<Integer> target, Integer[] values) {
	for (Integer value : values) {
	    target.append(value);
	}
	return;
    }

    /**
     * Returns an array of random integers.
     *
     * @return an array of random integers
     */
    private static Integer[] getValues() {
	Random rd = new Random(); // creating Random object
	Integer[] arr = new Integer[7];

	for (int i = 0; i < arr.length; i++) {
	    arr[i] = rd.nextInt(50); // storing random integers in an array
	}
	return arr;
    }

    /**
     * Shuffles the contents of an integer array.
     *
     * @param array the Integer array to shuffle
     */
    private static void shuffleArray(Integer[] array) {
	int index, temp;
	Random random = new Random();
	for (int i = array.length - 1; i > 0; i--) {
	    index = random.nextInt(i + 1);
	    temp = array[index];
	    array[index] = array[i];
	    array[i] = temp;
	}
    }

    /**
     * Test SingleList.
     */
    private static void testSingleList() {
	// Test values.
	int sameValue = 75;
	int badValue = 999;
	// Test arrays.
	Integer[] testValues = getValues();
	int length = testValues.length;
	Integer[] sortedValues = Arrays.copyOf(testValues, testValues.length);
	Arrays.sort(sortedValues);
	Integer[] sameValues = new Integer[length];
	Arrays.fill(sameValues, sameValue);
	Integer[] reverseValues = new Integer[length];

	for (int i = 0; i < length; i++) {
	    reverseValues[i] = testValues[length - 1 - i];
	}
	Integer[] uniqueValues1 = new Integer[length];

	for (int i = 0; i < length; i++) {
	    uniqueValues1[i] = i;
	}
	Integer[] uniqueValues2 = Arrays.copyOf(uniqueValues1, length);
	// Test SingleLists.
	SingleList<Integer> source = new SingleList<>();
	SingleList<Integer> target = new SingleList<>();
	SingleList<Integer> same = new SingleList<>();
	SingleList<Integer> left = new SingleList<>();
	SingleList<Integer> right = new SingleList<>();
	// Tests.
	System.out.println(TEST_LINE);
	System.out.println("Testing SingleList");
	System.out.println(LINE);
	System.out.println("SingleList<Integer> source = new SingleList<>();");
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println(LINE);
	Integer[] appendValues = Arrays.copyOfRange(testValues, length - 2, length);
	System.out.println("Append values: " + Arrays.toString(appendValues));
	for (Integer value : appendValues) {
	    System.out.println("  append: " + value);
	    source.append(value);
	}
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println("  peek {" + appendValues[0] + "}: " + source.peek());
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	System.out.println("prepend: " + testValues[0]);
	source.prepend(testValues[0]);
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println("  peek {" + testValues[0] + "}: " + source.peek());
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	Integer[] insertValues = Arrays.copyOfRange(testValues, 1, length - 2);
	System.out.println("Insert values: " + Arrays.toString(appendValues));
	int i = 1;
	for (Integer value : insertValues) {
	    System.out.println(String.format("  insert: (%d, %d)", i, value));
	    source.insert(i, value);
	    i++;
	}
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[0], source.peek()));
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	System.out.println(String.format("contains %d {false}: %b", badValue, source.contains(badValue)));
	int value = testValues[length / 2];
	System.out.println(String.format("contains %d {true}: %b", value, source.contains(value)));
	System.out.println(LINE);
	System.out.println(String.format("find %d {%s}: ", badValue, source.find(badValue)));
	System.out.println(String.format("find %d {%d}: %d", value, value, source.find(value)));
	System.out.println(LINE);
	System.out.println(String.format("get %d {%d}: %d", length / 2, value, source.get(length / 2)));
	System.out.println(LINE);
	System.out.println(String.format("index %d {%d}: %d", value, length / 2, source.index(value)));
	System.out.println(String.format("index %d {%d}: %d", badValue, -1, source.index(badValue)));
	System.out.println(LINE);
	System.out.println(String.format("max {%d}: %d", sortedValues[length - 1], source.max()));
	System.out.println(String.format("min {%d}: %d", sortedValues[0], source.min()));
	System.out.println(LINE);
	System.out.println("Contents: " + valuesToString(source));
	System.out.println(String.format("  count %d {%d}: %d", badValue, 0, source.count(badValue)));
	fillSingleList(same, sameValues);
	System.out.println("Contents: " + valuesToString(same));
	System.out.println(String.format("  count %d {%d}: %d", sameValue, length, same.count(sameValue)));
	System.out.println(LINE);
	System.out.println("Contents: " + valuesToString(same));
	same.clean();
	System.out.println(String.format("  clean {[%d]}: %s", sameValue, valuesToString(same)));
	System.out.println(LINE);
	same = new SingleList<>();
	fillSingleList(same, sameValues);
	System.out.println("Contents: " + valuesToString(same));
	System.out.println(
		String.format("  removeMany %d {%s}: %s", badValue, Arrays.toString(sameValues), valuesToString(same)));
	same.removeMany(sameValue);
	System.out.println(String.format("  removeMany %d {[]}: %s", sameValue, valuesToString(same)));
	System.out.println(LINE);
	System.out.println("Contents: " + valuesToString(source));
	System.out.println(String.format("  removeFront {%d}: %d", testValues[0], source.removeFront()));
	System.out.println(LINE);
	System.out.println("Contents: " + valuesToString(source));
	System.out.println(String.format("  remove %d {null}: %s", badValue, source.remove(badValue)));
	System.out.println(String.format("  remove %d {%d}: %d", testValues[length - 1], testValues[length - 1],
		source.remove(testValues[length - 1])));
	System.out.println("Contents: " + valuesToString(source));
	System.out.println(LINE);
	source = new SingleList<>();
	fillSingleList(source, testValues);
	System.out.println("Contents: " + valuesToString(source));
	source.reverse();
	System.out.println(String.format("  reverse {%s}: %s", Arrays.toString(reverseValues), valuesToString(source)));
	System.out.println(LINE);
	source = new SingleList<>();
	fillSingleList(source, testValues);
	System.out.println("Contents: " + valuesToString(source));
	source.split(left, right);
	System.out.println(String.format("  split {%s, %s}: %s, %s",
		Arrays.toString(Arrays.copyOfRange(testValues, 0, length / 2 + 1)),
		Arrays.toString(Arrays.copyOfRange(testValues, length / 2 + 1, length)), valuesToString(left),
		valuesToString(right)));
	System.out.println(LINE);
	source = new SingleList<>();
	left = new SingleList<>();
	right = new SingleList<>();
	fillSingleList(source, testValues);
	System.out.println("Contents: " + valuesToString(source));
	source.splitAlternate(left, right);
	Integer[] leftAlt = new Integer[length / 2 + 1];
	Integer[] rightAlt = new Integer[length - (length / 2 + 1)];
	i = 0;

	for (int j = 0; j < length / 2; j++) {
	    leftAlt[j] = testValues[i++];
	    rightAlt[j] = testValues[i++];
	}
	leftAlt[length / 2] = testValues[i];
	System.out.println(String.format("  splitAlternate {%s, %s}: %s, %s", Arrays.toString(leftAlt),
		Arrays.toString(rightAlt), valuesToString(left), valuesToString(right)));
	System.out.println(LINE);
	System.out.println(String.format("Contents: %s, %s", valuesToString(left), valuesToString(right)));
	source.combine(left, right);
	System.out.println(String.format("  combine {%s}: %s", valuesToString(source), valuesToString(source)));
	System.out.println(LINE);
	source = new SingleList<>();
	target = new SingleList<>();
	System.out.println(String.format("Contents: %s, %s", valuesToString(source), valuesToString(target)));
	System.out.println(String.format("  identical {%b}: %b", true, source.identical(target)));
	fillSingleList(source, testValues);
	fillSingleList(target, testValues);
	System.out.println(String.format("Contents: %s, %s", valuesToString(source), valuesToString(target)));
	System.out.println(String.format("  identical {%b}: %b", true, source.identical(target)));
	source = new SingleList<>();
	target = new SingleList<>();
	fillSingleList(source, testValues);
	fillSingleList(target, sortedValues);
	System.out.println(String.format("Contents: %s, %s", valuesToString(source), valuesToString(target)));
	System.out.println(String.format("  identical {%b}: %b", false, source.identical(target)));
	System.out.println(LINE);
	shuffleArray(uniqueValues1);
	shuffleArray(uniqueValues2);
	left = new SingleList<>();
	right = new SingleList<>();
	target = new SingleList<>();
	fillSingleList(left, uniqueValues1);
	fillSingleList(right, uniqueValues2);
	target.intersection(left, right);
	System.out.println(
		String.format("Contents: %s, %s", Arrays.toString(uniqueValues1), Arrays.toString(uniqueValues2)));
	System.out.println(
		String.format("  intersection {%s}: %s", Arrays.toString(uniqueValues1), valuesToString(target)));
	right = new SingleList<>();
	target = new SingleList<>();
	fillSingleList(left, uniqueValues1);
	fillSingleList(right, new Integer[] { badValue });
	target.intersection(left, right);
	System.out.println(String.format("Contents: %s, [%d]", Arrays.toString(uniqueValues1), badValue));
	System.out.println(String.format("  intersection {[]}: %s", valuesToString(target)));
	System.out.println(LINE);
	Integer[] leftUnique = Arrays.copyOfRange(uniqueValues1, 0, length / 2);
	Integer[] rightUnique = Arrays.copyOfRange(uniqueValues1, length / 2, length);
	left = new SingleList<>();
	right = new SingleList<>();
	target = new SingleList<>();
	fillSingleList(left, leftUnique);
	fillSingleList(right, rightUnique);
	target.union(left, right);
	System.out
	.println(String.format("Contents: %s, %s", Arrays.toString(leftUnique), Arrays.toString(rightUnique)));
	System.out.println(String.format("  union {%s}: %s", Arrays.toString(uniqueValues1), valuesToString(target)));
    }

    /**
     * Test SinglePriorityQueue.
     */
    private static void testSinglePriorityQueue() {
	System.out.println(TEST_LINE);
	System.out.println("Testing SinglePriorityQueue");
	Integer[] testValues = getValues();
	Integer[] sortedValues = Arrays.copyOf(testValues, testValues.length);
	Arrays.sort(sortedValues);
	System.out.println(LINE);
	System.out.println("SinglePriorityQueue<Integer> source = new SinglePriorityQueue<>();");
	final SinglePriorityQueue<Integer> source = new SinglePriorityQueue<>();
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println(LINE);
	System.out.println("Insert values: " + Arrays.toString(testValues));
	for (Integer value : testValues) {
	    System.out.println("  insert: " + value);
	    source.insert(value);
	}
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[0], source.peek()));
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	Integer key = sortedValues[sortedValues.length / 2];
	System.out.println("source.splitByKey(" + key + ", left, right)");
	final SinglePriorityQueue<Integer> left = new SinglePriorityQueue<>();
	final SinglePriorityQueue<Integer> right = new SinglePriorityQueue<>();
	source.splitByKey(key, left, right);
	System.out.println("source");
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println("left");
	System.out.println("  isEmpty {false}: " + left.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", sortedValues[0], left.peek()));
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {false}: " + right.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", key, right.peek()));
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("target.combine(left, right)");
	final SinglePriorityQueue<Integer> target = new SinglePriorityQueue<>();
	target.combine(left, right);
	System.out.println("target");
	System.out.println("  isEmpty {false}: " + target.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", sortedValues[0], target.peek()));
	System.out.println("  Contents: " + valuesToString(target));
	System.out.println("left");
	System.out.println("  isEmpty {true}: " + left.isEmpty());
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {true}: " + right.isEmpty());
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("Clear target");
	int i = 0;

	while (!target.isEmpty()) {
	    System.out.println("  remove {" + sortedValues[i] + "}: " + target.remove());
	    i++;
	}
	System.out.println();
    }

    /**
     * Test SingleQueue.
     */
    private static void testSingleQueue() {
	System.out.println(TEST_LINE);
	System.out.println("Testing SingleQueue");
	Integer[] testValues = getValues();
	System.out.println(LINE);
	System.out.println("SingleQueue<Integer> source = new SingleQueue<>();");
	final SingleQueue<Integer> source = new SingleQueue<>();
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println(LINE);
	System.out.println("Insert values: " + Arrays.toString(testValues));
	for (Integer value : testValues) {
	    System.out.println("  insert: " + value);
	    source.insert(value);
	}
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[0], source.peek()));
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	System.out.println("source.splitAlternate(left, right)");
	final SingleQueue<Integer> left = new SingleQueue<>();
	final SingleQueue<Integer> right = new SingleQueue<>();
	source.splitAlternate(left, right);
	System.out.println("source");
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println("left");
	System.out.println("  isEmpty {false}: " + left.isEmpty());
	System.out.println("  peek {" + testValues[0] + "}: " + left.peek());
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {false}: " + right.isEmpty());
	System.out.println("  peek {" + testValues[1] + "}: " + right.peek());
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("target.combine(left, right)");
	final SingleQueue<Integer> target = new SingleQueue<>();
	target.combine(left, right);
	System.out.println("target");
	System.out.println("  isEmpty {false}: " + target.isEmpty());
	System.out.println("  peek {" + testValues[0] + "}: " + target.peek());
	System.out.println("  Contents: " + valuesToString(target));
	System.out.println("left");
	System.out.println("  isEmpty {true}: " + left.isEmpty());
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {true}: " + right.isEmpty());
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("Clear target");
	int i = 0;

	while (!target.isEmpty()) {
	    System.out.println("  remove {" + testValues[i] + "}: " + target.remove());
	    i++;
	}
	System.out.println();
    }

    /**
     * Test SingleStack.
     */
    private static void testSingleStack() {
	System.out.println(TEST_LINE);
	System.out.println("Testing SingleStack");
	Integer[] testValues = getValues();
	System.out.println(LINE);
	System.out.println("SingleStack<Integer> source = new SingleStack<>();");
	final SingleStack<Integer> source = new SingleStack<>();
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println(LINE);
	System.out.println("Push values: " + Arrays.toString(testValues));
	for (Integer value : testValues) {
	    System.out.println("  push: " + value);
	    source.push(value);
	}
	System.out.println("  isEmpty {false}: " + source.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[testValues.length - 1], source.peek()));
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println(LINE);
	System.out.println("source.splitAlternate(left, right)");
	final SingleStack<Integer> left = new SingleStack<>();
	final SingleStack<Integer> right = new SingleStack<>();
	source.splitAlternate(left, right);
	System.out.println("source");
	System.out.println("  isEmpty {true}: " + source.isEmpty());
	System.out.println("  Contents: " + valuesToString(source));
	System.out.println("left");
	System.out.println("  isEmpty {false}: " + left.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[0], left.peek()));
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {false}: " + right.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[1], right.peek()));
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("target.combine(left, right)");
	final SingleStack<Integer> target = new SingleStack<>();
	target.combine(left, right);
	System.out.println("target");
	System.out.println("  isEmpty {false}: " + target.isEmpty());
	System.out.println(String.format("  peek {%d}: %d", testValues[testValues.length - 1], target.peek()));
	System.out.println("  Contents: " + valuesToString(target));
	System.out.println("left");
	System.out.println("  isEmpty {true}: " + left.isEmpty());
	System.out.println("  Contents: " + valuesToString(left));
	System.out.println("right");
	System.out.println("  isEmpty {true}: " + right.isEmpty());
	System.out.println("  Contents: " + valuesToString(right));
	System.out.println(LINE);
	System.out.println("Clear target");
	int i = testValues.length - 1;

	while (!target.isEmpty()) {
	    System.out.println("  Pop {" + testValues[i] + "}: " + target.pop());
	    i--;
	}
	System.out.println();
    }

    /**
     * Returns a comma-delimited string of the values in a data structure from front
     * to rear.
     *
     * @param dataStructure The object containing the values.
     * @return a string with the values of dataStructure listed from front to rear.
     */
    private static String valuesToString(SingleLink<Integer> dataStructure) {
	List<Integer> actual = new ArrayList<Integer>();

	for (Integer value : dataStructure) {
	    actual.add(value);
	}
	return actual.toString();
    }

    /**
     * Note that not all the data structure methods are called in this main. The
     * main method is just an illustration of how you may test your code. Test your
     * code thoroughly.
     *
     * When you start, comment out all code in the main, and un-comment as you add
     * code to the class.
     *
     * @param args (unused)
     */
    public static void main(final String[] args) {
	System.out.println("SingleLink Data Structures Tests");
	System.out.println();
	System.out.println("Tests are of the form:");
	System.out.println("  Test Operation {expected value}: actual value");
	System.out.println("  Contents: [contents from front to rear]");
	System.out.println();

	testSingleStack();
	testSingleQueue();
	testSinglePriorityQueue();
	testSingleList();
    }
}