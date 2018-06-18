/*
Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?

idea:
1. Iteratively
2. Recursively
*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // reverse ListNode head.next
        ListNode reversedRemaining = reverseList(head.next);
        // now reversedRemaining points to reversed head
        // cut off (head.next).next by letting .next point to head (now tail);
        head.next.next = head;
        head.next = null;
        
        return reversedRemaining;
    }

    public ListNode reverseListRecursively(ListNode head) {
        if ( head == null || head.next == null ) {
            return head;
        }

        ListNode second = head.next;
        head.next = null;
        ListNode remaining = reverseListRecursively(second);
        // join the two lists
        second.next = head;

        return remaining;
    }
}