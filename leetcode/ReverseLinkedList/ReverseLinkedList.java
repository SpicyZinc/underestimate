/*
idea:
reverse a linked list, there are two ways:
1. recursively
remember base case
then, suppose you have a reverse() method, it can reverse head.next, and return tail
while loop starting from the tail, go back to head.next
let head.next point to head, last return tail which is new head

2. iteratively
idea is simple,
loop through the list
change pointer direction in each step

for linked list while loop, things to remember
while (current.next != null) {
	current = current.next;
}
current finally points to last element of list, not null
while (current != null) {
	current = current.next;
}
current finally points to tail.next == null
*/

import java.util.*;

public class ReverseLinkedList {
	public static void main(String[] args) {
		new ReverseLinkedList();
	}

	// constructor
	public ReverseLinkedList() {
		LinkedList ll = new LinkedList(1);
		ll.next = new LinkedList(2);
		ll.next.next = new LinkedList(3);
		ll.next.next.next = new LinkedList(4);

		System.out.println( ll.toString() );
		// LinkedList newHead1 = reverse(ll);
		LinkedList newHead = reverseIteratively(ll);
		// System.out.println( newHead1.toString() );
		System.out.println( newHead.toString() );
	}

	// reverse linked list recursively
	public LinkedList reverse(LinkedList head) {
		// base case
		if (head == null || head.next == null) {
			return head;
		}

		LinkedList remainingReverse = reverse( head.next );
		LinkedList current = remainingReverse;
		while ( current.next != null ) {
			current = current.next;
		}
		current.next = head;
		// do not forget
		head.next = null;

		return remainingReverse;
	}
	// a little different from the above
	public LinkedList ReverseEvenHarder(LinkedList L) {
		if (L == null || L.next == null) {
			return L;
		}
		LinkedList remainingReverse = ReverseEvenHarder(L.next);
		// we need to update the tail of remaining reverse as our head L
		// this (L.next) is the key to get the tail in constant time!
		// at this time, L.next is last element (tail), pointing it to the head to finish the reverse
		L.next.next = L;
		// we need to set L.next to NULL after that! Otherwise it's causing cycles in list
		L.next = null;

		return remainingReverse;
	}

	// reverse linked list iteratively
	public LinkedList reverseIteratively(LinkedList head) {
		// base case
		if (head == null || head.next == null) {
			return head;
		}

		LinkedList prev = null;
		LinkedList curr = head;
		while (curr != null) {
			// use next to keep a record of current.next
			// before current.next changes
			LinkedList next = curr.next;
			curr.next = prev;
			prev = curr;
			// till here, current did not step further
			// step forward by one
			curr = next;
		}
		// key point
		head = prev;
		
		return head;
	}
}

// LinkedList Node class
class LinkedList {
	int value;
	LinkedList next;

	public LinkedList(int value) {
		this.value = value;
		this.next = null;
	}

	public String toString() {
		LinkedList current = this;
		String result = "";
		while (current != null) {
			result += current.value + " -> ";
			current = current.next;
		}

		return result + "NULL";
	}
}