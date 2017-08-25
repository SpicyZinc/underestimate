/*
http://en.wikipedia.org/wiki/Tree_traversal
*/
import java.util.Stack;

public class TreeTraversal {
	public static void main(String[] args) {
		TreeNode myTree = new TreeNode(1);
		myTree.left = new TreeNode(2);
		myTree.right = new TreeNode(3);
		myTree.left.left = new TreeNode(4);
		myTree.left.right = new TreeNode(5);
		myTree.right.left = new TreeNode(6);
		myTree.right.right = new TreeNode(7);
		
		System.out.print("InOrder Traversal with Recursion: ");
		myTree.inorderRecursive();
		System.out.print("\nInOrder Traversal with Stack: ");
		myTree.inorderStack();
		
		System.out.print("\nPreOrder Traversal with Recursion: ");
		myTree.preorderRecursive();
		System.out.print("\nPreOrder Traversal with Stack: ");
		myTree.preorderStack();
		
		System.out.print("\nPostOrder Traversal with Recursion: ");
		myTree.postorderRecursive();
		System.out.print("\nPostOrder Traversal with Stack: ");
		myTree.postorderStack();
		System.out.println();
	}
}

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value){
		this.value = value;
		left = right = null;
	}
/***************InOrder Traversal*************************/		
	public void inorderStack() {
		Stack<TreeNode> myStack = new Stack<TreeNode>();
		TreeNode current = this;
		
		while (current != null || !myStack.empty()) {
			if (current != null) {
				myStack.push(current);
				current = current.left;
			}
			else {
				current = myStack.pop();
				System.out.print(current.value + " ");
				current = current.right;
			}
		}
	}
	
	
	public void inorderRecursive(){
		if (this != null) {
			if (left != null) {
				left.inorderRecursive();
			}
			System.out.print(this.value + " ");
			if (right != null) {
				right.inorderRecursive();
			}
		}
	}
/****************PreOrder Traversal***********************/	
	public void preorderStack() {
		Stack<TreeNode> myStack = new Stack<TreeNode>();
		TreeNode current = this;
		
		while (current != null || !myStack.empty()) {
			if (current != null) {
				System.out.print(current.value + " "); // because each time current.value is already printed out
				myStack.push(current.right);
				// myStack.push(current.left);
				current = current.left;
			}			
			else {
				current = myStack.pop();				
			}
		}
	}
	
	public void preorderRecursive() {
		if (this != null) {
			System.out.print(this.value + " ");
			if (left != null) {
				left.preorderRecursive();
			}
			if (right != null) {
				right.preorderRecursive();
			}
		}
	}
	
/**************PostOrder Traversal***********************/
	public void postorderStack(){
		Stack<TreeNode> myStack = new Stack<TreeNode>();
		TreeNode previous = null;
		// generally previous is before (meaning parent of current) current, so previous should be null before current is root.
		// And once current.left or .right == previous, it indicates current goes back or current has been visited before.
		
		myStack.push(this);
		
		while (!myStack.isEmpty()) {
			TreeNode current = myStack.peek();
			
			if (current == null) 
				myStack.pop();			
			else if (current.left==null && current.right==null) {				
				System.out.print(current.value + " ");
				myStack.pop();
			}			
			else if (current.left == previous) 
				myStack.push(current.right);			
			else if (current.right == previous) {
				System.out.print(current.value+ " ");
				myStack.pop();
			}			
			else
				myStack.push(current.left);			
			
			previous = current;
		}
	}
		
	
	public void postorderRecursive() {
		if (this != null) {
			if (left != null) {
				left.postorderRecursive();
			}
			if (right != null) {
				right.postorderRecursive();
			}
			System.out.print(this.value + " ");
		}
	}
}