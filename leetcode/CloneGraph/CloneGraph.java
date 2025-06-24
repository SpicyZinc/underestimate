/*
Given the head of a graph, return a deep copy (clone) of the graph.
Each node in the graph contains a val (int) and a list (List[UndirectedGraphNode]) of its neighbors.
There is an edge between the given node and each of the nodes in its neighbors.

OJ's undirected graph serialization (so you can understand error output):
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 
Visually, the graph looks like the following:

	   1
	  / \
	 /   \
	0 --- 2
		 / \
		 \_/
Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer.
You don't need to understand the serialization to solve the problem.

idea:
需要记住的就是 一个 Node 的 neighbor 可以是 别的 Node 的 neighbor
所以必要重复 克隆 

http://blog.csdn.net/fightforyourdream/article/details/17497883

A queue is used to do breath first traversal (BFS).
a map is used to store the visited nodes.
It is the map between original node and copied node.

or dfs
*/

// Definition for undirected graph.
class Node {
	public int val;
	public List<Node> neighbors;

	public Node() {}

	public Node(int _val,List<Node> _neighbors) {
		val = _val;
		neighbors = _neighbors;
	}
}

public class CloneGraph {
	// Tue May 14 02:05:07 2024
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> hm = new HashMap<>();
        Node cloned = new Node(node.val);
        hm.put(node, cloned);

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            List<Node> neighborsCloned = new ArrayList<>();

            for (Node neighbor : current.neighbors) {
                Node clonedNeighbor = null;
                if (hm.containsKey(neighbor)) {
                    clonedNeighbor = hm.get(neighbor);
                } else {
                    queue.offer(neighbor);
                    clonedNeighbor = new Node(neighbor.val);
                }
                neighborsCloned.add(clonedNeighbor);

                hm.put(neighbor, clonedNeighbor);
            }

            Node currentCloned = hm.get(current);
            currentCloned.neighbors = neighborsCloned;
        }

        return cloned;
    }
    // Tue May 21 19:11:15 2024
    public Node cloneGraph(Node node) {
        Map<Node, Node> hm = new HashMap<>();
        return dfs(node, hm);
    }

    public Node dfs(Node node, Map<Node, Node> visited) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloned = new Node(node.val);
        visited.put(node, cloned);

        List<Node> neighbors = node.neighbors;
        List<Node> clonedNeighbors = new ArrayList<>();
        for (Node neighbor : neighbors) {
            clonedNeighbors.add(dfs(neighbor, visited));
        }
        cloned.neighbors = clonedNeighbors;

        return cloned;
    }

	// DFS easier
	// Sun Jun 16 00:58:57 2019
	public Node cloneGraph(Node node) {
		Map<Node, Node> hm = new HashMap<>();

		return dfsClone(node, hm);
	}

	public Node dfsClone(Node node, Map<Node, Node> hm) {
		if (node == null) {
			return null;
		}

		if (hm.containsKey(node)) {
			return hm.get(node);
		}

		Node cloned = new Node(node.val, new ArrayList<>());
		hm.put(node, cloned);

		for (Node neighbor : node.neighbors) {
			cloned.neighbors.add(dfsClone(neighbor, hm));
		}

		return cloned;
	}

	// Sun Jun  9 13:41:32 2019
	public Node cloneGraph(Node node) {
		if (node == null) {
			return node;
		}

		Map<Node, Node> hm = new HashMap<>();
		Node cloned = new Node(node.val, new ArrayList<>());
		hm.put(node, cloned);
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			List<Node> neighbors = current.neighbors;

			Node currentCloned = hm.get(current);
			List<Node> neighborsCloned = new ArrayList<>();

			for (Node neighbor : neighbors) {
				// note, where to offer(), matters
				if (!hm.containsKey(neighbor)) {
					queue.offer(neighbor);
				}

				Node neighborCloned = hm.getOrDefault(neighbor, new Node(neighbor.val));
				neighborsCloned.add(neighborCloned);
				hm.put(neighbor, neighborCloned);
			}
			// finish clone
			currentCloned.neighbors = neighborsCloned;
		}

		return cloned;
	}
}