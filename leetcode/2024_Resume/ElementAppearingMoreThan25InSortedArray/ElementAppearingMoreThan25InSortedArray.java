/*
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

Example 1:
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6

Example 2:
Input: arr = [1,1]
Output: 1

Constraints:
1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
*/

class ElementAppearingMoreThan25InSortedArray {
    public int findSpecialInteger(int[] arr) {
        int size = arr.length;

        int count = 1;
        for (int i = 0; i < size - 1; i++) {
            int current = arr[i];
            int next = arr[i + 1];

            if (current == next) {
                count++;
            } else {
                count = 1;
            }

            if (count > (size / 4)) {
                return current;
            }
        }

        return count == 1 ? arr[0] : count;
    }

    // Binary search
    public int findSpecialInteger(int[] arr) {
        int result = -1;
        int n = arr.length;
        if (n <= 2) return arr[0];

        int i = 0;

        while (i < n) {
            int lastIdx = binarySearch(arr[i], arr, i, n - 1);
            int count = lastIdx - i + 1;
            if (count > n / 4) {
                result = arr[i];
                break;
            }

            i = lastIdx + 1;
        }

        return result;
    }

    private int binarySearch(int target, int[] arr, int low, int high) {
        int lastIdx = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) {
                lastIdx = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return lastIdx;
    }
}