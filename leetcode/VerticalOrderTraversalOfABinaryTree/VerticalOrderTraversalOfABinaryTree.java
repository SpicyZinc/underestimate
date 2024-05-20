/*
Given a binary tree, return the vertical order traversal of its nodes values.
For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

Example 1:
https://assets.leetcode.com/uploads/2019/01/31/1236_example_1.PNG
Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:
https://assets.leetcode.com/uploads/2019/01/31/tree2.png
Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 
Note:
The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.


idea:
issue with output order

check the layer, the node on higher level(close to root) goes first
if they also in the same level, order from small to large

要分层处理
int size = queue.size();
for ()
*/


// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class VerticalOrderTraversalOfABinaryTree {
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Map<Integer, List<Integer>> hm = new HashMap<>();

		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> columns = new LinkedList<>();

		queue.add(root);
		columns.add(0);

		int min = 0;
		int max = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			// 当前这一层 单独一个 hashmap
			Map<Integer, List<Integer>> tmp = new HashMap<>();

			for (int i = 0; i < size; i++) {

				TreeNode node = queue.poll();
				Integer col = columns.poll();

				tmp.computeIfAbsent(col, x -> new ArrayList<>()).add(node.val);
				
				if (node.left != null) {
					queue.add(node.left);
					columns.add(col - 1);

					min = Math.min(min, col - 1);
				}
				
				if (node.right != null) {
					queue.add(node.right);
					columns.add(col + 1);

					max = Math.max(max, col + 1);
				}
			}

			for (int key : tmp.keySet()) {
				if (!hm.containsKey(key)) {
					hm.put(key, new ArrayList<Integer>());
				}
				List<Integer> list = tmp.get(key);
				// 只是当前这一层的排序 其他之前排好的 不予理会
				// 没有什么特别原因要sort 就是排下序
				Collections.sort(list, (a, b) -> a - b);
				hm.get(key).addAll(list);
			}
		}

		for (int i = min; i <= max; i++) {
			result.add(hm.get(i));
		}

		return result;
	}

	class Node {
		int order;
		TreeNode node;

		public Node(int order, TreeNode node) {
			this.order = order;
			this.node = node;
		}
	}
	
	public List<List<Integer>> verticalTraversal(TreeNode root) {

		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Map<Integer, List<Integer>> tm = new TreeMap<>();

		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, root));

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			int order = node.order;
			TreeNode tn = node.node;

			tm.computeIfAbsent(order, x -> new ArrayList<>()).add(tn.val);

			if (tn.left != null) {
				queue.offer(new Node(order - 1, tn.left));
			}

			if (tn.right != null) {
				queue.offer(new Node(order + 1, tn.right));
			}
		}
		
		result.addAll(tm.values());

		return result;
	}
}