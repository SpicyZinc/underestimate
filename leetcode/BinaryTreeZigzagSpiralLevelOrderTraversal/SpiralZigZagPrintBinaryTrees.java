import java.util.*;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int value) {
		value = this.value;
		left = right = null;
	}
}

public class SpiralZigZagPrintBinaryTrees {
	public static void main(String[] args) {
		TreeNode myTree = new TreeNode(1);
		myTree.left = new TreeNode(2);
		myTree.right = new TreeNode(3);
		myTree.left.left = new TreeNode(4);
		myTree.left.right = new TreeNode(5);
		myTree.right.left = new TreeNode(6);
		myTree.right.right = new TreeNode(7);
		
		System.out.print("Stack-based method 3rd Level is ");
		printSpiral(myTree, 2);
		System.out.println("\n======");

	}
	
	public static void printSpiral(TreeNode root, int desireLevel) {	
		if (desireLevel < 0) {
			return;
		}

		Stack<TreeNode> trees = new Stack<TreeNode>();
		Queue<Integer> levels = new LinkedList<Integer>();
		boolean left2right = false;
		
		trees.push(root);
		levels.add(0);
		
		while (!trees.empty()) {	
			TreeNode tmp = trees.pop();
			int currentLevel = levels.poll();
			
			if (tmp == null) {
				continue;
			}
			else if (currentLevel == desireLevel) {
				System.out.print(tmp.value + " ");
			}
			else if (left2right) {
				trees.push(tmp.left);
				levels.add(currentLevel+1);
				trees.push(tmp.right);
				levels.add(currentLevel+1);
				left2right = !left2right;
			}
			else {
				trees.push(tmp.right);
				levels.add(currentLevel+1);
				trees.push(tmp.left);
				levels.add(currentLevel+1);
				left2right = !left2right;
			}
		}
	}
}