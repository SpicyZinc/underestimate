/*
Intersection of Two Linked Lists
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

idea:
1. hashmap is natural option
2. use the rule when A or B reaches its end, change the pointer to B or A, if they meet (currA == currB),
that is the intersection node
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	if (headA == null || headB == null) {
    		return null;
    	}

        ListNode currentA = headA;
        ListNode currentB = headB;
        int lengthA = 0;
        int lengthB = 0;
        // get length of headA
        while (currentA != null) {
        	lengthA++;
        	currentA = currentA.next;
        }
        // get length of headB
        while (currentB != null) {
        	lengthB++;
        	currentB = currentB.next;
        }
        int diff = lengthA >= lengthB ? (lengthA - lengthB) : (lengthB - lengthA);
        boolean flag = lengthA >= lengthB ? true : false;

        if (flag) {
        	currentA = headA;
        	while (diff > 0) {
        		diff--;
        		currentA = currentA.next;
        	}
        	currentB = headB;
        	while (currentA != null && currentB != null) {
        		if (currentA == currentB) {
        			return currentA;
        		}
        		currentA = currentA.next;
        		currentB = currentB.next;
        	}

        	return null;
        }
        else {
        	currentB = headB;
        	while (diff > 0) {
        		diff--;
        		currentB = currentB.next;
        	}
        	currentA = headA;
        	while (currentA != null && currentB != null) {
        		if (currentA == currentB) {
        			return currentA;
        		}
        		currentA = currentA.next;
        		currentB = currentB.next;
        	}

        	return null;
        }
    }
}