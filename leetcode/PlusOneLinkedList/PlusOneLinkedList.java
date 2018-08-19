/*
Given a non-negative number represented as a singly linked list of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list.


Example:
Input:
1->2->3
Output:
1->2->4

idea:
the problem is to test reverse linked list

https://www.cnblogs.com/grandyang/p/5626389.html
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    String getString() {
    	String s = "";
    	ListNode curr = this;
    	while (curr != null) {
    		s += curr.val + " ";
    		curr = curr.next;
    	}

    	return s;
    }
}

class PlusOneLinkedList {
	public static void main(String[] args) {
		PlusOneLinkedList eg = new PlusOneLinkedList();
		ListNode head = new ListNode(9);
		head.next = new ListNode(9);
		head.next.next = new ListNode(9);

		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.val + "  ");
			curr = curr.next;
		}
		System.out.println();
		ListNode result = eg.plusOne(head);
		curr = result;
		while (curr != null) {
			System.out.print(curr.val + "  ");
			curr = curr.next;
		}
	}
	// self written one, good
	public ListNode plusOne(ListNode head) {
        int carry = 1;
        int sum = carry;
        ListNode reversed = reverse(head);
        ListNode curr = reversed;
        while (curr.next != null) {
            sum = carry + curr.val;
            curr.val = sum % 10;
            carry = sum / 10;
            // nothing to carry, no need to continue, can stop early
			if (carry == 0) {
				break;
			}
            curr = curr.next;
        }
        // separately treat last one
        sum = curr.val + carry;
        curr.val = sum % 10;

        if (sum >= 10) {
            curr.next = new ListNode(sum / 10);
            curr = curr.next;
        }
        
        return reverse(reversed);
    }
    
    public ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }


	public ListNode plusOne(ListNode head) {
		if (head == null) {
			return head;
		}

		ListNode reversed = reverseList(head);
		ListNode curr = reversed;
		ListNode prev = curr; // keep prev for carry > 0 to create a new node

		int carry = 1;
		while (curr != null) {
			prev = curr;
			int sum = curr.val + carry;
			curr.val = sum % 10;
			carry = sum / 10;
			// nothing to carry, no need to continue, can stop early
			if (carry == 0) {
				break;
			}
			curr = curr.next;
		}
		if (carry > 0) {
			prev.next = new ListNode(carry);
		}

		return reverseList(reversed);
	}

	public ListNode reverseList(ListNode head) {
		ListNode curr = head;
		ListNode prev = null;

		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}
}