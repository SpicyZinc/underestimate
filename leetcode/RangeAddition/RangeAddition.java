/*
Assume you have an array of length n initialized with all 0's and are given k update operations.
Each operation is represented as a triplet: [startIndex, endIndex, inc]
which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:
Given: length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]
Output:
    [-2, 0, 3, 5, 3]

Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]
After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]
After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]
After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]

Hint:
Thinking of using advanced data structures? You are thinking it too complicated.
For each update operation, do you really need to update all elements between i and j?
Update only the first and end element is sufficient.
The optimal time complexity is O(k + n) and uses O(1) extra space.

idea:
follow the hint,
add increment at start position
subtract increment at (end + 1) position
then use a sum variable to record sum in array so far, and assign sum to each element
*/
import java.util.*;

class RangeAddition {
	public static void main(String[] args) {
		RangeAddition eg = new RangeAddition();
		int[][] updates = {
			{1, 3, 2},
			{2, 4, 3},
			{0, 2, -2}
		};

		int[] result = eg.getModifiedArray(5, updates);
		System.out.println(Arrays.toString(result));
	}

	public int[] getModifiedArray(int length, int[][] updates) {
		int[] result = new int[length];

		for (int[] update : updates) {
			int start = update[0];
			int end = update[1];
			int increment = update[2];

			result[start] += increment;
			if (end < length - 1) {
				result[end + 1] += -1 * increment;
			}
		}

		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += result[i];
			result[i] = sum;
		}

		return result;
	}
}