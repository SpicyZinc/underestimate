/*
Given a singly linked list, determine if it is a palindrome.
Could you do it in O(n) time and O(1) space?

idea:
1. O(n) time and O(n) space, Use a Stack
1) Traverse the given list from head to tail and push every visited node to stack.
2) Traverse the list again. For every visited node, pop a node from stack and compare data of popped node with currently visited node.
3) If all nodes matched, then return true, else false.

2. O(n) time and O(1) space, By reversing the list
This method takes O(n) time and O(1) extra space.
1) Get the middle of the linked list.
2) Reverse the second half of the linked list.
3) Check if the first half and second half are identical.
4) Construct the original linked list by reversing the second half again and attaching it back to the first half
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class PalindromeLinkedList {
    // method 1
    public boolean isPalindrome(ListNode head) {
        if ( head == null || head.next == null ) {
            return true;
        }

        Stack<ListNode> s = new Stack<ListNode>();
        ListNode current = head;
        while ( current != null ) {
            s.push(current);
            current = current.next;
        }

        current = head;
        while ( !s.isEmpty() ) {
            ListNode temp = s.pop();
            if ( temp.val != current.val ) {
                return false;
            }
            current = current.next;
        }

        return true;
    }
    // method 2
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode secondHalf = slow.next;
        slow.next = null;
        
        ListNode reversedHalf = reverse(secondHalf);
        ListNode curr = head;
        while (curr != null && reversedHalf != null) {
            if (curr.val != reversedHalf.val) {
                return false;
            }
            curr = curr.next;
            reversedHalf = reversedHalf.next;
        }
        return true;
    }
    
    public ListNode reverse(ListNode node) {
        if ( node == null || node.next == null ) {
            return node;
        }
        ListNode prev = null;
        ListNode curr = node;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}