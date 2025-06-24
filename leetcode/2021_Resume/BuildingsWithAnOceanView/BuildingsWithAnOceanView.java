/*
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions.
Formally, a building has an ocean view if all the buildings to its right have a smaller height.
Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

Example 1:
Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.

Example 2:
Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.

Example 3:
Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.

Example 4:
Input: heights = [2,2,2,2]
Output: [3]
Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.

Constraints:
1 <= heights.length <= 10^5
1 <= heights[i] <= 10^9

idea:
directly from end of array, keep the so far highest, > soFarHighest update soFarHighest and put index into the ocean view
*/
import java.util.*;

class BuildingsWithAnOceanView {
    public static void main(String[] args) {
        BuildingsWithAnOceanView eg = new BuildingsWithAnOceanView();
        int[] heights = {4,2,3,1};
        int[] result = eg.findBuildings(heights);
        for (int pos : result) {
            System.out.println(pos);
        }
    }

    public int[] findBuildings(int[] heights) {
        // 就是建一个 decreased 的stack
        // 从左边看过去 就是下降的 不是的POP出来 留下的都是能看到右边海的
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> list = new ArrayList<>();
        
        // Monotonically decreasing stack.
        Stack<Integer> stack = new Stack<>();  
        for (int current = n - 1; current >= 0; current--) {
            // If the building to the right is smaller, we can pop it.
            while (!stack.isEmpty() && heights[current] > heights[stack.peek()]) {
                stack.pop();
            }
            
            // If the stack is empty, it means there is no building to the right 
            // that can block the view of the current building.
            if (stack.isEmpty()) { 
                list.add(current);
            }
            
            // Push the current building in the stack.
            stack.push(current);
        }
 
        // Push elements from list to answer array in reverse order.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            answer[i] = list.get(list.size() - 1 - i);
        }
        
        return answer;
    }

    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int heightSoFar = heights[n - 1];
        List<Integer> list = new ArrayList<>();
        list.add(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            if (heights[i] > heightSoFar) {
                heightSoFar = heights[i];
                list.add(i);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(list.size() - i  - 1);
        }
        return result;
    }
    // Fri Apr  5 01:59:57 2024
    public int[] findBuildings(int[] heights) {
        List<Integer> oceanView = new ArrayList<>();
        int size = heights.length;
        int soFarHighest = heights[size - 1];
        oceanView.add(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            if (heights[i] > soFarHighest) {
                soFarHighest = heights[i];
                oceanView.add(i);
            }
        }
        int n = oceanView.size();
        int[] result = new int[n];
        // 就是倒过来
        for (int i = 0; i < n; i++) {
            result[i] = oceanView.get(n - 1 - i);
        }

        return result;
    }

    public int[] findBuildings(int[] heights) {
        int size = heights.length;
        List<Integer> oceanView = new ArrayList<>();
        int soFarHighest = heights[size - 1];
        oceanView.add(size - 1);

        for (int i = size - 1; i >= 1; i--) {
            if (heights[i - 1] > soFarHighest) {
                oceanView.add(i - 1);
                soFarHighest = heights[i - 1];
            }
        }

        int[] result = new int[oceanView.size()];
        for (int i = oceanView.size() - 1; i >= 0; i--) {
            result[oceanView.size() - 1 - i] = oceanView.get(i);
        }

        return result;
    }
}
