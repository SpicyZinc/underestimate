/*
Convert Sorted List to Binary Search Tree

idea:
1. convert the list to an array, then use Convert Sorted Array to Binary Search Tree
2. find mid, pass in length as parameter in recursion function
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; next = null; }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class ConvertSortedListToBST  {
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		
		if (head.next == null) {
			return new TreeNode(head.val);
		}
		
		ListNode curr = head;
		int size = 0;
		while (curr != null) {
			size++;
			curr = curr.next;
		}

		return sortedListToBST(head, size);
	}

	// 就按照 奇数 个 元素 来 考虑
	public TreeNode sortedListToBST(ListNode head, int size) {
		if (size <= 0) {
			return null;
		}

		int passMiddleByOne = (size + 1) / 2;
		int leftLastPos = passMiddleByOne - 1;

		ListNode curr = head;
		// use leftLastPos to reach last of left part
		while (leftLastPos > 0) {
			leftLastPos--;
			curr = curr.next;
		}

		ListNode middle = curr;
		ListNode right = curr.next;
		// 切断 list
		curr.next = null;
		
		TreeNode root = new TreeNode(middle.val);
		root.left = sortedListToBST(head, passMiddleByOne - 1);
		root.right = sortedListToBST(right, size - passMiddleByOne);

		return root;
	}
}