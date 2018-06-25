/*
Given an directed graph, a topological order of the graph nodes is defined as follow:
For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

Notice
You can assume that there is at least one topological order in the graph.

Example
For graph as follow:
https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThE9AgZZszyhwe0o9qpp3VyizdIj9kWwMY50HiQEysXvkSLsoZ

The topological order can be:
[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]

idea:
https://songlee24.github.io/2015/05/07/topological-sorting/

DAG directed acyclic graph
Given a DAG, 图节点的拓扑排序被定义为
对于每条有向边A--> B，则A必须排在B之前　　
拓扑排序的第一个节点可以是任何在图中没有其他节点指向它的节点

BFS
queue 里只保存 indegree 为0的点
poll出indegree 为0的点, 它的neighbors indegree 都 minus 1
if 变成 0 了 就加入结果
*/

class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
};

public class TopologicalSorting {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
     	
     	Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        for (DirectedGraphNode vertex : graph) {
            for (DirectedGraphNode neighbor : vertex.neighbors) {
            	inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        // find in-degree 0 vertex, add to queue and result
        for (DirectedGraphNode vertex : graph) {
            if (!inDegree.containsKey(vertex)) {
                queue.offer(vertex);
            	result.add(vertex);
            }
        }
        
        while (!queue.isEmpty()) {
            DirectedGraphNode vertex = queue.poll();
            for (DirectedGraphNode neighbor : vertex.neighbors) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
				// 取入度为0的节点加入queue and result
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    result.add(neighbor);
                }
            }
        }
        
		return result;
	}
}