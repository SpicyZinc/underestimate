/*
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.

Example 1:
Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.

Example 2:
Input: arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
Output: []

Constraints:
1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000

idea:
因为都是sorted 所以谁最小 就 advance pointer 有可能 三个都advance 或是两个 或者一个
三个都是的要加入result
注意不是 if else

another method, for element in arr1
binary search in arr2 and arr3
*/

class IntersectionOfThreeSortedArrays {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        List<Integer> result = new ArrayList<>();

        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            int a = arr1[i];
            int b = arr2[j];
            int c = arr3[k];

            int min = Math.min(a, Math.min(b, c));


            if (a == min) {
                i++;
            }
            if (b == min) {
                j++;
            }
            if (c == min) {
                k++;
            }
            if (a == min && b == min && c == min) {
                result.add(min);
            }
        }

        return result;
    }
}