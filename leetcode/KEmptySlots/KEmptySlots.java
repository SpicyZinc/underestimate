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
output which day 2 flowers blooming with k (empty slots) not-blooming flowers in between

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
        
        // flower blooming days
        // flower 1 blooms at which day
        // flower 2 blooms at which day
        // flower at position i + 1 blooms at days[i]
        int[] bloomDays = new int[n];
        
        for (int i = 0; i < n; i++) {
            bloomDays[flowers[i] - 1] = i + 1;
        }
        
        // maintain a window of size k, [left, ..k个..., right]
        int left = 0;
        int right = k + 1;
        
        int whichDay = Integer.MAX_VALUE;
        for (int i = 0; right < n; i++) {
            if (i == right) {
                // find a valid subarray, any i in between left and right bloom afters left and right
                whichDay = Math.min(whichDay, Math.max(bloomDays[left], bloomDays[i]));
            }
            // fail to satisfy, update
            // make sure no flowers bloom in between, if break the rule, update the window range
            if (bloomDays[left] > bloomDays[i] || bloomDays[i] <= bloomDays[right]) {
                // maintain k flowers between left and right
                left = i;
                right = i + k + 1;
            }
        }
        
        return whichDay == Integer.MAX_VALUE ? -1 : whichDay;
    }
}