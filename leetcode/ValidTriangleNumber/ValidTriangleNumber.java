/*
Given an array consists of non-negative integers,
your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

idea:
borrow 3 sum smaller
note: two short length sum > the longest length 
*/
public class ValidTriangleNumber {
	public int triangleNumber(int[] nums) {
		if (nums.length < 3 || nums == null) {
			return 0;
		}

		int cnt = 0;
		Arrays.sort(nums);
		for (int i = 2; i < nums.length; i++) {
			int left = 0;
			int right = i - 1;
			while (left < right) {
				if (nums[left] + nums[right] > nums[i]) {
					cnt += right - left; // left is varying, so change right to avoid duplicate
					right--;
				} else {
					left++;
				}
			}
		}

		return cnt;
	}

    // lesson, 21 / 220 test cases passed
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] > nums[k]) {
                    cnt += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return cnt;
    }
}