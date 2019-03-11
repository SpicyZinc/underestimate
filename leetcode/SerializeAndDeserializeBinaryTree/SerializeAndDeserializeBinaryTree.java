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
NOTE: delimiter MUST be appended after TreeNode value or empty value # 

不用什么 StringTokenizer next()
用 StringBuilder, linkedlist remove()

当你看到这段代码时可能觉得没啥问题啊
不就是返回了一个ArrayList对象吗? 问题就出在这里. 这个ArrayList不是java.util包下的
而是java.util.Arrays.ArrayList
显然它是Arrays类自己定义的一个内部类! 这个内部类没有实现add(), remove()方法
而是直接使用它的父类AbstractList的相应方法.
而AbstractList中的add()和remove()是直接抛出java.lang.UnsupportedOperationException异常的
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
    		if (this.left != null) {
    			this.left.print();
    		}
    		if (this.right != null) {
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
	// 03/06/2019
	// ant service
	String empty = "#";
    String delimiter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        
        return sb.toString();
    }
    
    public void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(empty);
            sb.append(delimiter);
            return;
        } else {
            sb.append(node.val);
            sb.append(delimiter);
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<>();
        String[] matches = data.split(delimiter);
        nodes.addAll(Arrays.asList(matches));
        
        return deserialize(nodes);
    }
    
    public TreeNode deserialize(List<String> nodes) {
        String val = nodes.remove(0);
        if (val.equals(empty)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            
            return root;
        }
    }

    // 12/04/2018
    public static final String emptyValue = "#";
    public static final String delimiter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    public void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(emptyValue).append(delimiter);
            return;
        }
        
        sb.append(node.val).append(delimiter);
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<String>();
        
        String[] matches = data.split(delimiter);
        
        nodes.addAll(Arrays.asList(matches));
        return deserialize(nodes);
    }
    
    public TreeNode deserialize(List<String> list) {
        String val = list.remove(0);

        if (val.equals(emptyValue)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = deserialize(list);
            root.right = deserialize(list);

            return root;
        }
    }


    public static final String delimiter = ",";
    public static final String emptyValue = "#";

    // 07/19/2018
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    
    public void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(emptyValue).append(delimiter);
        } else {
            sb.append(node.val).append(delimiter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes = new LinkedList<String>();

        nodes.addAll(Arrays.asList(data.split(delimiter)));

        return buildTree(nodes);
    }
    
    public TreeNode buildTree(List<String> nodes) {
        String val = nodes.remove(0);
        if (val.equals(emptyValue)) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);

            return root;
        }
    }
    
    // method 2
    public String serialize(TreeNode root) {  
        StringBuilder sb = new StringBuilder();  
        serialize(root, sb);  
        return sb.toString();  
    }  
       
    private void serialize(TreeNode x, StringBuilder sb) {
        if (x == null) {  
            sb.append(emptyValue).append(delimiter);
        } else {
            sb.append(x.val).append(delimiter);
            serialize(x.left, sb);  
            serialize(x.right, sb);  
        }  
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ( data == null || data.length() == 0 ) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(data, delimiter);
        return deserialize(st);
    }
    private TreeNode deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;  
        }
        String val = st.nextToken();  
        if (val.equals(emptyValue)) {
            return null;  
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));  
        root.left = deserialize(st);  
        root.right = deserialize(st);  
        return root;  
    }
}
