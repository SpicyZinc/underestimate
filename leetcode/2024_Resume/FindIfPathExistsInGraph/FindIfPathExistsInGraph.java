/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
You want to determine if there is a valid path that exists from vertex source to vertex destination.
Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

Example 1:
https://assets.leetcode.com/uploads/2021/08/14/validpath-ex1.png
Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2

Example 2:
https://assets.leetcode.com/uploads/2021/08/14/validpath-ex2.png
Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.

Constraints:
1 <= n <= 2 * 10^5
0 <= edges.length <= 2 * 10^5
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.

idea:
build graph first by converting edges to Map
BFS or DFS
*/

class FindIfPathExistsInGraph {
    // Tue Apr 23 01:40:53 2024
    // 受TT 4/22 course schedule I dfs 启发
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        Set<Integer> visited = new HashSet<>();

        return canHavePath(graph, source, destination, visited);
    }

    public boolean canHavePath(int[][] graph, int node, int destination, Set<Integer> visited) {
        if (node == destination) {
            return true;
        }

        visited.add(node);
        int[] neighbors = graph[node];
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] == 1 && !visited.contains(i) && canHavePath(graph, i, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> hm = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            hm.computeIfAbsent(u, x -> new ArrayList<Integer>()).add(v);
            hm.computeIfAbsent(v, x -> new ArrayList<Integer>()).add(u);
        }

        Set<Integer> visited =  new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) {
                return true;
            }

            List<Integer> neighbors = hm.get(node);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            } else {
                return false;
            }
        }

        return false;
    }
}
