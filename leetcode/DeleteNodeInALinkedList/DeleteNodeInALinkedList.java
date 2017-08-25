/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
the linked list should become 1 -> 2 -> 4 after calling your function.

idea:
assign the next node value to current which is supposed to delete
then remove the next node by node.next = node.next.next
thus realize the result that change the current node to next node, and delete the current node
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class DeleteNodeInALinkedList {
    public void deleteNode(ListNode node) {
    	if ( node == null) {
    		return;
    	}
		if (node.next == null) {
            node = null;
        }

        if ( node.next != null ) {
            node.val = node.next.val;
        	node.next = node.next.next;
        }
    }
}