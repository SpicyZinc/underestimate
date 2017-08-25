/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

idea:
http://blog.csdn.net/fightforyourdream/article/details/17497883

Key to Solve This Problem
BFS

A queue is used to do breath first traversal.
a map is used to store the visited nodes. It is the map between original node and copied node.


*/


// Definition for undirected graph.
class UndirectedGraphNode {
    int label;
 	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}


public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
        	return null;
        }

        LinkedList<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        UndirectedGraphNode nodeClone = new UndirectedGraphNode(node.label);
        hm.put(node, nodeClone);
        q.add(node);

        while (!q.isEmpty()) {
        	UndirectedGraphNode current = q.remove();
        	UndirectedGraphNode currentCloned = hm.get(current);
        	List<UndirectedGraphNode> currentNeighbors = current.neighbors;

        	for (int i = 0; i < currentNeighbors.size(); i++) {
        		UndirectedGraphNode aNeighbor = currentNeighbors.get(i);
        		if (hm.containsKey(aNeighbor)) {
        			UndirectedGraphNode aNeighborCloned = hm.get(aNeighbor);
        			hm.put(aNeighbor, aNeighborCloned);

        			currentCloned.neighbors.add(aNeighborCloned);
        		}
        		else {
        			UndirectedGraphNode aNeighborClone = new UndirectedGraphNode(aNeighbor.label);
        			hm.put(aNeighbor, aNeighborClone);
        			q.add(aNeighbor);

        			currentCloned.neighbors.add(aNeighborClone);
        		}
        	}
        }
        
        return nodeClone;
    }
}

