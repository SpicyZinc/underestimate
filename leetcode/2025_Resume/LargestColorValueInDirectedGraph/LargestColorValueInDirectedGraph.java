/*
There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k.
The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

Example 1:

Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Example 2:

Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.

Constraints:
n == colors.length
m == edges.length
1 <= n <= 10^5
0 <= m <= 10^5
colors consists of lowercase English letters.
0 <= aj, bj < n

idea:

*/

class LargestColorValueInDirectedGraph {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[] indeg = new int[n];
        
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            indeg[e[1]]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        // dp[i][c] = max count of color c at node i
        int[][] dp = new int[n][26];

        int result = 0;
        // Initialize roots for topological sort and dp
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
                int color = colors.charAt(i) - 'a';
                dp[i][color] = 1;
                result = Math.max(result, 1);
            }
        }

        int processed = 0;


        while (!q.isEmpty()) {
            int node = q.poll();
            processed++;
            for (int nei : graph[node]) {
                for (int c = 0; c < 26; c++) {
                    int add = (colors.charAt(nei) - 'a' == c) ? 1 : 0;
                    dp[nei][c] = Math.max(dp[nei][c], dp[node][c] + add);
                    result = Math.max(result, dp[nei][c]);
                }
                if (--indeg[nei] == 0) q.offer(nei);
            }
        }

        // If any cycle exists, not all nodes processed -> return -1
        return processed == n ? result : -1;
    }
}
