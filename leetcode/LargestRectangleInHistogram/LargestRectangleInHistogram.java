/*
Largest Rectangle in Histogram
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram. 

idea:
http://fisherlei.blogspot.com/2012/12/leetcode-largest-rectangle-in-histogram.html
http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
http://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/

1. O(n*n): for each height, check all heights before it inclusive to get minHeight, then * (# heights) to get max area by comparing
this is a balance, to get maxArea
minHeight * (# of heights)
(1) minHeight is the maximum, # of heights does not matter much
(2) # of heights is the maximum, win by quantity. 

2. not fully based on the fact that maxArea only appears in consecutively increasing heights,
   but this way of finding a consecutive increasing span, will greatly decrease outside loop times
   otherwise step is 1, now step (start = end+1)
   while (start < height.length)
   
   In addition, each end, should check all heights before it, no matter if it is a consecutive increasing span or not

O(n): find last height whose next height will decrease in height
    check all heights before and including this last height
	moving forward, use this last height+1 as starting point to finish walking through the height array
*/
public class LargestRectangleInHistogram {
	// two nested looping ways
    // i, j = i; j < length
    // i, j = 0; j <= i; j++


	// 95 / 96 test cases passed
    public int largestRectangleArea(int[] height) {
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			int minHeight = height[i];			
			for (int j = i; j >= 0; j--) {
				minHeight = Math.min(height[j], minHeight);			
				int area = minHeight * (i - j + 1);
				maxArea = Math.max(maxArea, area);
			}
		}

        return maxArea;
    }
    // 94 / 96 test cases passed
    public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		int[] minHeights = new int[heights.length]; // to i inclusive, minimum Height
		for (int i = 0; i < heights.length; i++) {
			for (int j = i; j < heights.length; j++) {
				if (i == j) {
					minHeights[j] = heights[j];
				} else {
					minHeights[j] = (heights[j] < minHeights[j - 1]) ? heights[j] : minHeights[j - 1];
				}
				maxArea = Math.max(maxArea, minHeights[j] * (j - i + 1));
			}
		}

        return maxArea;
    }
    // failed [2,0,2]
    public int largestRectangleArea(int[] heights) {
    	Arrays.sort(heights);
        int maxArea = 0;
    	for (int i = heights.length - 1; i >= 0; i--) {
    		int height = heights[i];            
    		maxArea = Math.max(maxArea, height * ( heights.length - i));
    	}

    	return maxArea;
    }

	// passed OJ
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int start = 0;
        while (start < heights.length) {
            int end = heights.length - 1;
            for (int i = start + 1; i <= end; i++) {
                // find where the decreasing bar, set end to be bar before the decreasing point
                if ( heights[i - 1] > heights[i] ) {
                    end = i - 1;
                    break;
                }
            }
            int minHeight = heights[end];
            for (int i = end; i >= 0; i--) {
                minHeight = Math.min(minHeight, heights[i]);
                max = Math.max(max, minHeight * (end - i + 1));
            }
            start = end + 1;
        }

        return max;
    }
    // http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h = new int[heights.length + 1];
        h = Arrays.copyOf(heights, heights.length + 1);
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i);
                i++;
           	} else {
               	int index = stack.pop();
              	maxArea = Math.max(maxArea, h[index] * (stack.isEmpty() ? i : i - stack.peek() - 1));
           	}
        }

        return maxArea;
    }
}