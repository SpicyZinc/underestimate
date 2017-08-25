/**
 * A simple sinly-linked list class to show off a few of the list 
 * manipulation algorithms - insert, remove, resize, rotate, copy, etc.
 */
public class LinkedList {
    
    private Node head;    
    private int size;
//constructor
    public LinkedList() {
        head = null;
		size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * Returns the node at the given index in this list.
     *
     * @param index the index of the node to get
     * @exception IndexOutOfBoundsException if the index is out of bounds
     */
    public Node nodeAt(int index) {
	if (index < 0 || index >= size)
	    throw new IndexOutOfBoundsException();

	Node n = head;
	for (int i = 0; i < index; i++, n = n.next)
	    ;
		
	return n;
    }

    /**
     * Returns the element at the given index.
     *
     * @param index the index of the element to get
     * @exception IndexOutOfBoundsException if the index is invalid
     */
    public Object get(int index) {
	Node n = nodeAt(index);		// may throw the exception
	return n.element;
    }

    /**
     * Sets the element at the given index to the given value.
     *
     * @param index the index of the element to set
     * @param elem the new value to set the element to
     * @return the previous value before the change
     * @exception IndexOutOfBoundsException if the index is invalid
     */
    public Object set(int index, Object elem) {
	Node n = nodeAt(index);		// may throw the exception

	Object prevElem = n.element;
	n.element = elem;
	return prevElem;
    }

    /**
     * Returns a copy of this List.
     *
     * @return a new List whose elements are the same as this List's
     */
    public LinkedList copy() {
	LinkedList newList = new LinkedList();
	for (Node n = head; n != null; n = n.next) {
	    newList.insert(newList.size(), n.element);
	}
	return newList;
    }

    /**
     * Reverses the order the elements in this List.
     */
    public void reverse() {
	Node newHead = null;

	// We iterate over the nodes in the original list, and add each
	// node to the *beginning* of the new list, which reverses the 
	// order. Have to be careful when we iterate -- have to save the
	// reference to the *next* node before changing the next link.
	Node n = head;
	while (n != null) {
	    Node nextNode = n.next;		// need to do this!

	    // now change the link so that node 'n' is placed in the
	    // beginning of the list
	    n.next = newHead;
	    newHead = n;

	    // Can't do n = n.next because the link is changed. That's why
	    // we saved the reference to the next node earlier, so we can
	    // use that now.
	    n = nextNode;
	}
	
	head = newHead;
    }

    /**
     * Inserts the given element at the given index in this List.
     *
     * @param index the index of the newly inserted element
     * @param elem the element to insert into this List
     * @exception IndexOutOfBoundsException if the index is invalid
     */
    public void insert(int index, Object elem) {
		// Check for invalid index first. Should be between 0 and size (note:
		// note size-1, since we can insert it *after* the tail node (tail 
		// node has an index of size-1).
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();

		// Create the new node to hold the element in the list
		Node newNode = new Node(elem, null);
		
		// Remember the special case -- in the beginning of the list, when
		// the head reference changes.
		if (index == 0) {
			newNode.next = head;
			head = newNode;
		} 
		else {
			// get the predecessor node first
			Node pred = nodeAt(index - 1);
			newNode.next = pred.next;
			pred.next = newNode;
		}
		size++;
    }

    /**
     * Removes the element at the given index in this List.
     *
     * @param index the index of the element to remove
     * @exception IndexOutOfBoundsException if the index is invalid
     */
    public void remove(int index) {
	// Check for invalid index first. Should be between 0 and size-1.
	if (index < 0 || index >= size)
	    throw new IndexOutOfBoundsException();

	// Reference to the removed node.
	Node removedNode = null;

	// Remember the special case -- from the beginning of the list, when
	// the head reference changes.
	if (index == 0) {
	    removedNode = head;
	    head = head.next;
	} else {
	    // get the predecessor node first
	    Node pred = nodeAt(index - 1);
	    removedNode = pred.next;
	    pred.next = removedNode.next;
	}
	
	// Help the GC
	removedNode.element = null;
	removedNode.next = null;

	size--;
    }

    /**
     * Rotates this List's elements left by one position.
     */
    public void rotateLeft() {
	// Bug alert - does it work for empty lists?
	Node oldHead = head;
	head = head.next;

	// Now append the old head to the end of this list. Find the tail,
	// and add the old head after the tail.
	Node tail = head;
	while (tail.next != null)
	    tail = tail.next;

	// Now add after tail
	tail.next = oldHead;
	oldHead.next = null;
    }

    /**
     * Rotates this List's elements right by one position.
     */
    public void rotateRight() {
	// Bug alert - does it work for empty lists?

	// Need to find tail and it's predecessor (do you see why?)
	Node p = null;
	Node q = head;
	while (q.next != null) {
	    p = q;
	    q = q.next;
	}

	// Now q points to the tail node, and p points to it's predecessor
	// First make tail the new head 
	q.next = head;
	head = q;

	// make p the tail
	p.next = null;

    }

    /**
     * Returns an array of the elements in this list.
     *
     * @return an array of the elements in this list.
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
	int i = 0;
	for (Node n = head; n != null; n = n.next, i++)
	    array[i] = n.element;

	return array;
    }

    /**
     * Returns a string representation of the elements in this list.
     *
     * @return a string representation of the elements in this list.
     */
    public String toString() {
	String str = "[ ";
	for (Node n = head; n != null; n = n.next)
	    str = str + n.element + " ";

	str = str + "]";
	return str;
    }

    /**
     * Prints this List's elements to System.out.
     */
    public void print() {
		System.out.println(this);
    }

    /**
     * Represents a node in the singly-linked list. 
     */
    private class Node {
	// The element that we want to put in the list
		public Object element;
		// The reference to the next node in the list
		public Node next;
		/**
		 * Creates a new node with given element and next node reference.
		 *
		 * @param e the element to put in the node
		 * @param n the next node in the list, which may be null
		 */
		public Node(Object e, Node n) {
			element = e;
			next = n;
		}
    }

    /**
     * Tests this List class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	System.out.println(">> Creating an empty List object");
        LinkedList a = new LinkedList();

	System.out.println(">> Inserting 0 ... 4 into a");
	for (int i = 0; i < 5; i++)
	    a.insert(a.size(), new Integer(i));
	a.print();

	System.out.println(">> Copying a to b, and printing b");
	LinkedList b = a.copy();
	b.print();

	System.out.println(">> Inserting 5 ... 7 in a");
	for (int i = 5; i < 8; i++)
	    a.insert(a.size(), new Integer(i));
	a.print();

	System.out.println(">> Reversing a");
	a.reverse();
	a.print();
	System.out.println(">> Reversing a again!");
	a.reverse();
	a.print();

	System.out.println(">> Inserting 19 at index 3 in a");
	a.insert(3, new Integer(19));
	a.print();

	System.out.println(">> Inserting -7 at index 0 in a");
	a.insert(0, new Integer(-7));
	a.print();

	System.out.println(">> Inserting 3 at index 17 in a - invalid");
	try {
	    a.insert(17, new Integer(3));
	} catch(IndexOutOfBoundsException ex) {
	    System.err.println("ERROR: Index 17 is out of bounds");
	}

	System.out.println(">> Removing element at index 7 in a");
	a.remove(7);
	a.print();

	System.out.println(">> Removing first element in a");
	a.remove(0);
	a.print();

	System.out.println(">> Removing last element in a");
	a.remove(a.size() - 1);
	a.print();

	System.out.println(">> Removing 17th element in a - invalid");
	try {
	    a.remove(17);
	} catch(IndexOutOfBoundsException ex) {
	    System.err.println("ERROR: Index 17 is out of bounds");
	}

	System.out.println(">> Rotating a left by one position");
	a.rotateLeft();
	a.print();

	System.out.println(">> Rotating a right by one position");
	a.rotateRight();
	a.print();
    }
}

