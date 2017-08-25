/*
You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

	7 -> 1 -> 6 == 617
+ 	5 -> 9 -> 2 == 295

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

idea:
since the number represented by linked list in a reverse order
so create a new list to hold the result, then walk through the list, 
append sum of two digits from l1 and l2 to the newly-created list

this one is different from Add Two Numbers II in that the second one is already reversed
*/
class ListNode {
	int val;
	ListNode next;
		
	ListNode(int val) {
		this.val = val;
		next = null;
	}
} 
 
public class TwoNumAddSolution {
	// latest self written
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode res = null;
    	ListNode curr = res;
    	int carry = 0;
    	while (l1 != null || l2 != null) {
    		int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
    		carry = val / 10;
    		if (curr == null) {
    			res = new ListNode(val % 10);
                curr = res;
    		}
    		else {
    			curr.next = new ListNode(val % 10);
                curr = curr.next;
    		}

    		if (l1 != null) {
    			l1 = l1.next; 	
    		}
    		if (l2 != null) {
    			l2 = l2.next; 	
    		}
    	}
    	if (carry > 0) {
    		curr.next = new ListNode(carry);
    	}

    	return res;
    }
	
	public static void main(String[] args) {
	    TwoNumAddSolution test = new TwoNumAddSolution();
		
		ListNode list_a = test.fromArray(new int[]{4,7,4,7});
		ListNode list_b = test.fromArray(new int[]{5,3,7,4,7,4});
		
		test.printList(list_a);
		test.printList(list_b);
		
		ListNode newList = test.addTwoNumbers(list_a, list_b);
		test.printList(newList);
	}

	public void printList(ListNode node) {
		while (node != null) {
			System.out.printf("%d -> ", node.val);
			node = node.next;
		}
		System.out.print("END\n");
	}
	
	private ListNode fromArray(int[] array) {
		ListNode head = new ListNode(0);
		ListNode current = head;		
		// insertion from the tail
		for (int i = 0; i < array.length; i++) {
			ListNode temp = new ListNode(array[i]);
			current.next = temp;
			current = current.next;
		}

		return head.next;
	}

	// self wrote again
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = null;
        ListNode curr = ret;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int currValue = ((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val)) + carry;
            ListNode temp = new ListNode(currValue % 10);
            carry = currValue / 10; 
            
            // insert at behind, insert at the back, insertion from the tail
            if (ret == null) {
                ret = temp;
                curr = ret;
            }
            else {
                curr.next = temp;
                curr = curr.next;
            }
            
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
               l2 = l2.next; 
            }
        }
        
        return ret;
    }
}