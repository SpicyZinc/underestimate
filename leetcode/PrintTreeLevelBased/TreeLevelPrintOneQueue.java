import java.util.*;

class Tree {
	public int value;
	public Tree left;
	public Tree right;
	public Tree(int x) {
		value = x;
		left = right = null;
	}
}

public class TreeLevelPrintOneQueue {
	public static void main(String[] args) {
		Tree myTree = new Tree(1);
		myTree.left = new Tree(2);
		myTree.right = new Tree(3);
		myTree.left.left = new Tree(4);
		myTree.left.right = new Tree(5);
		myTree.right.left = new Tree(6);
		myTree.right.right = new Tree(7);
		
		System.out.print("Only One Queue-based method is ");
		printTreeLevelOneQueue(myTree);
		System.out.println();
	}

	// method based one queue, but only print, no returned list
	public static void printTreeLevelOneQueue(Tree t) {
		Queue<Tree> queue = new LinkedList<>();
		queue.add(t);

		if (queue == null) {
			return;
		}

		Tree temp = queue.poll();

		while (temp != null) {
			System.out.printf("%d ", temp.value);
			queue.add(temp.left);
			queue.add(temp.right);
			temp = queue.remove();
		}
	}
}