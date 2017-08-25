/**
1->2->3->4->5->null
it would become:
5->4->3->2->1->null

"reverse" method cannot return a linkedlist, it can only return a Type "Node"


Attention: my thought is to create a new Node once meeting a node when traversing the linkedlist
and use insert at the front of the new linkedlist to implement the "reverse" result
and return a new linked list with a newHead. It works fine.

But if I use method "reverseShouldUse()", it seems like it changed the linkedlist, and if put before regular method of
reversing, it cannot output the reversed linked list any more. "reverseShouldUse()" and "reverse_regular()" order matters.
the returned newHead is the tail of reversed linked list.
*/

class Node {
	public int value;
	public Node next;
	
//constructor	
	public Node(int value){
		this.value = value;
		next = null;
	}
}

public class MyOwnLinkedList{
	private Node head; // = new Node();
	
	public static void main(String[] args){
		MyOwnLinkedList aLL = new MyOwnLinkedList();		
		aLL.insert(5);
		aLL.insert(4);
		aLL.insert(3);
		aLL.insert(2);
		aLL.insert(1);	
		//print linkedlist
		System.out.println("Print original LinkedList using two methods: ");
		aLL.printAll();
		aLL.printAll(aLL.head);
		
				
		//reverse 1
		System.out.println("Print reversed LinkedList using method 1 without parameter: ");
		Node reverse = aLL.reverse_regular();
		while(reverse != null){
			System.out.print(reverse.value + " -> ");
			reverse = reverse.next;
		}
		System.out.print("null\n");
		
		//reverse 2
		System.out.println("Print reversed LinkedList using method 1 with parameter: ");
		Node reverse3 = aLL.reverse(aLL.head);
		while(reverse3 != null){
			System.out.print(reverse3.value + " -> ");
			reverse3 = reverse3.next;
		}
		System.out.print("null\n");
		
		//reverse 3
		System.out.println("Print reversed LinkedList using method 2: ");
		Node reverse2 = aLL.reverseShouldUse();
		while(reverse2 != null){
			System.out.print(reverse2.value + " -> ");
			reverse2 = reverse2.next;
		}
		System.out.print("null\n");	
		
		
		//reverse not understand
		
		System.out.println("Print reversed LinkedList using method not quite understand: ");
		Node reverse9 = aLL.reverse_not_understand(aLL.head);
		while(reverse9 != null){
			System.out.print(reverse9.value + " -> ");
			reverse9 = reverse9.next;
		}
		System.out.print("null\n");
	
	/**	
	///=====================================================================================================///	
		System.out.println("=====================");
		//reverse 4
		reverse = aLL.recursiveReverse(aLL.head);
		while(reverse != null){
			System.out.print(reverse.value + " -> ");
			reverse = reverse.next;
		}
		System.out.print("null\n");
		
		//reverse 5
		reverse = aLL.reverseRecursively(aLL.head);
		while(reverse != null){
			System.out.print(reverse.value + " -> ");
			reverse = reverse.next;
		}
		System.out.print("null\n");
	*/	
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public void insert(int x){
		Node newNode = new Node(x);
		newNode.next = head;
		head = newNode;		
	}
	
	public void printAll(){
		Node current = head;
		while(current != null){
			System.out.print(current.value + " -> ");
			current = current.next;
		}
		System.out.print("null\n");
	}
	// overloaded method printAll()
	public void printAll(Node firstNode){
		Node current = firstNode;
		while(current != null){
			System.out.print(current.value + " -> ");
			current = current.next;
		}
		System.out.print("null\n");
	}

/**
 all "reverse" methods
*/ 
	
	public Node reverse_regular(){
		Node current = head;
		Node newHead = null;
		while(current != null){
			Node newNode = new Node(current.value);
			newNode.next = newHead;
			newHead = newNode; 
			current = current.next;
		}
		return newHead;
	}
	
// overloaded method reverse_regular()
	public Node reverse(Node oldHead){
		Node current = oldHead;
		Node newHead = null;
		while(current != null){
			Node newNode = new Node(current.value);
			newNode.next = newHead;
			newHead = newNode; 
			current = current.next;
		}
		return newHead;
	}
	
	
	
	//another "reverse" method without creating a newNode
	
	public Node reverseShouldUse(){
		Node current = head;
		Node newHead = null;
		while(current != null){
			Node nextNode = current.next;
			current.next = newHead;
			newHead = current;
			current = nextNode;
		}
		return newHead;
	}
	
	// recursively reverse a linkedlist
	public Node recursiveReverse(Node n){
		
		if(n == null || n.next == null)
		return n;

		reverse(n.next);
		n.next.next = n;
		n.next = null;

		return n;
		
	}		
	// recursively reverse a linkedlist
	public Node reverseRecursively(Node list){
		if (list == null || list.next==null) 
			return list;
		Node nextItem = list.next;
		list.next = null;
		Node reverseRest = reverse(nextItem);
		nextItem.next = list;
		return reverseRest;
	}	
	
	public Node reverse_not_understand(Node head){
		
		Node prev   = null;
		Node current = head;
		Node next;
		while (current != null)
		{
			next = current.next;  
			current.next = prev;   
			prev = current;
			current = next;
		}
		return head = prev;

	}
	
}

