/*
Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
original relative order of the nodes in each of the two partitions should be reserved. 

Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5. 

idea:
very simple and direct 
since it is partition, use two lists to hold elements, and then connect them finally

should use append from the behind, not insert at front in the linkedlist

1. build two lists to hold the two partitions, left one starts with 0, right one starts with 1. 0, 1 are just place holders
2. while() to go through the list, <x come to left, >=x come to right, use append (still use while to implement), not insert at the front.
3. current.next = right.next, to skip the first 1 in the right partition which is only a placeholder
4. return left.next to skip the first 1
*/
class ListNode {
	int val;
	ListNode next;
	// constructor
	public ListNode(int x) {
		val = x;
		next = null;
	}
	// append in end
	public void append(int x) {
		ListNode current = this;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new ListNode(x);		
	}
	// print
	public void print() {
		ListNode curr = this;
		while (curr != null) {
			System.out.print( curr.val + "  " );
			curr = curr.next;
		}
	}
}
 
public class PartitionList {
	public static void main(String[] args) {
		new PartitionList();
	}
 	// constructor
	public PartitionList() {
		ListNode aListNode = new ListNode(1);
		/*
		aListNode.next = new ListNode(4);
		aListNode.next.next = new ListNode(3);
		aListNode.next.next.next = new ListNode(2);
		aListNode.next.next.next.next = new ListNode(5);
		aListNode.next.next.next.next.next = new ListNode(2);
		*/
		aListNode.append(4);
		aListNode.append(3);
		aListNode.append(2);
		aListNode.append(5);
		aListNode.append(2);
		System.out.println("The original List is ");
		aListNode.print();
		System.out.println("\nAfter partition List is ");
		ListNode partitionOne = partition(aListNode, 3);
		partitionOne.print();		
	}

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode curr = head;
        
        ListNode left = null;
        ListNode right = null;
        
        while (curr != null) {
            if (curr.val < x) {
                if (left == null) {
                    left = new ListNode(curr.val);
                } else {
                    appendFromBehind(left, curr.val);
                }
            } else {
                if (right == null) {
                    right = new ListNode(curr.val);
                } else {
                    appendFromBehind(right, curr.val);
                }
            }
            curr = curr.next;
        }

        // before connecting, check if left is empty
        // if so, no need to connect
        if (left == null) {
            return right;            
        }
        // connecting left and right
        // find end of left
        curr = left;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = right;
        
        return left;
    }
    
    private void appendFromBehind(ListNode head, int x) {
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new ListNode(x);
    }
}