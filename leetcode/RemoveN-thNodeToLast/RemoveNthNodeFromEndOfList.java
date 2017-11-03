/*
Given a linked list, remove the nth node from the end of list and return its head.
For example,
Given linked list: 1->2->3->4->5, and n = 2. 
After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.

idea:
draw a picture to help understand

should locate the nth node from end of list, remove it
how? maintain two pointers, the distance between them is n
so when minor one reaches end of list, it means major reaches the Nth Node From End of List,
remove it, and return head
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class RemoveNthNodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {	
		if (n == 0 || head == null) {
			return head;
		}
		
		ListNode slow = head;
		ListNode fast = head;
		int count = 0;
		while (count < n) {
			fast = fast.next;
			count++;
		}
		// if (fast == null) means remove original head, so return head.next;
		if (fast == null) {
			return head.next;
		}
		
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		slow.next = slow.next.next;
		
		return head;
	}
}