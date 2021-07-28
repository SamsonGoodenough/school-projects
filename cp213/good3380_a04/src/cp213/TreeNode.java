package cp213;

import java.util.ArrayList;

/**
 * The individual node of a linked structure that stores CountedValue objects.
 * This is a doubly-linked node with left and right pointers to child nodes. The
 * node link can be updated, but not the node value, in order to avoid moving
 * values between nodes. Data structures must be reordered by moving nodes.
 *
 * @author David Brown
 * @version 2021-07-05
 */
public class TreeNode<T extends Comparable<T>> {

    // Attributes
    private CountedValue<T> value = null; // the nodevaluea
    private int height = 1; // the node height

    // Link to the child TreeNodes.
    private TreeNode<T> left = null; // pointer to the left child node
    private TreeNode<T> right = null; // pointer to the right child node

    /**
     * Creates a new TreeNode with a copy of value and null links to its child
     * TreeNodes.
     *
     * @param value The value to store in the node.
     */
    public TreeNode(final CountedValue<T> value) {
	this.value = value;
    }

    /**
     * Performs an inorder traversal of a tree copying node values to a temporary
     * queue.
     *
     * @param node  a TreeNode
     * @param queue temporary structure to hold nodes
     */
    private final void inOrderAux(final TreeNode<T> node, final ArrayList<CountedValue<T>> queue) {
	if (node != null) {
	    this.inOrderAux(node.getLeft(), queue);
	    queue.add(new CountedValue<T>(node.getValue()));
	    this.inOrderAux(node.getRight(), queue);
	}
	return;
    }

    /**
     * Returns the height of this TreeNode.
     *
     * @return this node height.
     */
    public int getHeight() {
	return this.height;
    }

    /**
     * Returns the left child of this TreeNode.
     *
     * @return this left child pointer.
     */
    public TreeNode<T> getLeft() {
	return this.left;
    }

    /**
     * Returns the right child of this TreeNode.
     *
     * @return this right child pointer.
     */
    public TreeNode<T> getRight() {
	return this.right;
    }

    /**
     * Returns this node value. Not copy safe as it returns a reference to the
     * value, not a copy of the value.
     *
     * @return this node value.
     */
    public CountedValue<T> getValue() {
	return this.value;
    }

    /**
     * Returns an array of copies of CountedValue objects in a linked data
     * structure. The array contents are in value order from smallest to largest.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If value elements are added or
     * removed by an external thread while the value is being copied to the array,
     * then the declared array size may no longer be valid.
     *
     * @return this tree value as an array of value.
     */
    public final ArrayList<CountedValue<T>> inOrder() {
	final ArrayList<CountedValue<T>> queue = new ArrayList<>();
	this.inOrderAux(this, queue);
	return queue;
    }

    /**
     * Returns an ArrayList of copies of the CountedValue objects in a linked data
     * structure. The ArrayList contents are in level order starting from the root
     * (this) node. Helps you to determine the structure of the tree.
     *
     * Not thread safe as it assumes contents of data structure are not changed by
     * an external thread during the copy loop. If value elements are added or
     * removed by an external thread while the value is being copied to the
     * ArrayList, then the declared array size may no longer be valid.
     *
     * @return this tree values as an ArrayList of values.
     */
    public final ArrayList<CountedValue<T>> levelOrder() {
	final ArrayList<CountedValue<T>> values = new ArrayList<>();
	TreeNode<T> node = this;

	if (this != null) {
	    // Put the nodes for one level into a queue.
	    final ArrayList<TreeNode<T>> queue = new ArrayList<>();
	    queue.add(node);

	    while (queue.size() > 0) {
		// Add the node to the queue
		node = queue.remove(0);
		// Add a copy of the node value to the list of value
		values.add(new CountedValue<T>(node.getValue()));

		if (node.getLeft() != null) {
		    queue.add(node.getLeft());
		}
		if (node.getRight() != null) {
		    queue.add(node.getRight());
		}
	    }
	}
	return values;
    }

    /**
     * Updates the left child reference of this TreeNode to another TreeNode.
     *
     * @param left this new left child node to link to.
     */
    public void setLeft(final TreeNode<T> left) {
	this.left = left;
    }

    /**
     * Updates the right child reference of this TreeNode to another TreeNode.
     *
     * @param right this new right child node to link to.
     */
    public void setRight(final TreeNode<T> right) {
	this.right = right;
    }

    /**
     * @return a string version of this node including the value and height.
     */
    @Override
    public String toString() {
	return "D: " + this.value + "; H: " + this.height;
    }

    /**
     * Updates the height of this TreeNode to 1 plus the maximum heights of its two
     * child nodes. Empty child nodes are considered to have a height of 0.
     */
    public void updateHeight() {
	int leftHeight = 0;
	int rightHeight = 0;

	if (this.left != null) {
	    leftHeight = this.left.height;
	}
	if (this.right != null) {
	    rightHeight = this.right.height;
	}
	this.height = Math.max(leftHeight, rightHeight) + 1;
	return;
    }

}
