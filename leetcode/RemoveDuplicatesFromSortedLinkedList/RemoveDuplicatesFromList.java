/*
Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3. 

idea:
delete 2, 3 work
return the changed head

current.next != null finally current stops at the last element of the list
prev, curr = head, two pointers
1. connect to next unique element
2. update the prev pointer

*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    // append
    public void append(int x) {
        ListNode curr = this;
        while(curr.next != null) {          
            curr = curr.next;
        }
        curr.next = new ListNode(x);        
    }
    // print
    public void print() {
        ListNode curr = this;
        while (curr != null) {
            System.out.print(curr.val + "  ");
            curr = curr.next;
        }
    }
}

public class RemoveDuplicatesFromList {
    public static void main(String[] args) {
        // 1->1->2->3->3
        ListNode aListNode = new ListNode(1);
        /*
        aListNode.append(1);
        aListNode.append(2);
        aListNode.append(3);
        aListNode.append(3);
        */
        aListNode.next = new ListNode(1);
        aListNode.next.next = new ListNode(2);
        aListNode.next.next.next = new ListNode(3);
        aListNode.next.next.next.next = new ListNode(3);
        
        aListNode.print();
        System.out.print("\n");
        RemoveDuplicatesFromList eg = new RemoveDuplicatesFromList();
        ListNode result = eg.deleteDuplicates(aListNode);
        result.print();
    }
    // best and clearest solution
    // Tue Feb 28 23:50:01 2023
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            if (current.val == next.val) {
                current.next = next.next;
            } else {
                current = next;
            }
        }
        
        return head;
    }

    // delete 2
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        ListNode prev = head;
        while (cur != null) {
            while (cur != null && prev.val == cur.val) {
                cur = cur.next;
            }
            // first connect to the next unique element
            prev.next = cur;
            // then update the pre, otherwise cannot compare
            prev = cur;
            if (cur != null) {
                cur = cur.next;
            }
        }
 
        return head;    
    }
}