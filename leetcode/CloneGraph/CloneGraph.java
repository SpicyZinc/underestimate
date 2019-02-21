/*
Given the head of a graph, return a deep copy (clone) of the graph.
Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors.
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
http://blog.csdn.net/fightforyourdream/article/details/17497883

A queue is used to do breath first traversal (BFS).
a map is used to store the visited nodes. It is the map between original node and copied node.
*/

// Definition for undirected graph.
class UndirectedGraphNode {
    int label;
 	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

public class CloneGraph {
	// 02/13/2019
	// DFS easier
	// assume label is unique, otherwise use UndirectedGraphNode as key
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfsClone(node, map);
    }

    private UndirectedGraphNode dfsClone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null) {
        	return null;
        }

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }

        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);

        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(dfsClone(neighbor, map));
        }

        return clone;
    }

	// 02/13/2019
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        
        Map<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
        hm.put(node, cloned);
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode currNode = queue.poll();
            UndirectedGraphNode clonedCurrNode = hm.get(currNode);
            List<UndirectedGraphNode> neighbors = currNode.neighbors;
            List<UndirectedGraphNode> clonedNeighbors = new ArrayList<>();

            for (UndirectedGraphNode neighbor : neighbors) {
                UndirectedGraphNode clonedNeighbor = null;
                
                if (hm.containsKey(neighbor)) {
                    clonedNeighbor = hm.get(neighbor);
                } else {
                    clonedNeighbor = new UndirectedGraphNode(neighbor.label);
                    queue.offer(neighbor);
                }
                
                clonedNeighbors.add(clonedNeighbor);
                hm.put(neighbor, clonedNeighbor);
            }
            
            clonedCurrNode.neighbors = clonedNeighbors;
        }
        
        return cloned;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
        	return null;
        }

        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        UndirectedGraphNode nodeClone = new UndirectedGraphNode(node.label);
        hm.put(node, nodeClone);
        q.add(node);

        while (!q.isEmpty()) {
        	UndirectedGraphNode current = q.poll();
        	UndirectedGraphNode currentCloned = hm.get(current);
        	List<UndirectedGraphNode> neighbors = current.neighbors;

        	for (UndirectedGraphNode neighbor : neighbors) {
                UndirectedGraphNode neighborCloned;
        		if (hm.containsKey(neighbor)) {
        			neighborCloned = hm.get(neighbor);
        		} else {
        			neighborCloned = new UndirectedGraphNode(neighbor.label);
        			q.add(neighbor);
        		}

                hm.put(neighbor, neighborCloned);
                currentCloned.neighbors.add(neighborCloned);
        	}
        }
        
        return nodeClone;
    }
}