/*
Given a binary tree and a sum, 
find ALL root-to-leaf paths where each path's sum equals the given sum.

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
idea:
typical DFS, 

add one to list
recursion
remove last one from list
*/

import java.util.*;

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	public TreeNode(int value) {
		this.value = value;
		left = null;
		right = null;
	}
}

public class PathSumII {
	public static void main(String[] args) {
		TreeNode aTreeRoot = new TreeNode(5);
		aTreeRoot.left = new TreeNode(4);
		aTreeRoot.right = new TreeNode(8);
		aTreeRoot.left.left = new TreeNode(11);
		aTreeRoot.right.left = new TreeNode(13);
		aTreeRoot.right.right = new TreeNode(4);
		aTreeRoot.left.left.left = new TreeNode(7);
		aTreeRoot.left.left.right = new TreeNode(2);
		aTreeRoot.right.right.left = new TreeNode(5);
		aTreeRoot.right.right.right = new TreeNode(1);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input a sum to find all root-to-leaf paths");
		int sum = scanner.nextInt();
		
		PathSumII eg = new PathSumII();
		ArrayList<ArrayList<Integer>> all = eg.pathSum(aTreeRoot, sum);
		for (int i=0; i<all.size(); i++) {
			System.out.print("[ ");
			for (Integer a : all.get(i)) {
				System.out.print(a + " ");
			}
			System.out.print("]\n");
		}
	}
    // recursion dfs
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void dfs(TreeNode node, int remaining, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null && remaining == node.val) {
            result.add(new ArrayList<Integer>(path));
        }
        dfs(node.left, remaining - node.val, path, result);
        dfs(node.right, remaining - node.val, path, result);
        path.remove(path.size() - 1);
    } 

	// iterative
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) return res;
        
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        Stack<Integer> accSums = new Stack<Integer>();
        LinkedList<TreeNode> path = new LinkedList<TreeNode>();
		
        nodes.add(root);
        accSums.add(root.value);
        
        while (!nodes.isEmpty()) {            
            TreeNode node = nodes.pop(); // pop to path by path.add()
            Integer accSum = accSums.pop(); 
            path.add(node);
            
            if (node.left == null && node.right == null) {
                // a root-to-leaf path
                if (accSum == sum) {
                    res.add(getPath(path));
                }
				// ArrayList.remove(int index)
				// Removes the element at the specified position in this list.
				// no matter find sumPath or not
				// both needs to empty the path for other round adding
                path.remove(path.size()-1); 
				// if not remove beforehand for once, the num of nodes in the list of "path" not matching
				// why path.peekLast().right != nodes.peek()??????				
				// I guess this corresponds to add right before adding left
				// completely empty because it is root-to-leaf
                while (!nodes.isEmpty() && !path.isEmpty() && path.peekLast().left != nodes.peek()) {
                    path.remove(path.size()-1);
                }
            }
			
			if (node.left != null) {
                nodes.add(node.left);
                accSums.add(accSum + node.left.value);
            }
			
            if (node.right != null) {
                nodes.add(node.right);
                accSums.add(accSum + node.right.value);
            }
        }

        return res;
    }
    
    public ArrayList<Integer> getPath(LinkedList<TreeNode> s) {
        ArrayList<Integer> onePath = new ArrayList<Integer>();
        for (TreeNode node : s) {
            onePath.add(node.value);
        }
        return onePath;
    }
}