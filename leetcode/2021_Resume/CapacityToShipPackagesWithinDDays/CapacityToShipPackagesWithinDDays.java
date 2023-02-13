/*
A conveyor belt has packages that must be shipped from one port to another within D days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.

Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:
Input: weights = [3,2,2,4,1,4], D = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:
Input: weights = [1,2,3,1,1], D = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

Constraints:
1 <= D <= weights.length <= 5 * 104
1 <= weights[i] <= 500

idea:
same to Allocate minimum Number of pages


*/

class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        if (weights.length < D) {
            return -1;
        }

        int weightsSum = 0;
        for (int weight : weights) {
            weightsSum += weight;
        }

        int left = 0;
        int right = weightsSum;
        // The least weight capacity of the ship
        int leastWeight = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            // 还可以更大
            if (isPossible(weights, D, mid)) {
                leastWeight = Math.min(mid, leastWeight);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return leastWeight;
    }

    public boolean isPossible(int[] weights, int D, int minWeight) {
        int days = 1;
        int weightsSoFar = 0;
        
        for (int weight : weights) {
            if (weight > minWeight) {
                return false;
            }

            if (weightsSoFar + weight > minWeight) {
                days++;
                weightsSoFar = weight;

                if (days > D) {
                    return false;
                }
            } else {
                weightsSoFar += weight;
            }
        }

        return true;
    }
}