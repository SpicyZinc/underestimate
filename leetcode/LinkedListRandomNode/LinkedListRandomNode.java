/*
Given a singly linked list, return a random node's value from the linked list.
Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

idea:
reservoir sampling, in the end, all are 1/n

從S中抽取首k項放入「水塘」中
對於每一個S[j]項（j ≥ k）：
   隨機產生一個範圍從0到j的整數r
   若 r < k 則把水塘中的第r項換成S[j]項

The general question is get k elements randomly from n elements
from S(n elements) pick up the first k elements into a reservoir
int j = k; pointer != null; pointer++ (j >= k)
randomNum = random(j)
if (randomNum < k)
switch elements at randomNum and j
here switch just uses assignment since only 1 elements

but this question is k = 1
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class LinkedListRandomNode {
	Random random;
	ListNode head;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.random = new Random();
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
    	int ans = 0;
		ListNode p = head;
		for (int i = 1; p != null; p = p.next, i++) {
			int r = random.nextInt(i);
			if (r < 1) {
				ans = p.val;
			}
		}
		return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */