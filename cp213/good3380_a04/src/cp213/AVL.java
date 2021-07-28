package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author your name here
 * @author David Brown
 * @version 2021-07-05
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance value of node. If greater than 1, then left heavy, if
	 * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
	 * is balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {

		return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());

	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {

		TreeNode<T> n = node.getRight();
		node.setRight(n.getLeft());
		n.setLeft(node);
		node.updateHeight();

		return n;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {

		TreeNode<T> n = node.getLeft();
		node.setLeft(n.getRight());
		n.setRight(node);
		node.updateHeight();

		return n;
	}

	/**
	 * Auxiliary method for {@code insert}. Inserts data into this AVL.
	 *
	 * @param node the current node (TreeNode)
	 * @param data Data to be inserted into the node
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> data) {

		int balance = 0;
		if (node == null) {
			node = new TreeNode<>(data);
			this.size++;
		} else {
			int c = node.getValue().compareTo(data);

			if (c > 0) {
				node.setLeft(this.insertAux(node.getLeft(), data));
				node.updateHeight();
				balance = this.balance(node);

				if (balance > 1 && this.balance(node.getLeft()) >= 0) { // left left
					node = this.rotateRight(node);
				} else if (balance > 1 && this.balance(node.getLeft()) < 0) { // left right
					node.setLeft(this.rotateLeft(node.getLeft()));
					node = this.rotateRight(node);
				}
			} else if (c < 0) {
				node.setRight(this.insertAux(node.getRight(), data));
				node.updateHeight();
				balance = this.balance(node);

				if (balance < -1 && this.balance(node.getRight()) <= 0) { // right right
					node = this.rotateLeft(node);
				} else if (balance < -1 && this.balance(node.getRight()) > 0) { // right left
					node.setRight(this.rotateRight(node.getRight()));
					node = this.rotateLeft(node);
				} else {
					this.size++;
				}
			}
		}

		return node;

	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An AVL must meet the BST validation conditions, and
	 * additionally be balanced in all its subtrees - i.e. the difference in height
	 * between any two children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node) {

		boolean valid = false;
		if (node == null) {
			valid = true;
		} else if ((this.nodeHeight(node.getLeft()) >= this.nodeHeight(node.getRight())
				? this.nodeHeight(node.getLeft())
				: this.nodeHeight(node.getRight())) != node.getHeight() - 1) {
			valid = false;
		} else if (node.getLeft() != null && node.getLeft().getValue().compareTo(node.getValue()) >= 0
				|| node.getRight() != null && node.getRight().getValue().compareTo(node.getValue()) <= 0) {
			valid = false;
		} else {
			valid = this.isValidAux(node.getLeft()) && this.isValidAux(node.getRight());
		}

		return valid;
	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
