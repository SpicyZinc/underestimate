/*
Convert a BST to a sorted circular doubly-linked list in-place.
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:
https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png
 
We want to transform this BST into a circular doubly linked list.
Each node in a doubly linked list has a predecessor and successor.
For a circular doubly linked list,
the predecessor of the first element is the last element,
and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above.
https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png
The "head" symbol means the node it points to is the smallest element of the linked list.
 
Specifically, we want to do the transformation in place.
After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor.
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST.
https://assets.leetcode.com/uploads/2018/10/12/bstdllreturnbst.png
The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

idea:
https://www.cnblogs.com/grandyang/p/9615871.html

inorder to make sure sorted
dfs for tree
note, this is circular, bst and DLL share the same node structure

head and prev need to be global


先对 root 和 prev 做连接
先保存要访问的下一个结点
然后对 root 和 head 做连接
最后更新prev = root

*/

// Definition for a Node.
class Node {
	public int val;
	public Node left;
	public Node right;

	public Node() {}

	public Node(int _val,Node _left,Node _right) {
		val = _val;
		left = _left;
		right = _right;
	}
};

class ConvertBinarySearchTreeToSortedDoublyLinkedList {
	// method 1
	Node head = null;
	Node prev = null;

	public Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}

		treeToDoublyList(root.left);

		root.left = prev;
		if (prev != null) {
			prev.right = root;
		} else {
			head = root;
		}

		Node right = root.right;
		root.right = head;
		head.left = root;
		prev = root;

		treeToDoublyList(right);

		return head;
	}

	// method 2
	Node head = null;
	Node prev = null;

	public Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}

		inorderDFS(root);

		prev.right = head;
		head.left = prev;

		return head;
	}

	public void inorderDFS(Node node) {
		if (node == null) {
			return;
		}

		inorderDFS(node.left);

		if (head == null) {
			head = node;
			prev = node;
		} else {
			prev.right = node;
			node.left = prev;
			prev = node;
		}

		inorderDFS(node.right);
	}
}