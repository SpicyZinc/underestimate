/*
We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.
The answer can be returned in any order.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
Output: [7,4,1]
Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/06/28/sketch0.png

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 
Note:
The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

idea:
dfs() to get node -> parent relationship in hashmap
child (left, right)
parent

node == null
offer(null) why in order to count dist
用null作为mark 一个距离的mark
*/

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class AllNodesDistanceKInBinaryTree {
    // Thu May  2 02:29:47 2024
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> hm = new HashMap<>();
        dfs(root, null, hm);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        // 找和target 距离k的node 所以 以target为基准 先加入target
        queue.offer(null);
        queue.offer(target);

        visited.add(null);
        visited.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == k) {
                    // poll 到空
                    List<Integer> result = new ArrayList<>();
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        result.add(queue.poll().val);
                    }
                    return result;
                }
                dist++;
                // 之后的都是下一个同样的步
                queue.offer(null);
            } else {
                // add all distance +1 node to queue
                // which are left , right, and parent node
                
                if (!visited.contains(node.left)) {
                    queue.offer(node.left);
                    visited.add(node.left);
                }

                if (!visited.contains(node.right)) {
                    queue.offer(node.right);
                    visited.add(node.right);
                }

                TreeNode parent = hm.get(node);

                if (!visited.contains(parent)) {
                    queue.offer(parent);
                    visited.add(parent);
                }
            }
        }

        return new ArrayList<>();
    }

    public void dfs(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> hm) {
        if (node != null) {
            hm.put(node, parent);
            dfs(node.left, node, hm);
            dfs(node.right, node, hm);
        }
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> hm = new HashMap();
        dfs(root, null, hm);

        Queue<TreeNode> queue = new LinkedList();
        Set<TreeNode> seen = new HashSet();
        
        queue.add(null);
        queue.add(target);

        seen.add(null);
        seen.add(target);

        int dist = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> answer = new ArrayList();
                    for (TreeNode n : queue) {
                        answer.add(n.val);
                    }

                    return answer;
                }

                queue.offer(null);
                dist++;
            } else {
                // add left
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                // add right
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                // add parent
                TreeNode parent = hm.get(node);
                if (!seen.contains(parent)) {
                    seen.add(parent);
                    queue.offer(parent);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> hm) {
        if (node != null) {
            hm.put(node, parent);
            dfs(node.left, node, hm);
            dfs(node.right, node, hm);
        }
    }
}