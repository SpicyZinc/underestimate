/*
Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left.
Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)

Note: The number of boxes n would not exceed 100.

idea:
http://www.lydxlx.net/2017/03/26/first-post/
3D dp
Divider and Conquer, memorization

mem[l][r][k] represents to the left, right, k same after right, k + 1 same color
*/

public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int size = boxes.length;
        int[][][] mem = new int[size][size][size];
        return dfs(boxes, mem, 0, size - 1, 0);
    }
    
    public int dfs(int[] boxes, int[][][] mem, int l, int r, int k) {
        if (l > r) return 0;
        if (mem[l][r][k] > 0) return mem[l][r][k]; 
        // 2 3 ... 1 3 3 3
        // first case, find how many after r is equal to value at r
        while (l < r && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        mem[l][r][k] = dfs(boxes, mem, l, r - 1, 0) + (k + 1) * (k + 1);
        // second case
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                // remove boxes between i + 1 and r - 1 inclusively, gain
                int midPart = dfs(boxes, mem, i + 1, r - 1, 0);
                // merge the rest
                int merged = dfs(boxes, mem, l, i, k + 1); // r, k => i = r - 1, so k + 1
                mem[l][r][k] = Math.max(mem[l][r][k], midPart + merged);
            }
        }
        return mem[l][r][k];
    }
}