/*
Given a list, 
rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

idea:
similar to find nth node to tail of a linked list
how? maintain two pointers, the distance between them is n
so when fast reaches the end, the slow should point to the code which is nth to the end.

note: rotateRight by k, rotate right part of list by k places
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if (n == 0 || head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0) {
            n--;
            fast = fast.next;
            // if fast == null while n still big enough
            // do this like do n % length
            if (fast == null) {
                fast = head;
            }
        }
        // rotate will not change the list
        if (fast == null || slow == fast) {
            return head;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }
}