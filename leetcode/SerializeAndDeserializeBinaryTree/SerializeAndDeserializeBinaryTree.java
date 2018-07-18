/*
Serialization is the process of converting a data structure or object into a sequence of bits 
so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree
    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

idea:
http://yuanhsh.iteye.com/blog/2171113
NOTE: splitter MUST be appended after TreeNode value or # 
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public void print() {
    	if (this != null) {
    		System.out.print(this.val + " ");
    		if ( this.left != null ) {
    			this.left.print();
    		}
    		if ( this.right != null ) {
    			this.right.print();
    		}
    	}
    }
}

public class SerializeAndDeserializeBinaryTree {
	public static void main(String[] args) {
		SerializeAndDeserializeBinaryTree eg = new SerializeAndDeserializeBinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		System.out.println("The tree looks like:");
		root.print();
		System.out.println();

		System.out.println("serialize the tree");
		String serializeResult = eg.serialize(root);
		System.out.println(serializeResult);

		TreeNode t = eg.deserialize(serializeResult);
		System.out.println("deserialize the tree");
		t.print();
		System.out.println();
	}

    public static final String splitter = ",";
    public static final String NULL = "#";
    // method 1
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {  
        StringBuilder sb = new StringBuilder();  
        serialize(root, sb);  
        return sb.toString();  
    }  
       
    private void serialize(TreeNode x, StringBuilder sb) {
        if (x == null) {  
            sb.append(NULL).append(splitter);
        } else {
            sb.append(x.val).append(splitter);
            serialize(x.left, sb);  
            serialize(x.right, sb);  
        }  
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ( data == null || data.length() == 0 ) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(data, splitter);
        return deserialize(st);
    }
    private TreeNode deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;  
        }
        String val = st.nextToken();  
        if (val.equals(NULL)) {
            return null;  
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));  
        root.left = deserialize(st);  
        root.right = deserialize(st);  
        return root;  
    }
	// method 2
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    public void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL).append(splitter);
        }
        else {
            sb.append(node.val).append(splitter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(splitter)));
        return buildTree(nodes);
    }
    
    public TreeNode buildTree(List<String> nodes) {
        String val = nodes.remove(0);
        if (val.equals(NULL)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);
            return root;
        }
    }
}
