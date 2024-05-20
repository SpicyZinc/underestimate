/*
You are given the root of a binary tree and an integer distance.
A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
Return the number of good leaf node pairs in the tree.

Example 1:
https://assets.leetcode.com/uploads/2020/07/09/e1.jpg
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
https://assets.leetcode.com/uploads/2020/07/09/e2.jpg
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Constraints:
The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10

idea:
dfs to find all leaves, and meanwhile populate the leaf -> path from root to leaf
then brute force to get distance of every two leaves, get result
*/

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class NumberOfGoodLeafNodesPairs {
    public int countPairs(TreeNode root, int distance) {
        // leaf node -> trail from root to leaf
        Map<TreeNode, List<TreeNode>> hm = new HashMap<>();
        List<TreeNode> leaves = new ArrayList<>();
        dfs(root, new ArrayList<>(), leaves, hm);

        int count = 0;
        int size = leaves.size();
        // count the distance of each leaf node pairs
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                TreeNode leafOne = leaves.get(i);
                TreeNode leafTwo = leaves.get(j);

                List<TreeNode> pathToOne = hm.get(leafOne);
                List<TreeNode> pathToTwo = hm.get(leafTwo);
                for (int k = 0; k < Math.min(pathToOne.size(), pathToTwo.size()); k++) {
                    // Same node in the trail means common ancestor
                    // find the node which is not equal, 就是开始分叉了 是时候计算 distance
                    if (pathToOne.get(k) != pathToTwo.get(k)) {
                        // 总长 - k 就是 distance
                        int dist = pathToOne.size() - k + pathToTwo.size() - k;
                        if (dist <= distance) {
                            count++;
                        }

                        break;
                    }
                }
            }
        }

        return count;
    }

    // DFS find all the leaf nodes.
    private void dfs(TreeNode node, List<TreeNode> trail, List<TreeNode> leaves, Map<TreeNode, List<TreeNode>> hm) {
        if (node == null) return;
        List<TreeNode> path = new ArrayList<>(trail);
        path.add(node);

        if (node.left == null && node.right == null) {
            hm.put(node, path);
            leaves.add(node);
            return;
        }

        dfs(node.left, path, leaves, hm);
        dfs(node.right, path, leaves, hm);
    }

// ==============================
    int count = 0;
    public int countPairs(TreeNode root, int distance) {
        Distance(root,distance);
            return count;
    }
    public List<Integer> Distance(TreeNode root,int distance){
        if(root==null)
            return new ArrayList<Integer>();
        if(root.left==null&&root.right==null){
            List<Integer> sublist=new ArrayList<Integer>();
            sublist.add(1);
            return sublist;
        }
        List<Integer> l1=Distance(root.left,distance);
        List<Integer> l2=Distance(root.right,distance);
        //System.out.println("left "+l1);
        //System.out.println("right "+l2);
        for (int d1:l1){
            for (int d2:l2){
                if(d1+d2<=distance)
                    count++;
            }
        }
        List<Integer> list=new ArrayList<Integer>();
        for (int val:l1)
            list.add(val+1);
        for (int val:l2)
            list.add(val+1);
        return list;
    }
}