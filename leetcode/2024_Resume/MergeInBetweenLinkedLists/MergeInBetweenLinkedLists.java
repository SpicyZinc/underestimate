/*
You are given two linked lists: list1 and list2 of sizes n and m respectively.
Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
The blue edges and nodes in the following figure indicate the result:
Build the result list and return its head.

Example 1:
https://assets.leetcode.com/uploads/2024/03/01/ll.png
Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [10,1,13,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.

Example 2:
https://assets.leetcode.com/uploads/2020/11/05/merge_linked_list_ex2.png
Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.

Constraints:
3 <= list1.length <= 10^4
1 <= a <= b < list1.length - 1
1 <= list2.length <= 10^4

idea:
easy, just count carefully
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

class MergeInBetweenLinkedLists {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode current = list1;

        for (int i = 0; i < a - 1; i++) {
            current = current.next;
        }

        ListNode nodeBeforeA = current;

        for (int i = 0; i < b - a + 1; i++) {
            current = current.next;
        }

        ListNode nodeAfterB = current.next;

        nodeBeforeA.next = list2;

        while (list2.next != null) {
            list2 = list2.next;
        }

        list2.next = nodeAfterB;

        return list1;
    }
}
