/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 10^4
Absolute value of elements in the array and x will not exceed 10^4

idea:
sort the array based on the distance to the target and grab the top k elements
List.subList(0, k); k is not inclusive

binary
*/

public class FindKClosestElements {
    // Sat May 18 20:39:54 2024
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;

        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i] = new int[] {i, Math.abs(arr[i] - x)};
        }

        Arrays.sort(a, (p, q) -> p[1] - q[1]);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(arr[a[i][0]]);
        }
        Collections.sort(list);

        return list;
    }
    // Thu May  2 18:21:45 2024
    // Best
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        // while (right - left + 1 > k) {
        while (right - left >= k) {
            int diff = Math.abs(arr[left] - x) - Math.abs(arr[right] - x);
            if (diff > 0) {
                left++;
            } else {
                right--;
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    // Approach:
    // Using binary search and a sliding window, find the mid where,
    // the integers between mid and mid + k is the k closest integers to x.

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // The sliding window is between 'mid' and 'mid' + k.
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // With mid on the left, we use x - arr[mid], while arr[mid + k] - x because it is on the right.
            // This is important!
            // Rather than using Math.abs(), we need the direction keep the x within the sliding window.
            // If the window is too far left, we shift the window to the right.
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else { // If the window is too far right, we shift the window to the left.
                right = mid;
            }
        }

        // Input all the k closest integers into the result.
        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int i : arr) {
            nums.add((Integer) i);
        }

        Collections.sort(nums, (a, b) -> a == b ? 0 : Math.abs(a - x) - Math.abs(b - x));

        nums = nums.subList(0, k);
        Collections.sort(nums);

        return nums;
    }
}