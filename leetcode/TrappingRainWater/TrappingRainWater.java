/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.
For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

idea:
Traverse every array element and find the highest bars on left and right sides. Take the smaller of two heights.
The difference between smaller height and height of current element is the amount of water that can be stored in this array element. 

calculate one element by one, in order to do that, need to know the min height to the left and right current trap
一个位置一个位置的考虑

trap i is holding water only if its smaller than min of left max and the right max.
for a height[i], the maximum water trapped depends on leftMostHeight[i] to the left of i
and rightMostHeight[i] to the right of i (exclusively)
if min(left, right) > height[i], so trapped water amount on i == min(right, left) - height[i]
note:
finally, container[i] == minHeightValue( right, left )
loop 3 times

there is 3rd solution to scan from both ends, like Minimum Window Substring
http://blog.csdn.net/linhuanmars/article/details/20888505
*/

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length < 3 || height == null) {
            return 0;
        }

        int n = height.length;
        int[] leftHighest = new int[n];
        int[] rightHighest = new int[n];

        int max = 0;
        // populate lefHighest
        for (int i = 0; i < n; i++) {
            leftHighest[i] = max;
            max = Math.max(max, height[i]);
        }

        max = 0;
        // populate rightHighest
        for (int i = n - 1; i >= 0; i--) {
            rightHighest[i] = max;
            max = Math.max(max, height[i]);
        }

        int maxTrapped = 0;
        for (int i = 0; i < n; i++) {
            if (Math.min(leftHighest[i], rightHighest[i]) - height[i] > 0) {
                maxTrapped += Math.min(leftHighest[i], rightHighest[i]) - height[i];
            }
        }

        return maxTrapped;
    }
}