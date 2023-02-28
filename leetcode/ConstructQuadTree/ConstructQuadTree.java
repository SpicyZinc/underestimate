/*
Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.

Return the root of the Quad-Tree representing the grid.

Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
isLeaf: True if the node is leaf node on the tree or False if the node has the four children.

We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.
https://assets.leetcode.com/uploads/2020/02/11/new_top.png

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:
The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

 

Example 1:
https://assets.leetcode.com/uploads/2020/02/11/grid1.png

Input: grid = [[0,1],[1,0]]
Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
Explanation: The explanation of this example is shown below:
Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
https://assets.leetcode.com/uploads/2020/02/12/e1tree.png

Example 2:
https://assets.leetcode.com/uploads/2020/02/12/e2mat.png

Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
The topLeft, bottomLeft and bottomRight each has the same value.
The topRight have different values so we divide it into 4 sub-grids where each has the same value.
Explanation is shown in the photo below:
https://assets.leetcode.com/uploads/2020/02/12/e2tree.png

Constraints:
n == grid.length == grid[i].length
n == 2x where 0 <= x <= 6

idea:
https://www.cnblogs.com/grandyang/p/9649348.html

*/

// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};

public class ConstructQuadTree {
    // Fri Feb 24 23:21:36 2023
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int grid[][], int r, int c, int length) {
        if (length == 1) {
            return new Node(grid[r][c] == 1, true);
        }

        int newLength = length / 2;

        Node topLeft = dfs(grid, r, c, newLength);
        Node topRight = dfs(grid, r, c + newLength, newLength);
        Node bottomLeft = dfs(grid, r + newLength, c, newLength);
        Node bottomRight = dfs(grid, r + newLength, c + newLength, newLength);

        if (topLeft.val == topRight.val && bottomLeft.val == bottomRight.val && topLeft.val == bottomLeft.val && topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
            return new Node(topLeft.val, true);
        } else {
            // here is "val" true or false does not matter, we dont know, and value is not all 1 nor all 0
            return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    // 1st time
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int[][] grid, int x, int y, int len) {
        if (len == 1) {
            return new Node(grid[x][y] == 1, true, null, null, null, null);
        }
        
        Node nodeTL = dfs(grid, x, y, len / 2);
        Node nodeTR = dfs(grid, x, y + len / 2, len / 2);
        Node nodeBL = dfs(grid, x + len / 2, y, len / 2);
        Node nodeBR = dfs(grid, x + len / 2, y + len / 2, len / 2);

        // Merge all child nodes
        if (nodeTL.isLeaf && nodeTR.isLeaf && nodeBL.isLeaf && nodeBR.isLeaf) {
            if (nodeTL.val && nodeTR.val && nodeBL.val && nodeBR.val) {
                return new Node(true, true);
            }

            if (!nodeTL.val && !nodeTR.val && !nodeBL.val && !nodeBR.val) {
                return new Node(false, true);
            }
        }

        return new Node(true, false, nodeTL, nodeTR, nodeBL, nodeBR);
    }
}
