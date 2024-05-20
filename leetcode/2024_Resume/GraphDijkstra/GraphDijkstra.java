/*
function Dijkstra(Graph, source):

 create vertex set Q

 for each vertex v in Graph: // Initialization
 dist[v] ← INFINITY // Unknown distance from source to v
 prev[v] ← UNDEFINED // Previous node in optimal path from source
 add v to Q // All nodes initially in Q (unvisited nodes)

 dist[source] ← 0 // Distance from source to source

 while Q is not empty:
 u ← vertex in Q with min dist[u] // Node with the least distance
 // will be selected first
 remove u from Q

 for each neighbor v of u: // where v is still in Q.
 alt ← dist[u] + length(u, v)
 if alt < dist[v]: // A shorter path to v has been found
 dist[v] ← alt
 prev[v] ← u
return dist[], prev[]

*/

import java.util.*; 

class GraphDijkstra { 
    int dist[];
    Set<Integer> visited; 
    PriorityQueue<Node> pq; 
    int V; // Number of vertices 
    List<List<Node> > adjList; 

    // class constructor
    public GraphDijkstra(int V) { 
        this.V = V; 
        dist = new int[V];
        visited = new HashSet<Integer>(); 
        pq = new PriorityQueue<Node>(V, new Node()); 
    } 
   
    // Dijkstra's Algorithm implementation 
    public void dijkstra(List<List<Node> > adjList, int srcVertex)  { 
        this.adjList = adjList; 
   
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE; 
        }
   
        // first add source vertex to PriorityQueue 
        pq.add(new Node(srcVertex, 0)); 
   
        // Distance to the source from itself is 0 
        dist[srcVertex] = 0;

        while (visited.size() != V) {
            // u is removed from PriorityQueue and has min distance  
            int u = pq.remove().node;
            // add node to finalized list (visited)
            visited.add(u); 
            graphAdjacentNodes(u); 
        } 
    } 
    // this methods processes all neighbors of the just visited node 
    private void graphAdjacentNodes(int u) { 
        int edgeDistance = -1; 
        int newDistance = -1; 
   
        // process all neighboring nodes of u 
        for (int i = 0; i < adjList.get(u).size(); i++) { 
            Node v = adjList.get(u).get(i); 
   
            // proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
   
                // compare distances 
                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance; 
                }
   
                // Add the current vertex to the PriorityQueue 
                pq.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    }
}
