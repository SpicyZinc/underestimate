/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B
such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
Each node is an integer between 0 and graph.length - 1.
There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.

Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.

Note:
graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

idea:
http://www.cnblogs.com/grandyang/p/8519566.html
二分图, 就是可以将图中的所有顶点分成两个不相交的集合, 使得同一个集合的顶点不相连. not connected
使用两种颜色, 分别用1和-1来表示, 初始时每个顶点用0表示未染色
*/

class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int size = graph.length;
        int[] vertexColors = new int[size];

        for (int i = 0; i < size; i++) {
            // if not dyed before, dye vertex i
            if (vertexColors[i] == 0 && !isValidAfterDye(graph, i, 1, vertexColors)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidAfterDye(int[][] graph, int pos, int colorToDye, int[] vertexColors) {
        if (vertexColors[pos] != 0) {
            return vertexColors[pos] == colorToDye;
        }

        vertexColors[pos] = colorToDye;

        for (int connected : graph[pos]) {
            // connected two points dye to different colors
            if (!isValidAfterDye(graph, connected, -1 * colorToDye, vertexColors)) {
                return false;
            }
        }

        return true;
    }
}
