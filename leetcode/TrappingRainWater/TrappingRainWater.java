/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
[0,1,0,2,1,0,1,3,2,1,2,1]

idea:
calculate one trap by one, in order to do that, need to know the min height around current trap

i is holding water only if its smaller than min of right max and the left max.
for one A[i], the maximum water trapped depends on i之前最高的值leftMostHeight[i]和在i右边的最高的值rightMostHeight[i] (exclusively)
if min(left, right) > A[i], 那么在i这个位置上能trapped的water amount == min(right, left) - A[i]
note:
finally, container[i] == minHeightValue( right, left )
loop three times

there is 3rd solution to scan from both ends, like Minimum Window Substring
http://blog.csdn.net/linhuanmars/article/details/20888505
*/
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int res = 0;
        int max = 0;
        int[] minHeightValues = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            minHeightValues[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        for (int i = height.length - 1; i >= 0 ; i--) {
            minHeightValues[i] = Math.min(max, minHeightValues[i]);
            max = Math.max(max, height[i]);
        }
        for (int i = 0; i < height.length; i++) {
            if ( minHeightValues[i] > height[i] ) {
                res += ( minHeightValues[i] - height[i] );
            }
        }
        
        return res;
    }
}