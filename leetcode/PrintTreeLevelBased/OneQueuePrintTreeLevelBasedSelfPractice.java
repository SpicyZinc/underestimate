import java.util.LinkedList;
import java.util.Queue;

public class OneQueuePrintTreeLevelBasedSelfPractice
{
	public static void main(String[] args)
	{	
		TreeNode mytree = new TreeNode(1);
		mytree.left = new TreeNode(2);
		mytree.right = new TreeNode(3);
		mytree.left.left = new TreeNode(4);
		mytree.left.right = new TreeNode(5);
		mytree.right.left = new TreeNode(6);
		mytree.right.right = new TreeNode(7);
		System.out.println("Another way to call printLevelTraversalBinary Tree: ");
		mytree.print();
		
		System.out.println("\nLevel Traversal of A Binary Tree: ");
		printTreeLevel(mytree);
	}
/*
Make the use of Queue, add() each TreeNode one level by one, from left to right
poll() or remove() each TreeNode and print its value, the result would be the 
level by level and from left to right traversal of the binary tree.
*/	
	public static void printTreeLevel(TreeNode root)
	{
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		// which means the queue is empty at the beginning
		if ( queue == null ) return;
		TreeNode current = queue.remove();
		while ( current != null )
		{
			System.out.print(current.value + " ");
			queue.add(current.left);
			queue.add(current.right);
			current = queue.remove();			
		}
	}
}

class TreeNode
{
	public int value;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int value)
	{
		this.value = value;
		left = right = null;
	}
	
	public void print()
	{
		Queue<TreeNode> aQueue = new LinkedList<TreeNode>();
		aQueue.add(this);
		TreeNode tmp = aQueue.remove();
		while( tmp != null )
		{
			System.out.printf("%d ", tmp.value);
			aQueue.add(tmp.left);
			aQueue.add(tmp.right);
			tmp = aQueue.remove();
		}
	}
}