package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-07-05
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

	/**
	 * Auxiliary method for {@code valid}. May force node rotation if the retrieval
	 * count of the located node value is incremented.
	 *
	 * @param node The node to examine for key.
	 * @param key  The value to search for. Count is updated to count in matching
	 *             node value if key is found.
	 * @return the updated node.
	 */
	private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedValue<T> key) {

		if (node == null) {
			this.comparisons++;
		} else {
			int compare = node.getValue().compareTo(key);
			this.comparisons++;
			
			if (compare == 0) {
				node.getValue().incrementCount();
				key.setCount(node.getValue().getCount());
			} else if (compare > 0) { // left
				node.setLeft(this.retrieveAux(node.getLeft(), key));
				if (node.getLeft() != null && node.getValue().getCount() < node.getLeft().getValue().getCount()) {
					node = this.rotateRight(node);
				}
			} else if (compare < 0) { // right
				node.setRight(this.retrieveAux(node.getRight(), key));
				if (node.getRight() != null && node.getValue().getCount() < node.getRight().getValue().getCount()) {
					node = this.rotateLeft(node);
				}
			}
		}

		return node;
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

		final TreeNode<T> node = parent.getRight();
    	parent.setRight(node.getLeft());
    	node.setLeft(parent);
    	
    	parent.updateHeight();
    	node.updateHeight();
    	
    	return node;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param parent The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> parent) {

		final TreeNode<T> node = parent.getLeft();
    	parent.setLeft(node.getRight());
    	node.setRight(parent);
    	
    	parent.updateHeight();
    	node.updateHeight();
    	
    	return node;
    	
	}

	/**
	 * Replaces BST {@code insertAux} - does not increment count on repeated
	 * insertion. Counts are incremented only on retrieve.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> data) {

    	if (node == null) { // first node
    		node = new TreeNode<>(data);
    		this.size++;
    	} else {
    		int compare = node.getValue().compareTo(data);
    		if (compare > 0) { // insert left
    			node.setLeft(this.insertAux(node.getLeft(), data));
    		} else if (compare < 0) { // insert right
    			node.setRight(this.insertAux(node.getRight(), data));
    		}
    	}
    	
    	node.updateHeight();
    	
    	return node;
    	
	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An Popularity Tree must meet the BST validation conditions,
	 * and additionally the counts of any node data must be greater than or equal to
	 * the counts of its children.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {

		boolean isValid = false;
    	
    	if (node != null && (node.getLeft() != null && node.getRight() != null)) {
    		if (Math.abs(node.getLeft().getHeight() - node.getRight().getHeight()) > 1) {
    			isValid = false;
    		} else if (node.getLeft().getValue().getCount() > node.getValue().getCount() || node.getRight().getValue().getCount() > node.getValue().getCount()) {
    			isValid = false;
    		} else if (node.getLeft() != null && node.getValue().compareTo(node.getLeft().getValue()) < 0) {
    			isValid = false;
    		} else if (node.getRight() != null && node.getValue().compareTo(node.getRight().getValue()) < 0) {
    			isValid = false;
    		} else {
    			isValid = isValidAux(node.getLeft()) && isValidAux(node.getRight());
    		}
    	}
    	
    	return isValid;
    	
	}

	/**
	 * Very similar to the BST retrieve, but increments the character count here
	 * instead of in the insertion.
	 *
	 * @param key The key to search for.
	 */
	@Override
	public CountedValue<T> retrieve(CountedValue<T> key) {

		this.root = this.retrieveAux(this.root, key);
		
    	if (key.getCount() != 0) {
    		key = new CountedValue<>(key);
    	} else {
    		key = null;
    	}
    	
    	return key;
    	
	}

	/**
	 * Determines whether two PopularityTrees are identical.
	 *
	 * @param target The PopularityTree to compare this PopularityTree against.
	 * @return true if this PopularityTree and target contain nodes that match in
	 *         position, value, count, and height, false otherwise.
	 */
	public boolean equals(final PopularityTree<T> target) {
		return super.equals(target);
	}

}
