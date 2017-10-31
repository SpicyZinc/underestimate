/*
Given a list, 
rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

idea:
I only can say it is similar to find nth to last node
http://www.lifeincode.net/programming/leetcode-rotate-list-java/
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
            if (fast == null) {
                fast = head;
            }
        }
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