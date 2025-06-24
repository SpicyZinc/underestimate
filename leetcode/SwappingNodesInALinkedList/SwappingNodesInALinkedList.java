/*
You are given the head of a linked list, and an integer k.
Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]

Example 2:
Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]

Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 10^5
0 <= Node.val <= 100

idea:
swap 不用连接node 直接swap value
一个loop 就找到 front...k...k...end
注意什么时候 size++ and current.next
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode frontKth = null;
        ListNode endKth = null;

        int size = 0;
        ListNode current = head;
        while (current != null) {
            size++;

            if (endKth != null) {
                endKth = endKth.next;
            }
            if (size == k) {
                frontKth = current;
                endKth = head;
            }

            current = current.next;
        }

        int temp = frontKth.val;
        frontKth.val = endKth.val;
        endKth.val = temp;

        return head;
    }
}
