/*
Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.

Example 1:
Input: arr = [1,0,2,3,0,4,5,0]
Output: [1,0,0,2,3,0,0,4]
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]

Example 2:
Input: arr = [1,2,3]
Output: [1,2,3]
Explanation: After calling your function, the input array is modified to: [1,2,3]
 

Constraints:
1 <= arr.length <= 104
0 <= arr[i] <= 9

idea:
i += 2
*/

class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        int size = arr.length;
        int[] nums = new int[size];
        int i = 0;
        int j = 0;
        
        while (j < size && i < size) {
            if (arr[i] == 0) {
                nums[j] = 0;
                if (j + 1 < size) {
                    nums[j + 1] =0;
                }
                j += 2;
            } else {
                nums[j] = arr[i];
                j++;
            }
            i++;
        }

        for (int k = 0; k < size; k++) {
            arr[k] = nums[k];
        }
    }
}
