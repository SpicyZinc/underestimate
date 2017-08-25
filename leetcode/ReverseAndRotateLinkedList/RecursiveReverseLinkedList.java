class Node{
	int value;
	Node next;
	public Node(int value){
		this.value = value;
		next = null;
	}	
}

public class RecursiveReverseLinkedList{
	public static void main(String[] args){
		Node aHead = new Node(1);
		aHead.next = new Node(2);
		aHead.next.next = new Node(3);
		aHead.next.next.next = new Node(4);
		aHead.next.next.next.next = new Node(5);
		
		System.out.print("Before reversal of List: ");
		printList(aHead);
		
		aHead = recursiveReverse(aHead);
		System.out.print("\nAfter reversal of List: ");
		printList(aHead);		
		
	}
	
	private static Node recursiveReverse(Node header){
		
		
		Node first = header;
		Node rest = header.next;
		
		if(header == null) return header;
		if(rest == null) return header;
		
		rest = recursiveReverse(rest);
		
		first.next.next = first;
		///rest.next = first;
		/// Both of them work, because rest = first.next;
		first.next = null;
		
		return rest;
	}
	
	private static void printList(Node header){
		Node current = header;
		while(current != null){
			System.out.printf("%d ", current.value);
			current = current.next;
		}
	}

}