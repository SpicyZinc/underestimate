/*
Reverse a singly linked list.

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
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        ListNode next;
        
        while (curr != null && curr.next != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // curr points to end of the list, not set its next yet
        curr.next = prev;

        return curr;
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

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // reverse ListNode head.next
        ListNode reversedRemaining = reverse(head.next);
        // now reversedRemaining points to reversed head
        // cut off (head.next).next by letting .next point to head (now tail);
        head.next.next = head;
        head.next = null;
        
        return reversedRemaining;
    }
}