/*
The diameter of a tree is the number of edges in the longest path in that tree.
There is an undirected tree of n nodes labeled from 0 to n - 1. You are given a 2D array edges where edges.length == n - 1 and edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the tree.
Return the diameter of the tree.

Example 1:
https://assets.leetcode.com/uploads/2022/01/19/tree1.jpg
Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: The longest path of the tree is the path 1 - 0 - 2.

Example 2:
https://assets.leetcode.com/uploads/2022/01/19/tree2.jpg
Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.

Constraints:
n == edges.length + 1
1 <= n <= 104
0 <= ai, bi < n
ai != bi

idea:
DiameterOfBinaryTree 求一个到底的深度
+1 就是有那个深度那个点到出发点 一个距离
关键是构建 graph edge
还要一个 全局变量 max
*/

class TreeDiameter {
    int maxDiameter = 0;

    public int treeDiameter(int[][] edges) {
        if (edges.length == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], (k) -> new ArrayList<>()).add(e[1]);
            graph.computeIfAbsent(e[1], (k) -> new ArrayList<>()).add(e[0]);
        }
        
        depth(0, -1, graph);

        return maxDiameter;
    }
    
    public int depth(int node, int parent, Map<Integer, List<Integer>> graph) {
        // m1: 1st deepest depth
        int m1 = 0;
        // m2: 2nd deepest depth
        int m2 = 0;
        for (int child : graph.get(node)) {
            // Only one way from root node to child node, don't allow child node go to root node again!
            if (child != parent) {
                int d = depth(child, node, graph);
                if (d > m1) {
                    m2 = m1;
                    m1 = d;
                } else if (d > m2) {
                    m2 = d;
                }
            }
        }
        maxDiameter = Math.max(maxDiameter, m1 + m2);
        // this max depth for parent
        return m1 + 1;
    }
}
