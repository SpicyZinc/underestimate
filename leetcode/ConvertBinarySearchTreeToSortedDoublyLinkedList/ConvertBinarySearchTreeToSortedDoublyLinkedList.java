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

inorder traversal to make sure sorted
dfs for tree
note, this is circular, BST and DLL share the same node structure
left == .prev
right == .next

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
	// Fri Jul  5 01:42:10 2019
	// iterative
	public Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}

		Node head = null;
		Node tail = null;

		Node node = root;

		Stack<Node> stack = new Stack<>();
		// note, not empty
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}

			// the most left node which is the smallest node
			// since this is BST
			node = stack.pop();
			if (head == null) {
				head = node;
			}

			if (tail != null) {
				// tail.next = node;
				tail.right = node;
				// node.prev = tail;
				node.left = tail;
			}
			// tail points to current node 这是依次升序的
			tail = node;
			// 前进 node
			node = node.right;
		}

		head.left = tail;
		tail.right = head;

		return head;
	}

	// recursion
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
}