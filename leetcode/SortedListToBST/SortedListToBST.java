/**
Given a singly linked list where elements are sorted in ascending order, 
convert it to a height balanced BST.

idea:
find the middle point of linkedlist and set it as root of BST
then for left and right parts of linkedlist, 
keep finding the middle of left part and middle of right part, recursively
 
*/

// Definition of singly-linked list.
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x; 
		next = null; 
	}
	public void print() {
		ListNode cur = this;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}				
	}
}

// Definition of binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
	// a way to test if BST, because BST in order print is a sorted list
	// An in-order traversal of a binary search tree will always result in a sorted list of node items
	// so think it as left -- root -- right, must be a sorted list
	// 
	public void inOrder() {
		if (this != null) {
			if (this.left != null) {
				this.left.inOrder();
			}				
			System.out.print(this.val + " ");			
			if (this.right != null) {
				this.right.inOrder();
			}							
		}				
	}
}

public class SortedListToBST {

	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(5);
		head.print();
		System.out.print("\n=====================\n");
		SortedListToBST aTest = new SortedListToBST();
		TreeNode root = aTest.sortedListToBST(head);
		root.inOrder();
		
	}

    public TreeNode sortedListToBST(ListNode head) {
        int length = 0;
        ListNode cur = head;
		// get the length of linkedlist first
		// not like array, need to walk through the list to get the list length
		// 
        while (cur != null) {
            cur = cur.next;
            length++;
        }
		// System.out.println("The list length " + length);
        return sortedListToBST(head, length);
    }
    
    public TreeNode sortedListToBST(ListNode head, int length) {
        if (length == 0)
			return null;
        if (length == 1) 
			return new TreeNode(head.val);
     
        ListNode cur = head;
        int count = length / 2;
        while (count > 0) {
            cur = cur.next;
            count--;
        }
        TreeNode res = new TreeNode(cur.val);
		// all that has to be noticed is that
		// right and left parts together will length-1, because middle reduce the length by 1
		//
		res.left = sortedListToBST(head, length/2);
        res.right = sortedListToBST(cur.next, length - length/2 - 1);        
		
        return res;
    }
}