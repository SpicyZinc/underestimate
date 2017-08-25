/**
Given an array where elements are sorted in ascending order, 
convert it to a height balanced BST.

idea:
Binary search on this sorted array, 
find mid as root, 
find left's mid in the left part as root.left
find right's mid in the right part as root.right


Binary traversal on sorted array, recursion

*/
import java.util.*;

// Definition of binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
	// a way to test if BST, because BST in_order print is a sorted list
	public void inOrder() {
		if (this != null) {
			if (this.left != null)
				this.left.inOrder();
				
			System.out.print(this.val + " ");
			
			if (this.right != null)
				this.right.inOrder();			
		}				
	}
}

public class SortedArrayToBST {

	public static void main(String[] args) {
		new SortedArrayToBST();
	}
	
	// constructor
	//
	public SortedArrayToBST() {
		Random random = new Random();
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(50);
			System.out.print(a[i] + " ");
		}
		
		Arrays.sort(a);
		
		System.out.print("\n");
		TreeNode root = sortedArrayToBST(a);
		root.inOrder();
	
	}
	
	public TreeNode sortedArrayToBST(int[] a) {
		if (a == null || a.length == 0) 
			return null;
		if (a.length == 1) 
			return new TreeNode(a[0]);
		
		return sortedArrayToBST(a, 0, a.length - 1);		
	}
	
	public TreeNode sortedArrayToBST(int[] a, int start, int end) {
		// Crucial Part
		// start and end, similar parameters in array
		// must check if it is a valid range
		// 
		if (start > end) 
			return null;
		
		int mid = (start + end) / 2;
		TreeNode res = new TreeNode(a[mid]);
		res.left = sortedArrayToBST(a, start, mid - 1);
		res.right = sortedArrayToBST(a, mid + 1, end);
			
		return res;
	}
}