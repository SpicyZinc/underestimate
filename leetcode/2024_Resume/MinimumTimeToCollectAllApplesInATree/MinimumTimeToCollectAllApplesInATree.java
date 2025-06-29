/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
You spend 1 second to walk over one edge of the tree.
Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

Example 1:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 2:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  

Example 3:
Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0

Constraints:
1 <= n <= 10^5
edges.length == n - 1
edges[i].length == 2
0 <= ai < bi <= n - 1
hasApple.length == n

idea:
dfs graph
哪里体现出最少时间了?
description is misleading, no sense of minimum time, just visit all nodes having apple, nodes without apple just time = 0;
*/

class MinimumTimeToCollectAllApplesInATree {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        boolean[] visited = new boolean[n];
        // Build graph
        // populate the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        return dfs(graph, hasApple, visited, 0);
    }

    public int dfs(List<List<Integer>> graph, List<Boolean> hasApple, boolean[] visited, int appleId) {
        int time = 0;
        visited[appleId] = true;

        List<Integer> neighbors = graph.get(appleId);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                time += dfs(graph, hasApple, visited, neighbor);
            }
        }
        // go back to root
        if (appleId == 0) {
            return time;
        }
        // 一来一回时间两倍
        time += (hasApple.get(appleId) || time > 0) ? 2 : 0;
        return time;
    }
}
