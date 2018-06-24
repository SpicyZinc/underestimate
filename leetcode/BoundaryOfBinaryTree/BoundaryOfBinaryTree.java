/*
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
Left boundary is defined as the path from root to the left-most node.
Right boundary is defined as the path from root to the right-most node.
If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
Note this definition only applies to the input binary tree, and not applies to any subtrees.
The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
If not, travel to the right subtree. Repeat until you reach a leaf node.
The right-most node is also defined by the same way with left and right exchanged.

Example 1
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].

Example 2
Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

idea:
push root to result at the condition one child of root is not null,
if all children are null, no push, handled by leavesBoundary()
leftBoundary() node == null || node.left && node.right == null return
leavesBoundary() node == null return, others add
rightBoundary() note, result.add(node.val) at last, when to return

*/
import java.util.*;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
}

class BoundaryOfBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(4);


		TreeNode anotherRoot = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		
		left.left = new TreeNode(4);
		left.right = new TreeNode(5);
		left.right.left = new TreeNode(7);
		left.right.right = new TreeNode(8);

		right.left = new TreeNode(6);
		right.left.left = new TreeNode(9);
		right.left.right = new TreeNode(10);

		anotherRoot.left = left;
		anotherRoot.right = right;

		BoundaryOfBinaryTree eg = new BoundaryOfBinaryTree();
		List<Integer> boundary = eg.boundaryOfBinaryTree(root);
		System.out.println(boundary.toString());

		List<Integer> anotherboundary = eg.boundaryOfBinaryTree(anotherRoot);
		System.out.println(anotherboundary.toString());
	}

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		// add root to result
		if (root.left != null || root.right != null) {
			result.add(root.val);
		}
		// left boundary
		leftBoundary(root.left, result);
		// leaves boundary
		leavesBoundary(root, result);
		// right boundary
		rightBoundary(root.right, result);

		return result;
	}

	public void leftBoundary(TreeNode node, List<Integer> result) {
		if (node == null || node.left == null && node.right == null) {
			return;
		}
		result.add(node.val);
		if (node.left == null) {
			leftBoundary(node.right, result);
		} else {
			leftBoundary(node.left, result);
		}
	}

	public void rightBoundary(TreeNode node, List<Integer> result) {
		if (node == null || node.left == null && node.right == null) {
			return;
		}
		if (node.right == null) {
			rightBoundary(node.left, result);
		} else {
			rightBoundary(node.right, result);
		}
		result.add(node.val);
	}

	public void leavesBoundary(TreeNode node, List<Integer> result) {
		if (node == null) {
			return;
		}
		// handle only one root node, and regular leaves
		if (node.left == null && node.right == null) {
			result.add(node.val);
		}
		leavesBoundary(node.left, result);
		leavesBoundary(node.right, result);
	}
}