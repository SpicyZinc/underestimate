/*
print the binary tree in level order with newline in the end of each level
Attention: when it is not full binary search tree
*/

import java.util.*;

class Tree {
	public int value;
	public Tree left;
	public Tree right;
	public Tree(int x){
		value = x;
		left = right = null;
	}
}

public class TreeLevelPrint{
	public static void main(String[] args) {
		Tree myTree = new Tree(1);
		myTree.left = new Tree(2);
		myTree.right = new Tree(3);
		myTree.left.left = new Tree(4);
		myTree.left.right = new Tree(5);
		myTree.right.left = new Tree(6);
		myTree.right.right = new Tree(7);
		
		System.out.print("Queue-based method 3rd Level is ");
		printTreeLevel(myTree, 2);
		System.out.println("\n======");
		System.out.print("Recursion-based method 3rd Level is ");
		printTreeLevel(myTree, 0, 2);
		System.out.println("\n======");
	}
	// method based recursion, currentLevel will be 0, starting from the first level
	public static void printTreeLevel(Tree t, int currentLevel, int desire) {
		if (t == null || currentLevel > desire) {
			return;
		}
		if (currentLevel == desire) {
			System.out.print(t.value + " ");
		}
		else {
			// tree node and currentLevel increase simultaneously
			printTreeLevel(t.left, currentLevel+1, desire);
			printTreeLevel(t.right, currentLevel+1, desire);
		}
	}
	
	// method based two queues, iteration
	public static void printTreeLevel(Tree t, int desire){
		// first test if level desired is < 0, then invalid
		if (desire < 0) {
			return;
		}
		// Queue is an interface, is implemented by LinkedList
		Queue<Tree> trees = new LinkedList<Tree>();
		Queue<Integer> levels = new LinkedList<Integer>();
		// starting by pushing root node into queue 
		trees.add(t);
		levels.add(0);
		// the whole "trees" queue is empty or not
		while ( !trees.isEmpty() ) {
/*
The remove() and poll() methods remove and return the head of the queue.
The two methods differ in, if queue is empty, remove() throws an exception, while poll() returns null

poll() this method is very crucial here, because we need to return null and remove the head of queue at the same time.
we keep adding "tree node" and "level" into "trees" and "levels" two queues, respectively. 
If condition (currentLevel == desire) is not met, poll() and add another pair of right and left tree node, 
also "level" is changing correspondingly. 
use currentLevel to control so that to meet desireLevel
*/
			Tree temp = trees.poll();
			int currentLevel = levels.poll();
			/*
			each time poll() a tree node, test it if null or not. Tree node itself is Tree Class, it is different from
			"the whole tree queue is null or not"
			*/
			if (temp == null) {
				continue; // if poll() tree node is null, print nothing.
			}
			else if (currentLevel == desire) {
				System.out.print(temp.value + " ");
			}
			else {
				trees.add(temp.left);
				levels.add(currentLevel + 1);
				trees.add(temp.right);
				levels.add(currentLevel + 1);
			}
		}
	}
}