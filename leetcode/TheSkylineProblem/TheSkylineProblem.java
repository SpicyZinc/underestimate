/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
https://leetcode.com/static/images/problemset/skyline1.jpg
write a program to output the skyline formed by these buildings collectively (Figure B).
https://leetcode.com/static/images/problemset/skyline2.jpg

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
A key point is the left endpoint of a horizontal line segment.
Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height.
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:
The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline.
For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

idea:
https://segmentfault.com/a/1190000003786782
https://codechen.blogspot.com/2015/06/leetcode-skyline-problem.html

1. convert rectangle [Li, Ri, Hi] into [Li, -Hi], [Ri, Hi], and save to a list
2. sort list based on x-axis, if x-axis is the same, sort based on y-axis
3. heap == priorityQueue(11, comparator), y-axis from big to small order
4. first add horizon 0 to priority queue
5. top left point into queue
6. top right point out queue 
7. only prev != cur, meaning a new key point, add to result list 
*/

import java.util.*;

public class TheSkylineProblem {
	public static void main(String[] args) {
		int[][] buildings = {
			{2, 9, 10},
			{3, 7, 15},
			{5, 12, 12},
			{15, 20, 10},
			{19, 24, 8}
		};

		TheSkylineProblem eg = new TheSkylineProblem();
		eg.getSkyline(buildings);
	}

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();

        for (int[] building : buildings) {
            heights.add(new int[] {building[0], -building[2]});
            heights.add(new int[] {building[1], building[2]});
        }

        Collections.sort(heights, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });

        for (int[] height : heights) {
        	System.out.println(Arrays.toString(height));
        }

        // heights priority queue, max at the top
        Queue<Integer> pq = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        // 先将地平线值0加入堆中
        // 为了[12 0] 这个点 在这个例子中
        int horizon = 0;
        int prevHighest = horizon;
        pq.offer(horizon);

        for (int[] height : heights) {
            int h = height[1];
            // 区分左右顶点 依靠[0,1] 0 negative, 1 positive
            if (h < 0) {
                pq.offer(-h);
            } else {
                pq.remove(h);
            }
            int currHighest = pq.peek();
            // 出现拐点了 要么更高的进来了 要么更高的出去了
            // 使用出去或者进来点的x value, 但是是当前最高高度
            if (prevHighest != currHighest) {
                result.add(new int[] {height[0], currHighest});
                prevHighest = currHighest;
            }
        }

        return result;
    }
}
