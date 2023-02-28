
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class NaryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;        
    }

    public void dfs(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.val);
            for (Node child : node.children) {
                dfs(child, result);
            }
        } else {
            return;
        }
    }

    // Iteration
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            result.add(curr.val);
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                stack.push(curr.children.get(i));
            }
        }

        return result;
    }
}
