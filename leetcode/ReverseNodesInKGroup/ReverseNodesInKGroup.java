/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5

idea:
http://rleetcode.blogspot.com/2014/01/reverse-nodes-in-k-group-java.html
note: reverse(ListNode prev, ListNode next) prev and next are EXCLUSIVELY

0->1->2->3->4->5->6
|           |
prev        next

after call prev = reverse(prev, next)
0->3->2->1->4->5->6
         |  |
         prev next
e.g. not same as run case
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        int i = 0;
        ListNode prev = dummy;
        while (head != null) {
            head = head.next;
            i++;
            if (i % k == 0) {
                prev = reverseInBetween(prev, head);
                head = prev.next;
            }
        }
        
        return dummy.next;
    }
    // return the reversed list's last node, which is the preceding node before parameter end
    // reverse a->b->c, reverseInBetween(dummy, d)
    // a->b->c->d->e
    // c->b->a->d->3
    // return a
    public ListNode reverseInBetween(ListNode start, ListNode end) {
        ListNode last = start.next;
        ListNode prev = start;
        ListNode curr = prev.next;
        
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // prev is now the reversed list head
        start.next = prev;
        last.next = curr;
        
        return last;
    }
}