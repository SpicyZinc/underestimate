/*
Remove Nth Node From End of List
Given a linked list, remove the nth node from the end of list and return its head.

For example,
Given linked list: 1->2->3->4->5, and n = 2.
After removing the 2nd node from the end, the linked list becomes 1->2->3->5.

idea:
find an example, and draw a picture to help understand

the whole idea is find the nth node from end of list, remove it

Keep two pointers, the distance between them is n
so when minor one reaches end of list, it means major reaches the Nth Node From End of List,
remove it, and return head

**************
one thing to note:
if (minor == null) means remove original head, so return head.next;
**************
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
		
        ListNode major = head;
		ListNode minor = head;
		int count = 0;
		while (count < n) {
			minor = minor.next;
			count++;
		}
		// key part, don't forget to check
		if (minor == null) {
			return head.next;
		}
		
		while (minor.next != null) {
			major = major.next;
			minor = minor.next;
		}

		major.next = major.next.next;
		
		return head;
    }
    // self written version passed test
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) {
			return head;
		}
        ListNode slow = head;
        ListNode fast = head;
        int cnt = 0;
        while (n > cnt) {
            fast = fast.next;
            cnt++;
        }
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