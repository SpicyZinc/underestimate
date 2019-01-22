/*
Design your implementation of the linked list.
You can choose to use the singly linked list or the doubly linked list.
A node in a singly linked list should have two attributes: val and next.
val is the value of the current node, and next is a pointer/reference to the next node.

If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list.
Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:
get(index) : Get the value of the index-th node in the linked list.
If the index is invalid, return -1.

addAtHead(val) : Add a node of value val before the first element of the linked list.
After the insertion, the new node will be the first node of the linked list.

addAtTail(val) : Append a node of value val to the last element of the linked list.

addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
If index equals to the length of linked list, the node will be appended to the end of linked list.
If index is greater than the length, the node will not be inserted.

deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.

Example:
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3

Note:
All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.

idea:
note, 要有一个size来判断什么时候 query的index 不合法
还有就是前面没有items 而从后面 e.g. 2 开始放入是不合法的 记得index == 0 equals addAtHead()
*/

class Node {
	int val;
	Node next;

	public Node(int val) {
		this.val = val;
		this.next = null;
	}
}

class MyLinkedList {
	Node head;
	int size;

	/** Initialize your data structure here. */
	public MyLinkedList() {
		head = null;
		size = 0;
	}

	/** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	public int get(int index) {
		if (index < 0 || index >= size) {
			return -1;
		}

		Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

		return curr.val;
	}

	/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	public void addAtHead(int val) {
		Node newNode = new Node(val);
		newNode.next = head;
		head = newNode;
		size++;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		Node newNode = new Node(val);

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}

		curr.next = newNode;
        size++;
	}

	/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
	public void addAtIndex(int index, int val) {
		// 注意 add() index 必须 > size
		// 注意index的情况
        if (index < 0 || index > size) {
        	System.out.println("return");
			return;
		}

		if (index == 0) {
			addAtHead(val);
		} else {
			Node newNode = new Node(val);

			Node curr = head;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.next;
			}

			Node next = curr.next;
			curr.next = newNode;
			newNode.next = next;

			size++;
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (index < 0 || index >= size) {
			return;
		}

		Node curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

		curr.next = curr.next.next;

		size--;
	}


	public String toString() {
		String s = "";
		Node curr = head;
		while (curr != null) {
			s += " " + curr.val;
			curr = curr.next;
		}

		return s;
	}

	public void print() {
		System.out.println(toString());
	}

	public static void main(String[] args) {
		MyLinkedList ll = new MyLinkedList();
		ll.get(0);
		ll.print();
		ll.addAtIndex(1, 2);
		ll.print();
		ll.get(0);
		ll.get(1);
		ll.addAtIndex(0, 1);
	}
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */