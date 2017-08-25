class Node{
	public int value;
	public Node next;
	public Node(int value){
		this.value = value;
		next = null;
	}
	public void printList(Node aNode){
		Node current = aNode;
		while(current != null){
			System.out.printf("%d -> ", current.value);
			current = current.next;
		}
		if(current == null)
			System.out.print("Null");
	}
}

public class RemoveNthNodeToLast{
	public static void main(String[] args){
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
	
	public static Node remove1(Node head, int n) {
		Node p, q;  
        p = head;  
        q = head;
		
        int i = 0;  
        if (n == 0)  
            return head;  
        while(p != null && i < n-1 ) {  
            p = p.next;  
            i++;  
        }  
        if (p == null) {
            return head;
        }
        if (p.next == null) {
            return head.next;
        } 
          
        p = p.next;  
        while (p.next != null) { 
            q = q.next;  
            p = p.next;  
        }  
        q.next = q.next.next;  
        
        return head;  
  
    }  
/* The very right answer to Remove Nth to Last Node */	
	public static Node remove(Node head, int n) {
	// assume n is less than the size of list	
		if(head == null) return head;
		if(n == 0) return head;		
		
		Node minor = head;
		Node major = head;		
		
		int count = 0;
		while(count < n) {
			minor = minor.next;			
			count++;
		}
		if(minor == null) 
			return head.next;
				
		while(minor.next != null) {
			major = major.next;	// why minor.next != null because minor.next could be already "null"		
			minor = minor.next;			
		}		
		major.next = major.next.next; // key knowledge point to delete a node
		
		return head;		
	}
		
	public static Node remove2(Node head, int n) {
		if(head == null) return head; 
        if(n == 0) return head;
		
		Node p = head;  
        Node q = head;
		
        int i = 0;
		while(i < n) // p != null && 
        {  
            p = p.next;  
            i++;  
        }
/*		
        if(p == null)  
            return head;  
        if(p.next == null)  
            return head.next;        
*/         
        while(p.next != null)  
        {  
            q = q.next;  
            p = p.next;  
        }
		
        q.next = q.next.next;  
        return head;  
  
    }
/*
	public static Node remove(Node head, int toBeDeleted){
		Node n = head;
		if(n.value == toBeDeleted)
			return head.next;
		
		while(n.next != null){
			if(n.next.value == toBeDeleted){
				n.next = n.next.next; // key knowledge point to delete a node
			}
			n = n.next;
		}
		return head;
	}
*/
}