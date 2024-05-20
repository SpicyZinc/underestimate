/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5

idea:
typical merge sort with list
easy operation

1. fast and slow
cut two halves slow.next = null
2. merge two sorted linked list

Wed Feb  8 23:20:09 2023 tested by eBay
*/

ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class SortList {
    // Thu Apr 25 03:24:04 2024
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 找到中间点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode first = head;
        ListNode second = slow.next;
        // cut the list two halves
        slow.next = null;

        first = sortList(first);
        second = sortList(second);

        return merge(first, second);
    }
    // merge two sorted list
    public ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (first != null && second != null) {
            if (first.val < second.val) {
                current.next = first;
                first = first.next;
                current.next.next = null;
            } else {
                current.next = second;
                second = second.next;
                current.next.next = null;
            }

            current = current.next;
        }

        if (first != null) {
            current.next = first;
        } else {
            current.next = second;
        }

        return dummy.next;
    }


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 找到中间点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode first = head;
        ListNode second = slow.next;
        // cut the list two halves
        slow.next = null;

        first = sortList(first);
        second = sortList(second);

        return merge(first, second);
    }

    public ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (first != null && second != null) {
            if (first.val < second.val) {
                current.next = new ListNode(first.val);
                first = first.next;
            } else {
                current.next = new ListNode(second.val);
                second = second.next;
            }

            current = current.next;
        }

        if (first != null) {
            current.next = first;
        } else {
            current.next = second;
        }

        return dummy.next;
    }
}
