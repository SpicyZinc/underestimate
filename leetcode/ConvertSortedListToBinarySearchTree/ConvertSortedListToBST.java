/*
Convert Sorted List to Binary Search Tree 
idea:
1. convert the list to an array, then use Convert Sorted Array to Binary Search Tree
2. emulate Convert Sorted Array to Binary Search Tree
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
		ListNode tmp = head;
		int len = 0;
		while (tmp != null) {
			tmp = tmp.next;
			len++;
		}
		return sortedListToBST(head, len);
	}

	public TreeNode sortedListToBST(ListNode head, int len) {
		if (len <= 0) {
			return null;
		}
		int mid = (1 + len) / 2;
		int tmp = mid - 1;
		ListNode current = head;
		while (tmp > 0) {
			current = current.next;
			tmp--;
		}

		TreeNode root = new TreeNode(current.val);
		root.left = sortedListToBST(head, mid - 1);
		root.right = sortedListToBST(current.next, len - mid);

		return root;
	}
}