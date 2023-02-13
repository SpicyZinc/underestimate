import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}


class RootToLeafSumList {
    public static void main(String[] args) {
        RootToLeafSumList eg = new RootToLeafSumList();
/* The above code constructs this tree
       30
     /    \
    10   50
    / \  / \
   3 16 40 60
*/
        Node root = new Node(30);
        root.left = new Node(10);
        root.right = new Node(50);
        root.left.left = new Node(3);
        root.left.right = new Node(16);
        root.right.left = new Node(40);
        root.right.right = new Node(60);

        List<Integer> result = eg.findPathSum(root);
        System.out.println(result);
    }

    // Function that finds the root to leaf
    public List<Integer> findPathSum(Node root) {
        List<Integer> pathSum = new ArrayList<>();
        dfs(root, 0, pathSum);

        return pathSum;
    }

    public void dfs(Node root, int sum, List<Integer> pathSum) {
        if (root == null) {
            return;
        }

        sum += root.val;

        // Store the path sum if node is leaf
        if (root.left == null && root.right == null) {
            pathSum.add(sum);
            return;
        }

        dfs(root.left, sum, pathSum);
        dfs(root.right, sum, pathSum);
    }
}
