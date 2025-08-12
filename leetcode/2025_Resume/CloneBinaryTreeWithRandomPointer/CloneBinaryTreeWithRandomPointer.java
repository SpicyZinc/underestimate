 /**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class CloneBinaryTreeWithRandomPointer {
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> hm = new HashMap<>();
        return dfs(root, hm);
    }

    public NodeCopy dfs(Node node, Map<Node, NodeCopy> hm) {
        if (node == null) {
            return null;
        }
        if (hm.containsKey(node)) {
            return hm.get(node);
        }

        NodeCopy newNode = new NodeCopy(node.val);
        hm.put(node, newNode);

        newNode.left = dfs(node.left, hm);
        newNode.right = dfs(node.right, hm);
        newNode.random = dfs(node.random, hm);

        return newNode;
    }
}