/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

idea:
1. iterative, dummy node pointing to first element
2. recursive, easy to understand
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode prev = helper;
     
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next; 
            }
            else {
                prev = prev.next;
            }
        }
     
        return helper.next;
    }


    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
        	return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}