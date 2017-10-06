/*
There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
and also the number of flowers between them is k and these flowers are not blooming.
If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.

Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].

idea:

flowers[] means
day 1, position 1 bloom
day 2, position 5 bloom
day 3, position 9 bloom
day 4, position 5 bloom

based on flowers[], build another array
flower at position i + 1 blooms at days[i]
int[] days = new int[n];

just need to find a subarray days[left, left+1,..., left+k, right] which satisfies:
for any i = left+1,..., left+k, it must satisfy days[left] < days[i] && days[right] < days[i]
Then, the result is max(days[left], days[right]).

note: i reaches right, found out a valid subarray
*/

class KEmptySlots {
	public int kEmptySlots(int[] flowers, int k) {
    	int n = flowers.length;
    	// flower at position i + 1 blooms at days[i]
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
        	days[flowers[i] - 1] = i + 1;
        }

		int res = Integer.MAX_VALUE;
    	int left = 0;
    	int right = k + 1;

        for (int i = 0; right < days.length; i++) {
            // a valid subarray found, the condition is i reaches right
            if (i == right) {
                res = Math.min(res, Math.max(days[left], days[right]));
            }
            if (days[i] < days[left] || days[i] <= days[right]) {
                // maintain k flowers between left and right
                left = i;
                right = k + i + 1;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}