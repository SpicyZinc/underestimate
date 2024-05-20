/*
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

Example 1:
https://assets.leetcode.com/uploads/2020/07/19/sample_2_1897.png
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.

Example 2:
https://assets.leetcode.com/uploads/2020/07/19/sample_1_1897.png
Input: root = [1,null,2,null,3,4,null,5,null,6]
Output: 4

Example 3:
https://assets.leetcode.com/uploads/2020/07/19/sample_3_1897.png
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 7

Constraints:
The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 10^4].

idea:
path sum arcAcross
same as 1245. Tree Diameter
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class DiameterOfNAryTree {
    int diameter = 0;

    public int diameter(Node root) {
        dfs(root);
        return diameter;
    }
    // get longest distance from "root" to leaf
    int dfs(Node root) {
        int max1 = 0; // 当前节点下距离叶子节点最长的路径
        int max2 = 0; // 当前节点下距离叶子节点次长的路径

        for (Node child : root.children) {
            // 递归查看当前子节点到叶子节点的最长路径
            // 加 1 为当前节点到该子节点的距离
            int length = dfs(child) + 1;
            // 使用该距离更新最长路径即次长路径
            if (length >= max1) {
                max2 = max1;
                max1 = length;
            } else if (length >= max2) {
                max2 = length;
            }
        }
        // 最长和次长的两个路径之和为通过当前节点的最长路径
        // 用该长度更新全局最大长度
        diameter = Math.max(diameter, max1 + max2);

        return max1;
    }
}