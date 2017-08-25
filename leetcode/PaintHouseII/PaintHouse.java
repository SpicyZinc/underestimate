/*
There are a row of n houses, each house can be painted with one of the k colors.
The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2]is the cost of painting house 1 with color 2, and so on...
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?

idea:
https://segmentfault.com/a/1190000003903965

note: 不同颜色的最小值不是遍历所有不同颜色, 而是用min1和min2来记录之前房子的最小和第二小的花费
如果当前房子颜色和min1相同, 那么我们用min2对应的值计算, 反之我们用min1对应的值
The other test is to find the second max in the array
*/

public class PaintHouse {
    public int minCostII(int[][] costs) {
    	if (costs == null || costs.length == 0) {
    		return 0;
    	}

    	int prevSecMin = 0;
    	int prevMin = 0;
    	int prexIdx = -1;

    	for (int i = 0; i < costs.length; i++) {

	    	int currSecMin = Integer.MAX_VALUE;
	    	int currMin = Integer.MAX_VALUE;
	    	int currIdx = -1;
	    	// house i painted with different colors, min cost and second min cost
	    	// min cost use which color j
	    	for (int j = 0; j < costs[i].length; j++) {
	    		// all prev, no current
	    		// paint another house, if use the same color as previous house uses, use second min
	    		costs[i][j] = costs[i][j] + (j == prexIdx ? prevSecMin : prevMin);

	    		if (costs[i][j] < currMin) {
	    			currSecMin = currMin;
	    			currMin = costs[i][j];
	    			currIdx = j;
	    		}
	    		else if (costs[i][j] < currSecMin) {
	    			currSecMin = costs[i][j];
	    		}
	    	}
    		
			prevSecMin = currSecMin;
			prevMin = currMin;
			prexIdx = currIdx;
    	}

    	return prevMin;
    }
}