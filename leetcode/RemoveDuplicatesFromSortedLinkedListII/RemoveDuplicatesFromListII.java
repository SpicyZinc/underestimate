/*
Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3. 

idea:
simple thought:
use a boolean to decide: if duplicate, not add current, add current.next; if not, add current
use a boolean to decide a span is duplicates or not

idea for rewrite with clear explanation
fake a prev before head which is always new ListNode(0)
make head points to fake "prev"

attention:
while(n1.next != null) {n1 = n1.next} this one stops at last element, whose next is null, but n1 is not null
while(n1 != null) {n1 = n1.next} this one n1 becomes last element' next which is null, n1 is null

find a span which are all duplicates, n2 is the last ele in this span, n1.next = n2.next to skip all duplicates
if n2 == n1.next, OK, which is supposed to be equal because this is how declare n2, but finally indicates n2 not moves
not a span of duplicates, following n1 is a different value node, so no need to skip, just connect n1 = n1.next 

general idea:
can have multiple pointers pointing to a same address,
executive pointer plays a role of connecting
return his peer

rewrite remove duplicates from linked list summary
1. remove to leave only one distinct element in linked list is easy to manipulate, because of trait of list 
2. remove all instances of one element must bear the idea that if it happens, should be a span of elements, since this is sorted linked list
*/
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
	// append
	public void append(int x) {
		ListNode curr = this;
		while(curr.next != null) {			
			curr = curr.next;
		}
		curr.next = new ListNode(x);		
	}
	// print
	public void print() {
		ListNode curr = this;
		while(curr != null) {
			System.out.print(curr.val + "  ");
			curr = curr.next;
		}
	}
}

public class RemoveDuplicatesFromListII {
	public static void main(String[] args) {
		// 1->2->3->3->4->4->5
		ListNode aListNode = new ListNode(1);		
		aListNode.append(2);
		aListNode.append(3);
		aListNode.append(3);
		aListNode.append(4);
		aListNode.append(4);
		aListNode.append(5);
		aListNode.print();
		System.out.println();
		RemoveDuplicatesFromListII aTest = new RemoveDuplicatesFromListII();
		ListNode result = aTest.deleteDuplicates(aListNode);
		result.print();
		
	}

	// 03/13/2019
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);

        dummy.next = head;

        head = dummy;

        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            // != indicates next pointer moves
            // address changes, it is address that is changed
            // it must be a span
            // so connect to next.next
            // next stops at exactly where the repetitive span last element
            // notice order matters, next.next != null must be pre
            while (next.next != null && next.val == next.next.val) {
                next = next.next;
            }
            // now detect if span is there by checking next moves or not
            // how to check if next moves, comparing with current

            // move pointer, since it is while loop
            if (current.next != next) {
                current.next = next.next;
            } else { // no span of repetitive elements
                current = current.next;
            }
        }
        
        return head.next;
    }


	// delete
    public ListNode deleteDuplicates(ListNode head) {
		// Sentinel to guard against the changed head node
		ListNode sent = new ListNode(0); 
        sent.next = head;
 
        ListNode cur = head;
		// prev, and sent are pointing the same starting position
		// but the list is changed by operations on 'prev'
		ListNode prev = sent;
		
        while (cur != null) {
            boolean dup = false;
            while (cur.next != null && cur.val == cur.next.val) {
                dup = true;
                cur = cur.next;
            }             
            if (dup) {
                prev.next = cur.next;
                cur = cur.next;
            } else {
                prev = cur; // guarantee that it is to append
                cur = cur.next;
            }
        }
 
        return sent.next;
    }
}