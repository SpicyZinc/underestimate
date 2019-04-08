/*
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3 (common part length is c)
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

idea:
1. hashset is natural option
2. use the rule when A or B reaches its end, change the pointer to B or A,
if they meet (currA == currB), that should be the intersection node
两个链表的长度之和, 一定会相等 (a + c + b = b + c + a)
3. direct method, find the diff in length of two lists, start from the point where A and B are of equal length
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

        Set<ListNode> hs = new HashSet<>();
        ListNode curr = headA;
        while (curr != null) {
            hs.add(curr);
            curr = curr.next;
        }
        
        curr = headB;
        while (curr != null) {
            if (hs.contains(curr)) {
                return curr;
            }
            curr = curr.next;
        }
        
        return null;
    }

    // (a + c) + b = (b + c) + a 换head 相等处 就是 intersection point
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode currA = headA;
        ListNode currB = headB;
        // prerequisite is there must be intersection
        while (currA != currB) {
            currA = currA == null ? headB : currA.next;
            currB = currB == null ? headA : currB.next;
        }
        
        return currA;
    }

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

        int diff = Math.abs(lengthA - lengthB);
        ListNode longer = lengthA >= lengthB ? headA : headB;
        ListNode shorter = lengthA >= lengthB ? headB : headA;
        // make longer equal length as shorter
        for (int i = 0; i < diff; i++) {
            longer = longer.next;
        }

    	while (longer != null && shorter != null) {
    		if (longer == shorter) {
    			return longer;
    		}

    		longer = longer.next;
    		shorter = shorter.next;
    	}

    	return null;
    }
}