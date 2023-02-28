/*
Given an array of positive integers arr (not necessarily distinct), return the lexicographical largest permutation that is smaller than arr, that can be made with exactly one swap.
If it cannot be done, then return the same array.

Note that a swap exchanges the positions of two numbers arr[i] and arr[j]

Example 1:
Input: arr = [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.

Example 2:
Input: arr = [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.

Example 3:
Input: arr = [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.
 

Constraints:
1 <= arr.length <= 10^4
1 <= arr[i] <= 10^4

idea:
找到拐点 下降点 是下降前的那个最大点
然后 与之后的上升过程中的最大点互换

*/

class PreviousPermutationWithOneSwap {
    public int[] prevPermOpt1(int[] arr) {
        int size = arr.length;
        
        int turnPointIdx = -1;
        
        for (int i = size - 1; i >= 1; i--) {
            // 1st ascending point
            if (arr[i] < arr[i - 1]) {
                turnPointIdx = i - 1;
                break;
            }
        }
        
        // already sorted, so such turning point, return
        if (turnPointIdx == -1) {
            return arr;
        }
        
        int j = size - 1;
        for (int i = size - 1; i >= turnPointIdx; i--) {
            // if there is duplicate, find the leftmost one
            if (arr[turnPointIdx] > arr[i] && arr[i - 1] != arr[i]) {
                j = i;
                break;
            }
        }
        
        swap(arr, turnPointIdx, j);
        
        return arr;
    }
    
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
