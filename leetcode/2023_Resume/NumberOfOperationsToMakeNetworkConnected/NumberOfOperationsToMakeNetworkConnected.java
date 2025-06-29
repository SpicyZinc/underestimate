/*
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi.
Any computer can reach any other computer directly or indirectly through the network.
You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

Example 1:
https://assets.leetcode.com/uploads/2020/01/02/sample_1_1677.png
Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.

Example 2:
https://assets.leetcode.com/uploads/2020/01/02/sample_2_1677.png
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2

Example 3:
Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.


Constraints:
1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.

idea:
dfs to find the standalone components using visited,
the # of components - 1 will be the answer
*/

class NumberOfOperationsToMakeNetworkConnected {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] c : connections) {
            int u = c[0];
            int v = c[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        int loneComponents = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) {
            loneComponents += dfs(v, graph, visited);
        }

        return loneComponents - 1;
    }

    // Find the number of components not connected to any other components
    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u]) {
            return 0;
        }

        visited[u] = true;
        for (int v : graph[u]) {
            dfs(v, graph, visited);
        }

        return 1;
    }
}
