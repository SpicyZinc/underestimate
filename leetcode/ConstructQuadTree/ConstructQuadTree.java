/*
idea:
https://www.cnblogs.com/grandyang/p/9649348.html

need to go back
*/

// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
}

public class ConstructQuadTree {
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
                return new Node(true, true, null, null, null, null);
            }

            if (!nodeTL.val && !nodeTR.val && !nodeBL.val && !nodeBR.val) {
                return new Node(false, true, null, null, null, null);
            }
        }

        return new Node(true, false, nodeTL, nodeTR, nodeBL, nodeBR);
    }
}