/*
There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order. You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
A person can see another person to their right in the queue if everybody in between is shorter than both of them. More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.


Example 1:
https://assets.leetcode.com/uploads/2021/05/29/queue-plane.jpg
Input: heights = [10,6,8,5,11,9]
Output: [3,1,2,1,1,0]
Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.

Example 2:
Input: heights = [5,1,2,3,10]
Output: [4,1,1,1,0]

Constraints:
n == heights.length
1 <= n <= 10^5
1 <= heights[i] <= 10^5
All the values of heights are unique.

idea:
https://leetcode.com/problems/number-of-visible-people-in-a-queue/solutions/1897393/java-tc-sc-o-n-approach-solution-tracing-example/
need to come back
*/

class NumberOfVisiblePeopleInAQueue {
    // TLE
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        // Check for each pair of person
        for (int i = 0; i < n; i++) {
            int count = 0;
            int max = -1;
            for (int j = i + 1; j < n; j++) {
                // Count it if its height it maximum till now
                if (heights[j] > max) {
                    max = heights[j]; 
                    count++;
                }
                // Break if we have found someone taller than the current person we are checking for. Though we have counted this taller person
                if (heights[j] > heights[i]) {
                    // break early, no need to further
                    break;
                }
            }
            result[i] = count;
        }

        return result;
    }

    // with stack
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        // [10,6,8,5,11,9]
        // 9: [9] visibility = 0 []
        // 11: [] visibility = 1 [11]
        // 5: [11] visibility = 1 [5, 11]
        // 8: [11] visibility = 2 [8, 11]
        // 6: [8, 11] visibility = 1 [6, 8, 11]
        // 10: [6, 8, 11] visibility = 3 [10, 11]
        // stack keeps descending to ith inclusive so far
        for (int i = n - 1; i >= 0; i--) {
            int visibility = 0;
            // 不断pop 符合mono 递减数列规律
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                visibility++;
            }
            // always see neighbor
            if (!stack.isEmpty()) {
                visibility++;
            }
            stack.push(heights[i]);

            result[i] = visibility;
        }

        return result;
    }

    public int[] canSeePersonsCount(int[] heights) {
        int size = heights.length;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < size; i++) {
            hm.put(heights[i], i);
        }
        int[] visible = new int[size];
        for (int i = 0; i < size; i++) {
            visible[i] = getVisibleCount(i, size, heights, hm);
        }

        return visible;
    }

    public int getVisibleCount(int start, int size, int[] heights, Map<Integer, Integer> hm) {
        if (start == size - 1) {
            return 0;
        }

        int count = 1;
        int currentVisibleIdx = start + 1;
        int currentVisibleHeight = heights[currentVisibleIdx];
        
        while (currentVisibleIdx + 1 < size) {
            currentVisibleIdx++;
            if (heights[currentVisibleIdx] > currentVisibleHeight) {
                currentVisibleHeight = heights[currentVisibleIdx];
                count++;
            }
        }

        return count;
    }
}
