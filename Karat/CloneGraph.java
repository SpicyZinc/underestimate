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