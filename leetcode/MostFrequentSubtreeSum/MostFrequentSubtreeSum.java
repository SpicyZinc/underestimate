/*
Given the root of a tree, you are asked to find the most frequent subtree sum.
The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:
  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:
   5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

idea:
dfs + map
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class MostFrequentSubtreeSum {
    int maxFrequency = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        dfs(root, hm);

        List<Integer> list = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                list.add(entry.getKey());
            }
        }
        // convert arraylist to array
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    public int dfs(TreeNode node, Map<Integer, Integer> hm) {
        if (node == null) return 0;
        int leftSubTreeSum = node.left == null ? 0 : dfs(node.left, hm);
        int rightSubTreeSum = node.right == null ? 0 : dfs(node.right, hm);
        int sum = leftSubTreeSum + rightSubTreeSum + node.val;
        hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        maxFrequency = Math.max(maxFrequency, hm.get(sum));
        return sum;
    }
}