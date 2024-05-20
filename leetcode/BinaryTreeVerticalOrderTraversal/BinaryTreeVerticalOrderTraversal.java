/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.

Examples 1:
Input: [3,9,20,null,null,15,7]
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:
[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:
Input: [3,9,8,4,0,1,7]
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]

Examples 3:
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

idea:
same as VerticalOrderTraversalOfABinaryTree

hashmap
value is a list of TreeNode
key is column 序号 i, left is i - 1, right i + 1
min and max is the range [min, max]
要么就是拿出 keySet() 来排序

就是 key为column number
value 为 list of TreeNode的 map
*/

class Pair {
    int idx;
    TreeNode node;

    public Pair(int idx, TreeNode node) {
        this.idx = idx;
        this.node = node;
    }
}

class BinaryTreeVerticalOrderTraversal {
    // Tue May 14 21:05:33 2024
    // note, if use Pair, have to use <>
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> hm = new HashMap<>();

        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair(0, root));

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> current = queue.poll();
            int idx = current.getKey();
            TreeNode node = current.getValue();
            hm.computeIfAbsent(idx, x -> new ArrayList<>()).add(node.val);

            min = Math.min(min, idx);
            max = Math.max(max, idx);

            if (node.left != null) {
                queue.offer(new Pair(idx - 1, node.left));
            }

            if (node.right != null) {
                queue.offer(new Pair(idx + 1, node.right));
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(hm.get(i));
        }
        return result;
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> hm = new HashMap<>();

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> columns = new LinkedList<>();

        queue.add(root);
        columns.add(0);

        int min = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer col = columns.poll();

            hm.computeIfAbsent(col, x -> new ArrayList<>()).add(node.val);
            
            if (node.left != null) {
                queue.add(node.left);
                columns.add(col - 1);
                // update range's min
                min = Math.min(min, col - 1);
            }
            
            if (node.right != null) {
                queue.add(node.right);
                columns.add(col + 1);
                // update range's max
                max = Math.max(max, col + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(hm.get(i));
        }

        return result;
    }
}
