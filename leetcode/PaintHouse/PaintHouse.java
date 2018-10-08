/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example,
costs[0][0] is the cost of painting house 0 with color red;
costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

idea:
a typical dp problem

dp[i][j] == min cost to paint till (i + 1) the house with the color of j
reuse cost[i][j]
since there are only 3 colors, so 3 cases instead of for loop
paint the current house with a certain color,
the previous house must use min of two other colors' cost

*/

public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs != null && costs.length == 0) {
        	return 0;
        }

        for (int i = 1; i < costs.length; i++) {
        	// loop through color array
        	for (int j = 0; j < 3; j++) {
        		costs[i][j] += Math.min(costs[i - 1][(j + 1) % 3], costs[i - 1][(j + 2) % 3]);
        	}
            // costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            // costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            // costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
    }
}