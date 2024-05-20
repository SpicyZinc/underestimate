/*
You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).

You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.
Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
A node u is an ancestor of another node v if u can reach v via a set of edges.

Example 1:
https://assets.leetcode.com/uploads/2019/12/12/e1.png
Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
Explanation:
The above diagram represents the input graph.
- Nodes 0, 1, and 2 do not have any ancestors.
- Node 3 has two ancestors 0 and 1.
- Node 4 has two ancestors 0 and 2.
- Node 5 has three ancestors 0, 1, and 3.
- Node 6 has five ancestors 0, 1, 2, 3, and 4.
- Node 7 has four ancestors 0, 1, 2, and 3.

Example 2:
https://assets.leetcode.com/uploads/2019/12/12/e2.png
Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
Explanation:
The above diagram represents the input graph.
- Node 0 does not have any ancestor.
- Node 1 has one ancestor 0.
- Node 2 has two ancestors 0 and 1.
- Node 3 has three ancestors 0, 1, and 2.
- Node 4 has four ancestors 0, 1, 2, and 3.

Constraints:
1 <= n <= 1000
0 <= edges.length <= min(2000, n * (n - 1) / 2)
edges[i].length == 2
0 <= fromi, toi <= n - 1
fromi != toi
There are no duplicate edges.
The graph is directed and acyclic.

idea:
BFS
https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/solutions/1821991/java-python-3-2-codes-topological-sort-dfs-w-brief-explanation-and-comments/
*/

class AllAncestorsOfANodeInADirectedAcyclicGraph {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {        
        // Build graph, and compute in degree
        int[] inDegree = new int[n];
        Map<Integer, List<Integer>> parentToKids = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            parentToKids.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
            inDegree[to]++;
        }
        
        // 1. Use a list of sets to save ancestors and to avoid duplicates.
        // 2. Use a Queue to save 0-in-degree nodes as the starting nodes for topological sort.
        List<Set<Integer>> sets = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            sets.add(new HashSet<>());
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // BFS to their neighbors and decrease the in degrees, when reaching 0, add it into queue;
        // During this procedure, get direct parent, add all ancestors of direct parents' of each kid.
        // Start from the one without edges coming
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int kid : parentToKids.getOrDefault(parent, Arrays.asList())) {
                sets.get(kid).add(parent);
                sets.get(kid).addAll(sets.get(parent));
                if (--inDegree[kid] == 0) {
                    queue.offer(kid);
                }
            }
        }

        // Convert the list into required format.
        // Sort ancestors and put into return list.
        List<List<Integer>> result = new ArrayList<>();
        for (Set<Integer> set : sets) {
            result.add(new ArrayList<>(set));
            Collections.sort(result.get(result.size() - 1));
        }
        return result;
    }
}
