/*
Serialization is the process of converting a data structure or object into a sequence of bits
so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary search tree can be serialized to a string
and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states.
Your serialize and deserialize algorithms should be stateless.
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class SerializeAndDeserializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);

        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
        	sb.append("& ");
        }
        else {
        	sb.append(root.val + " ");
        	serialize(root.left, sb);
        	serialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data == null || data.length() == 0) {
    		return null;
    	}

    	StringTokenizer st = new StringTokenizer(data, " ");

    	return deserialize(st);
    }

    public TreeNode deserialize(StringTokenizer st) {
    	if (!st.hasMoreTokens()) {
    		return null;
    	}
    	String val = st.nextToken();
    	if (val.equals("&")) {
    		return null;
    	}

    	TreeNode root = new TreeNode(Integer.parseInt(val));
    	root.left = deserialize(st);
    	root.right = deserialize(st);

    	return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));