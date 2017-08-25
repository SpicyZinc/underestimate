/*
You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Return the array [2, 1, 1, 0].

idea:
1. Brute force
2. construct binary search tree

  1(0, 1)
   \
   6(3, 1)
   /
 2(0, 2)
     \
      3(0, 1)
*/

import java.util.*;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf eg = new CountOfSmallerNumbersAfterSelf();
        int[] nums = new int[] {5, 2, 6, 1};
        eg.countSmaller(nums);
    }
    // brute force
    // 15 / 16 test cases passed
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        if (nums.length == 0 || nums == null) {
            return list;
        }
        for (int i = 0; i < nums.length; i++) {
            list.add(countMin(nums, i));
        }
        return list;
    }

    public int countMin(int[] nums, int idx) {
        int cnt = 0;
        for (int i = idx + 1; i < nums.length; i++) {
            if (nums[i] < nums[idx]) {
                cnt++;
            }
        }
        return cnt;
    }
    // passed OJ 
    class Node {
        int val;
        int leftSmaller;
        int dup;
        Node left;
        Node right;

        public Node(int val, int leftSmaller) {
            this.val = val;
            this.leftSmaller = leftSmaller;
            this.dup = 1;
            this.left = null;
            this.right = null;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];

        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, nums[i], ans, i, 0);
        }

        return Arrays.asList(ans);
    }

    private Node insert(Node node, int num, Integer[] ans, int i, int preSum) {
        if (node == null) {
            node = new Node(num, 0);
            ans[i] = preSum;
        }
        else if (node.val == num) {
            node.dup++;
            ans[i] = preSum + node.leftSmaller;
        }
        else if (node.val > num) {
            node.leftSmaller++;
            node.left = insert(node.left, num, ans, i, preSum);
        }
        else {
            node.right = insert(node.right, num, ans, i, preSum + node.dup + node.leftSmaller);
        }

        return node;
    }

    // binary search method
    // maintain a sorted list which contains all visited number
    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int index = searchInsertPos(sorted, num);
            result[i] = index; // index is the number of smaller
            sorted.add(index, num);
        }
        return Arrays.asList(result);
    }
    
    public int searchInsertPos(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}
