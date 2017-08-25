/*
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:
Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:
Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:
How many MHTs can a graph have at most?

Note:
(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”
(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

idea:
https://buttercola.blogspot.com/2016/01/leetcode-minimum-height-trees.html

n nodes, n-1 edges, two pointers from both ends, depending on the parity of n
move two pointers together until there are 2 or 1 nodes left
applying this to this problem, starting from in-degree being 1 vertices,
until there are 2 or 1 nodes left
Note: and it also only 2 nodes left
Note: that for a tree we always have V = n, E = n-1.

http://blog.csdn.net/jiangbo1017/article/details/50112177
*/

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<Integer>();
        if (n <= 0) return result;
        // Corner case: there is a single node and no edge at all
        if (n == 1) {
            result.add(0);
            return result;
        }
        // construct the graph using adjancent list
        List<Set<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<Integer>());
        }
        // populate the graph
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        
        // find leaves with in-degree == 1
        List<Integer> leaves = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (adjList.get(i).size() == 1) leaves.add(i);
        }
        
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();
            for (int leaf : leaves) {
                int neighbor = adjList.get(leaf).iterator().next();
                // remove leaf nodes from neighbor
                adjList.get(neighbor).remove(leaf);
                // if neighbor become leave node, add to the new leaves
                if (adjList.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }

    // method 2
    // note: similar to BFS, queue.size() is changing, so have to save to a variable count first
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<Integer>();
        if (n <= 0) {
            return res;
        }
        if (n == 1 && edges.length == 0) {
            res.add(0);
            return res;
        }
        List<List<Integer>> map = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<Integer>());
        }
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0];
            int to = edge[1];
            map.get(from).add(to);
            map.get(to).add(from);
            degree[from]++;
            degree[to]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                return res;
            }
            else if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            res = new ArrayList<Integer>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int leaf = queue.poll();
                degree[leaf]--;
                res.add(leaf);
                List<Integer> adjList = map.get(leaf);
                for (int j = 0; j < adjList.size(); j++) {
                    int next = adjList.get(j);
                    if (--degree[next] == 1) {
                        queue.offer(next);
                    } 
                }
            }
        }

        return res;
    }
}
