/*
Serialization is the process of converting a data structure or object into a sequence of bits
so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree.
An N-ary tree is a rooted tree in which each node has no more than N children.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that an N-ary tree can be serialized to a string
and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree
https://leetcode.com/static/images/problemset/NaryTreeExample.png 
as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.

Note:
N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

idea:
dfs
encode size of children into the string
use , as a separator
*/

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class SerializeAndDeserializeNaryTree {
	// 12/04/2018
	private static final String delimiter = ",";
	// Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.val).append(delimiter);
        sb.append(node.children.size()).append(delimiter);
        
        for (Node child : node.children) {
            serialize(child, sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
		if (data.isEmpty()) {
			return null;
		}
        
        String[] matches = data.split(delimiter);
        Queue<String> queue = new LinkedList<>(Arrays.asList(matches));
        
        return deserialize(queue);
    }
    
    public Node deserialize(Queue<String> queue) {
        String val = queue.poll();
        Node root = new Node(Integer.parseInt(val), new ArrayList<Node>());
        
        val = queue.poll();
        int size = Integer.parseInt(val);
        
        for (int i = 0; i < size; i++) {
            // after poll(), actually new queue
            root.children.add(deserialize(queue));
        }
        
        return root;
    }

	public static final String delimiter = ",";

	// Encodes a tree to a single string.
	public String serialize(Node root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);

		return sb.toString();
	}

	public void buildString(Node node, StringBuilder sb) {
		if (node == null) {
			return;
		} else {
			sb.append(node.val).append(delimiter);
			sb.append(node.children.size()).append(delimiter);

			for (Node child : node.children) {
				buildString(child, sb);
			}
		}
	}

	// Decodes your encoded data to tree.
	public Node deserialize(String data) {
		if (data.isEmpty()) {
			return null;
		}

		String[] parts = data.split(delimiter);
		Queue<String> queue = new LinkedList<>(Arrays.asList(parts));

		return buildTree(queue);
	}

	public Node buildTree(Queue<String> queue) {
		String val = queue.poll();
		Node root = new Node(Integer.parseInt(val));
		int size = Integer.parseInt(queue.poll());

		root.children = new ArrayList<Node>();
		for (int i = 0; i < size; i++) {
			root.children.add(buildTree(queue));
		}

		return root;
	}
}

// Your SerializeAndDeserializeNaryTree object will be instantiated and called as such:
// SerializeAndDeserializeNaryTree codec = new SerializeAndDeserializeNaryTree();
// codec.deserialize(codec.serialize(root));