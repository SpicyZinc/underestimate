/*
Given a linked list, return the node where the cycle begins. 
If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

idea: best solution explaining 
http://www.cnblogs.com/hiddenfox/p/3408931.html


basic: 
1. find the node X where fast and slow meet
2. let slow walk from X 
3. let fast back to head, and walk from head
4. where they meet again is start of cycle

derivative questions from this issue:
1. 环的长度是多少?
2. 如何找到环中第一个节点(Linked List Cycle II)
3. 如何将有环的链表变成单链表(解除环)
4. 如何判断两个单链表是否有交点? 如何找到第一个相交的节点?


1. 从一开始到二者第一次相遇, while循环的次数就等于环的长度.
2. 我们已经得到了结论a=c, 那么让两个指针分别从X和Z开始走, 每次走一步, 那么正好会在Y相遇!也就是环的第一个节点.
3. 在上一个问题的最后, 将c段中Y点之前的那个节点与Y的链接切断即可.
4. 如何判断两个单链表是否有交点？先判断两个链表是否有环, 如果一个有环一个没环, 肯定不相交；如果两个都没有环, 判断两个列表的尾部是否相等；如果两个都有环, 判断一个链表上的Z点是否在另一个链表上.
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class DetectLinkedListCycle {
    public static ListNode detectCycle(ListNode head) {
	    ListNode slow = head;
	    ListNode fast = head;

	    while (true) {
	    	// there is no cycle if fast encountering null
	        if (fast == null || fast.next == null) {
	            return null;   
	        }
	        // find where fast and slow meet each other
	        slow = slow.next;
	        fast = fast.next.next;
	        if (fast == slow) {
	            break;
	        }
	    }
	    // let slow start walking from head
	    slow = head;
	    while (slow != fast) {
	        slow = slow.next;
	        fast = fast.next;
	    }

	    return slow;

	    /* also works
	    // let fast start walking from head
	    fast = head;
	    while (slow != fast) {
	        slow = slow.next;
	        fast = fast.next;
	    }

	    return fast;
	    */
	}
	// with opening an extra hashset memory space
	public static ListNode detectCycleWithExtraSpace(ListNode head) {
		HashSet<ListNode> hs = new HashSet<ListNode>();
		ListNode current = head;

		while (current != null) {
			if (hs.contains(current)) {
				return current;
			}
			hs.add(current);
			current = current.next;
		}

		return null;
	}
}