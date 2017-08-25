/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
Note:
Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.

idea:
have to remember this reverse method which is reverse a list for certain length
https://github.com/rffffffff007/leetcode/blob/master/Reverse%20Linked%20List%20II.java
reverse(ListNode head, int length)
reverseInBetween(ListNode head, int start, int end)
go to start position, call reverse(ListNode start, end - start + 1)

*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    String printString() {
        ListNode curr = this;
        String res = "";
        while (curr != null) {
            res += curr.val + " ";
            curr = curr.next;
        }

        return res;
    }
}

public class ReverseLinkedListInBetween {
    public static void main(String[] args) {
        ReverseLinkedListInBetween eg = new ReverseLinkedListInBetween();
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);

        ListNode what = eg.reverseBetween(list, 3, 4);
        System.out.println(what.printString());
    }
    // self written version 
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode current = fake;
        int i = 1;
        while (current.next != null && i < m) {
            current = current.next;
            i++;
        }
        if (current.next == null) {
            return head;
        }   

        current.next = reverse(current.next, n - m + 1);

        return fake.next; 
    }
    // must memorize how to reverse limited n length
    private ListNode reverse(ListNode head, int n) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        int i = 0;
        while (i < n) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
        // till here, head is already tail, tail.next must point to null, and curr is null now
        head.next = curr;
        // prev is head now
        return prev;
    }
    
    // a typical iterative method to reverse list, has nothing to with this problem
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;

        return head;
    }
}