/*
Given a linked list, remove the nth node from the end of list and return its head.
For example,
Given linked list: 1->2->3->4->5, and n = 2. 
After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.

idea:
locate the nth node from end of list, remove it
how? maintain two pointers, the distance between them is n
so when minor one reaches end of list, it means major reaches the Nth Node From End of List,
remove it, and return head
*/

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}

	public void printList(Node aNode){
		Node current = aNode;
		while (current != null) {
			System.out.printf("%d -> ", current.val);
			current = current.next;
		}
		if (current == null) {
			System.out.print("Null");
		}
	}
}

public class RemoveNthNodeFromEndOfList {
	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		
		head.printList(head);
		System.out.println();
		
		Node newH = remove(head, 6);
		newH.printList(newH);
	}


	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (n == 0 || head == null) {
            return head;
        }
    
        ListNode slow = head;
		ListNode fast = head;
        // make the slow and fast have n difference gap
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // if fast already reaches tail of the list
        // slow has 'n' far from fast
        // means Nth is the head, so return head.next
        if (fast == null) {
            return slow.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        slow.next = slow.next.next;
        
        return head;
	}

	// self come up resolution
	public ListNode removeNthFromEnd(ListNode head, int n) {
        int index = 0;
        int length = 0;
        ListNode curr = head;
        
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        // if n exceeds the total length of list
        if (n >= length) {
            return head.next;
        }
 
        curr = head;
        while (curr != null) {
            index++;
            // find the node to remove
            if (index + n == length) {
                curr.next = curr.next.next;       
            } else {
                curr = curr.next;    
            }
        }
        
        return head;
    }
}