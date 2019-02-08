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
	moving forward, use this last height + 1 as starting point to finish walking through the height array
*/
public class LargestRectangleInHistogram {
	// two nested looping ways
	// i, j = i; j < length
	// i, j = 0; j <= i; j++

	// 08/25/2018 passed OJ no errors
	// 01/26/2019 passed OJ no errors 530 ms
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			int minHeight = heights[i];
			for (int j = i; j >= 0; j--) {
				minHeight = Math.min(minHeight, heights[j]);
				int area = minHeight * (i - j + 1);
				maxArea = Math.max(maxArea, area);
			}
		}

		return maxArea;
	}
	// 01/26/2019 passed OJ no errors 989 ms
	// 开一个array 存放到i 为止最小的 height 包括i
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		int[] minHeights = new int[heights.length]; // to i inclusive, minimum Height
		for (int i = 0; i < heights.length; i++) {
			for (int j = i; j < heights.length; j++) {
				if (i == j) {
					minHeights[j] = heights[j];
				} else {
					minHeights[j] = heights[j] < minHeights[j - 1] ? heights[j] : minHeights[j - 1];
				}

				maxArea = Math.max(maxArea, minHeights[j] * (j - i + 1));
			}
		}

		return maxArea;
	}

	// passed OJ, so fast 3 ms
	// 是以质取胜还是以量取胜 高度连续增长序列可能很高Height需要很短 x 距离 或是 很长 x 距离 一般的Height
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int start = 0;
        int maxArea = 0;
        
        while (start < n) {
            int end = n - 1;
            for (int i = start + 1; i <= end; i++) {
                // find where the decreasing bar, set end to be bar before the decreasing point
                if (heights[i - 1] > heights[i]) {
                    end = i - 1;
                }
            }
            int minHeight = heights[end];
            for (int i = end; i >= 0; i--) {
                minHeight = Math.min(minHeight, heights[i]);
                int area = minHeight * (end - i + 1);
                maxArea = Math.max(maxArea, area);
            }
            start = end + 1;
        }
        
        return maxArea;
    }

    // 用单调递增 stack
    // 01/26/2019 17 ms
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0 || heights == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            int curr = i == heights.length ? 0 : heights[i]; 
            
            while (!stack.isEmpty() && heights[stack.peek()] >= curr) {
                // this is the height of the bar just kicked out
                int h = heights[stack.pop()];

                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int right = i - 1;
                int thisArea = h * (right - left + 1);

                maxArea = Math.max(maxArea, thisArea);
            }

            stack.push(i);
        }

        return maxArea;
    }
}