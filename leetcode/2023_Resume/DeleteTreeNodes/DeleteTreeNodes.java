/*
A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the ith node is value[i];
The parent of the ith node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

Return the number of the remaining nodes in the tree.


Example 1:

Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2

Example 2:
Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
Output: 6

Constraints:
1 <= nodes <= 10^4
parent.length == nodes
0 <= parent[i] <= nodes - 1
parent[0] == -1 which indicates that 0 is the root.
value.length == nodes
-10^5 <= value[i] <= 10^5
The given input is guaranteed to represent a valid tree.

idea:
construct graph
dfs
*/
class DeleteTreeNodes {
    public int deleteTreeNodes(int n, int[] parent, int[] value) {
        // Create graph for the tree
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] != -1)
                graph.get(parent[i]).add(i);
        }

        return dfs(graph, value, 0)[1];
    }

    // return [sum, count of remain nodes after deletion]
    private int[] dfs(List<List<Integer>> graph, int[] value, int root) {
        int totalSum = value[root];
        int totalNodes = 1;
        for (int child : graph.get(root)) {
            int[] sumAndCountFromChild = dfs(graph, value, child);
            totalSum += sumAndCountFromChild[0];
            totalNodes += sumAndCountFromChild[1];
        }
        if (totalSum == 0) totalNodes = 0; // This subtree should be removed, so don't count nodes of this subtree

        return new int[] {totalSum, totalNodes};
    }
}