/*
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]

idea:
http://www.hrwhisper.me/leetcode-create-maximum-number/

this problem consists of 3 different problems
1. find k largest numbers from an array 
when the stack is not empty, and current integer in the array is greater than current stack peek(),
pop the stack, as long as the rest of the array can guarantee to provide enough integers to form an integer of k bits, relative order not change
(1) it is smaller than nums[i]
(2) stack is empty
(3) leftover in array enough to fill the stack to size k

2. from nums1 and nums2, pick the greater one to add to answer array of size k
3. helper function greater(), note: used twice
*/

public class CreateMaximumNumber {
	public static void main(String[] args) {
		CreateMaximumNumber eg = new CreateMaximumNumber();
		int[] nums = {1, 1, 1, 9};
		int k = 3;
		int[] results = eg.maxArray(nums, k);
		for (int i : results) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];
        // note: equal sign
        for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, result, 0)) {
                result = candidate;
            }
        }
        return result;
    }

    private int[] maxArray(int[] nums, int k) {
        int n = nums.length;
        int[] answers = new int[k];
        // j is the pointer in the maxKElementsArray
        for (int i = 0, j = 0; i < n; i++) {
            while (n - i + j > k && j > 0 && answers[j - 1] < nums[i]) {
                j--;
            }
            if (j < k) {
                answers[j] = nums[i];
                j++;
            }
        }

        return answers;
    }

    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, idx = 0; idx < k; idx++) {
            ans[idx] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return ans;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}

