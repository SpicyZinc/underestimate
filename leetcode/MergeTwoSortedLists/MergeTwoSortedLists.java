/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

idea:
create a dummy node
loop through two lists
it easy to point to next by pointer

when it is done,
check if either of the two could not be walked to the end
then finish walking to the end
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class MergeTwoSortedLists {
	// Sun Apr 28 17:59:37 2019
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode head = dummy;
		
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head.next = new ListNode(l1.val);
				head = head.next;
				l1 = l1.next;
			} else if (l1.val > l2.val) {
				head.next = new ListNode(l2.val);
				head = head.next;
				l2 = l2.next;
			} else {
				head.next = new ListNode(l1.val);
				head = head.next;
				head.next = new ListNode(l2.val);
				head = head.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}

		if (l1 != null) {
			head.next = l1;
		}
		if (l2 != null) {
			head.next = l2;
		}

		return dummy.next;
	}

	// 07/28/2018
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
			
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curr.next = new ListNode(l1.val);
				curr = curr.next;
				l1 = l1.next;
			} else if (l1.val > l2.val) {
				curr.next = new ListNode(l2.val);
				curr = curr.next;
				l2 = l2.next;
			} else {
				curr.next = new ListNode(l1.val);
				curr = curr.next;
				l1 = l1.next;
				curr.next = new ListNode(l2.val);
				curr = curr.next;
				l2 = l2.next;
			}
		}
		
		if (l1 != null) {
			curr.next = l1;
		}
		
		if (l2 != null) {
			curr.next = l2;
		}
		
		return dummy.next;
	}

	// simpler method
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {        
		ListNode sorted = new ListNode(0);
		ListNode current = sorted;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				current.next = l1;
				l1 = l1.next;
			} else {
				current.next = l2;
				l2 = l2.next;
			}
			current = current.next;
		}
		
		if (l1 != null) {
			current.next = l1;
		}
		
		if (l2 != null) {
			current.next = l2;
		}
		
		return sorted.next;
	}
}