/*
Given a singly linked list L: L0->L1->…->Ln-1->Ln,
reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->…

idea:
this problem contains many useful reusable techniques for linked list
1. find middle position of linked list
2. reverse a linked list
3. merge two linked lists
*/

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ReorderList {
	public static void main(String[] args) {
		ReorderList eg = new ReorderList();

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		eg.printList(n1);
		eg.reorderList(n1);
		eg.printList(n1);
	}

	public void printList(ListNode head) {
		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.val + " ");
			curr = curr.next;
		}
		System.out.print("\n");
	}
	// Thu May 23 15:40:24 2019
	public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // find middle point
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstHalf = head;
        ListNode secondHalf = slow.next;

        // note: not forget to cut off the list, detach two sub lists
        slow.next = null;

        // reverse a linked list, in this case, the second half
        ListNode prev = null;
        ListNode curr = secondHalf;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        secondHalf = prev;

        // interweave two linked lists
        while (secondHalf != null) {
            ListNode next1 = firstHalf.next;
            ListNode next2 = secondHalf.next;
            firstHalf.next = secondHalf;
            secondHalf.next = next1;
            firstHalf = next1;
            secondHalf = next2;
        }
    }
}