/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

idea:
create a dummy node
loop through two lists
it easy to point to next by pointer

when it is done,
check if either of the two could not be walked to the end
then finish walking to the end
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class MergeTwoSortedLists {
    // self written recently, passed oj
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        
        ListNode merged = new ListNode(0);
        ListNode current = merged;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = new ListNode(l1.val);
                current = current.next;
                l1 = l1.next;
            }
            else if (l1.val > l2.val) {
                current.next = new ListNode(l2.val);
                current = current.next;
                l2 = l2.next;
            }
            else {
                current.next = new ListNode(l1.val);
                l1 = l1.next;
                current = current.next;
                current.next = new ListNode(l2.val);
                l2 = l2.next;
                current = current.next;
            }
        }
        while (l1 != null) {
            current.next = l1;
            l1 = l1.next;
            current = current.next;
        }
        while (l2 != null) {
            current.next = l2;
            l2 = l2.next;
            current = current.next;
        }

        return merged.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
                current = current.next;
            }
            else {
                current.next = l2;
                l2 = l2.next;
                current = current.next;
            }
        }
        
        while (l1 != null) {
            current.next = l1;
            l1 = l1.next;
            current = current.next;
        }
        
        while (l2 != null) {
            current.next = l2;
            l2 = l2.next;
            current = current.next;
        }
            
        return dummy.next;        
    }
}