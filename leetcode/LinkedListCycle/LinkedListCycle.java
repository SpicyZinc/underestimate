/*
Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space?

idea:
make use of Floyd's cycle-finding algorithm, also know as tortoise and hare algorithm.

The idea is to have two references to the list 
and move them at different speeds. Move one forward by 1 node and the other by 2 nodes.

If the linked list has a loop they will definitely meet.
Else either of the two references(or their next) will become null.
*/


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        // first fast != null is for fast.next
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
    // fast walks 3 steps while slow walks 1 step, works
   	public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        // first fast != null is for fast.next
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next.next;
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
}