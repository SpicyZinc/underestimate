/*
Convert a binary search tree to doubly linked list with in-order traversal.

Example
Given a binary search tree:
    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

idea:
lintcode version, not circular
leetcode version, circular

know in order traversal of a tree
inorder is sorted
把left都弄完了后 才弄right 而且right又是从right.left开始
dfs need to revisit
*/

// Definition of TreeNode
class TreeNode {
	public int val;
	public TreeNode left, right;
	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}
// Definition for Doubly-ListNode
class DoublyListNode {
	int val;
	DoublyListNode next, prev;
	DoublyListNode(int val) {
		this.val = val;
		this.next = this.prev = null;
	}
}

public class ConvertBinarySearchTreeToDoublyLinkedList {
	/**
	 * @param root: The root of tree
	 * @return: the head of doubly list node
	 */
	public DoublyListNode bstToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}

		// Init stack
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;    
		stack.push(node);

		// Create DoublyListNode header
		DoublyListNode dummy = new DoublyListNode(0);
		DoublyListNode dllNode = dummy;

		while (!stack.isEmpty()) {
			while (node != null && node.left != null) {
				stack.push(node.left);
				node = node.left;
			}
			// add node to doubly linked list
			node = stack.pop();
			DoublyListNode currrent = new DoublyListNode(node.val);
			dllNode.next = currrent;
			currrent.prev = dllNode;
			dllNode = currrent;

			// check right node and add to stack
			node = node.right;
			if (node != null) {
				stack.push(node);
			}
		}

		return dummy.next;
	}

	/**
	 * @param root: The root of tree
	 * @return: the head of doubly list node
	 */
	public DoublyListNode bstToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}

		DoublyListNode left = null;
		DoublyListNode right = null;
		
		DoublyListNode node = new DoublyListNode(root.val);

		if (root.left != null) {
			left = bstToDoublyList(root.left);
		}
		node.prev = rightMost(left);
		if (node.prev != null) {
			node.prev.next = node;
		}

		if (root.right != null) {
			right = bstToDoublyList(root.right);
		}
		node.next = right;
		if (node.next != null) {
			node.next.prev = node;
		}

		// navigate to the left to return the heed
		return leftMost(node);
	}

	private DoublyListNode leftMost(DoublyListNode node) {
		if (node == null) {
			return null;
		}

		while (node.prev != null) {
			node = node.prev;
		}

		return node;
	}

	private DoublyListNode rightMost(DoublyListNode node) {
		if (node == null) {
			return null;
		}

		while (node.next != null) {
			node = node.next;
		}

		return node;
	}
}