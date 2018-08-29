/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
idea:
http://blog.csdn.net/fightforyourdream/article/details/17497883
BFS

A queue is used to do breath first traversal.
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