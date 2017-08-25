/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

idea:
http://www.cnblogs.com/hiddenfox/p/3408931.html
how to not use extra space

*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> hs = new HashSet<ListNode>();
        ListNode curr = head;
        while (curr != null) {
            if (hs.add(curr)) {
                curr = curr.next;
            }
            else {
                return curr;
            }
        }
        return null;
    }
    // without extra space
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (true) {
            if (fast == null || fast.next == null) {
                return null;    
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;  
            }
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    // self written recent
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return null;
        }
        ListNode curr = head;
        while (curr != slow) {
            curr = curr.next;
            slow = slow.next;
        }

        return slow;
    }
}