/*
Run-length encoding is a compression algorithm that allows for an integer array nums with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded.
Each encoded[i] = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the value that is repeated freqi times.

For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]].
Another way to read this is "three 1's followed by five 2's".
The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:

Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
Compress prodNums into a run-length encoded array and return it.
You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively. Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.

Return the product of encoded1 and encoded2.
Note: Compression should be done such that the run-length encoded array has the minimum possible length.

Example 1:
Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
Output: [[6,6]]
Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].

Example 2:
Input: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
Output: [[2,3],[6,1],[9,2]]
Explanation: encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].

Constraints:
1 <= encoded1.length, encoded2.length <= 105
encoded1[i].length == 2
encoded2[j].length == 2
1 <= vali, freqi <= 104 for each encoded1[i].
1 <= valj, freqj <= 104 for each encoded2[j].
The full arrays that encoded1 and encoded2 represent are the same length.

idea:
no direct brute force,
找出最短common 然后更新
注意同一个value的情况

space: (maxOf(M, N)
time: O(M + N)
*/
import java.util.*;

class ProductOfTwoRunLengthEncodedArrays {
    // Fri Mar 22 22:56:06 2024
    public static void main(String[] args) {
        ProductOfTwoRunLengthEncodedArrays eg = new ProductOfTwoRunLengthEncodedArrays();
        
        int[][] encoded1 = new int[][] {{1,3},{2,1},{3,2}};
        int[][] encoded2 = new int[][] {{2,3},{3,3}};
        List<List<Integer>> result = eg.findRLEArray(encoded1, encoded2);

        System.out.println(result);
    }
    // Mon May 20 02:46:36 2024
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int m = encoded1.length;
        int n = encoded2.length;

        while (i < m) {
            int[] first = encoded1[i];
            int[] second = encoded2[j];

            int firstVal = first[0];
            int firstCnt = first[1];
            int secondVal = second[0];
            int secondCnt = second[1];

            int size = Math.min(firstCnt, secondCnt);

            if (!result.isEmpty() && result.get(result.size() - 1).get(0) == firstVal * secondVal) {
                List<Integer> prev = result.get(result.size() - 1);
                int prevCount = prev.get(1);
                prev.set(1, prevCount + size);
                result.set(result.size() - 1, prev);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(firstVal * secondVal);
                list.add(size);

                result.add(list);
            }
            // note, 这里用地址[] 用变量是错的
            firstCnt -= size;
            secondCnt -= size;

            if (firstCnt == 0) {
                i++;
            }
            if (secondCnt == 0) {
                j++;
            }
        }

        return result;
    }

    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        
        while (i < encoded1.length) {
            int[] first = encoded1[i];
            int[] second = encoded2[j];

            int common = Math.min(first[1], second[1]);
            int mul = first[0] * second[0];
            // handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
            if (!result.isEmpty() && result.get(result.size() - 1).get(0) == mul) {
                List<Integer> prev = result.get(result.size() - 1);
                int prevFreq = prev.get(1);
                prev.set(1, prevFreq + common);
                result.set(result.size() - 1, prev);
            } else {
                List<Integer> current = new ArrayList<>();
                current.add(mul);
                current.add(common);
                result.add(current);
            }
            
            first[1] -= common;
            second[1] -= common;
            
            if (first[1] == 0) i++;
            if (second[1] == 0) j++;
        }

        return result;
    }
}

