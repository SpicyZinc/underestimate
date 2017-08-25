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
    public ListNode reverseList(ListNode head) {
        if ( head == null ) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        while ( cur.next != null ) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;

        return cur;
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