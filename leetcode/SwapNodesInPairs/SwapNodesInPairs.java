/*
Given a linked list, 
swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, 
return the list as 2->1->4->3.

Require constant space

idea:

1. iterative version

a fake header is needed,
and while (n2 != null && n2.next != null)
faker 1 2 is small group to do the swap
set aside 3 by n2.next.next

faker --> 1 --> 2 --> 3 --> 4 --> 5
  |       | 
  |       |   
  n1      n2

faker --> 2 --> 1 --> 3 --> 4 --> 5
  |             | 
  |             |   
  n1            n2

  so n1 = n2
	 n2 = n1.next

2. recursive version
(1) swap the first single pair of nodes
(2) then the rest keep calling swapSinglePair(StartingNodeInnextPair)
	assign it to node.next.next

https://www.cnblogs.com/grandyang/p/5648424.html
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class SwapNodesInPairs {
	public static void main(String[] args) {
		SwapNodesInPairs eg = new SwapNodesInPairs();

		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
		node.next.next.next.next.next = new ListNode(6);

		eg.swapPairs(node);
		
	}

	// 07/18/2018
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode curr = head;
		ListNode next = head.next;
		ListNode rest = head.next.next;
		
		next.next = curr;
		head = next;
		curr.next = swapPairs(rest);
		
		return head;
	}

	// recursive version
	public ListNode swapPairs(ListNode head) {  
		if (head == null || head.next == null) {
			return head;
		}  
		  
		return swapSinglePair(head);  
	}
	private ListNode swapSinglePair(ListNode node) {
		if (node == null || node.next == null) {
			return node;
		}

		ListNode next = node.next.next;
		// swap a single pair of nodes
		ListNode temp = node;
		node = node.next;
		node.next = temp;

		node.next.next = swapSinglePair(next);

		return node;
	}
	
	// iterative version
	public ListNode swapPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode prev = dummy;
		ListNode curr = head;
		
		while (curr != null && curr.next != null) {
			ListNode rest = curr.next.next;

			curr.next.next = prev.next;
			prev.next = curr.next;
			curr.next = rest;
			
			prev = curr;
			curr = prev.next;

		}
		
		return dummy.next;
	}
}