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
	// Sun May 19 21:39:22 2019
	public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;   
        }
        
        int i = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        
        while (curr != null) {
            curr = curr.next;
            i++;
            if (i % k == 0) {
                prev = reverseInBetween(prev, curr);
                curr = prev.next;
            }
        }
        
        return dummy.next;
    }
    
    // exclusively
    public ListNode reverseInBetween(ListNode head, ListNode tail) {
        ListNode prev = head;
        ListNode curr = head.next;
        
        ListNode reversedPartLastNode = head.next;
        
        while (curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // 把reversed的部分 连接excluded的头尾
        head.next = prev; // prev: reversed的头
        reversedPartLastNode.next = tail; // reversedPartLastNode: reversed的尾
        
        return reversedPartLastNode;
    }

    // Thu Mar 28 00:48:27 2019
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        int i = 0;

        while (curr != null) {
            curr = curr.next;
            i++;
            
            if (i % k == 0) {
                prev = reverse(prev, curr);
                curr = prev.next;
            }
        }
        
        return dummy.next;
    }
    
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode lastNodeOfReversed = a.next;
        ListNode prev = a;
        ListNode curr = a.next;
        
        while (curr != b) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        a.next = prev;
        lastNodeOfReversed.next = curr;
        
        return lastNodeOfReversed;
    }

    // long ago
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode curr = head;
        int i = 0;

        while (curr != null) {
            curr = curr.next;
            i++;
            if (i % k == 0) {
                prev = reverseInBetween(prev, curr);
                curr = prev.next;
            }
        }
        
        return dummy.next;
    }

    // return the reversed list's last node, which is the preceding node before parameter end
    // reverse a->b->c, reverseInBetween(dummy, d)
    // a->b->c->d->e
    // c->b->a->d->e
    // return a
    public ListNode reverseInBetween(ListNode start, ListNode end) {
        ListNode lastOfReversed = start.next;
        ListNode prev = start;
        ListNode curr = start.next;
        
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // prev is now the reversed list head
        // 把start指向prev
        start.next = prev;
        lastOfReversed.next = curr;

        return lastOfReversed;
    }
}