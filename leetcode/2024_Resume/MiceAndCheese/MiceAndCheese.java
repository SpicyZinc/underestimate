/*
There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.

A point of the cheese with index i (0-indexed) is:
reward1[i] if the first mouse eats it.
reward2[i] if the second mouse eats it.
You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.

Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.

Example 1:
Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
Output: 15
Explanation: In this example, the first mouse eats the 2nd (0-indexed) and the 3rd types of cheese, and the second mouse eats the 0th and the 1st types of cheese.
The total points are 4 + 4 + 3 + 4 = 15.
It can be proven that 15 is the maximum total points that the mice can achieve.

Example 2:
Input: reward1 = [1,1], reward2 = [1,1], k = 2
Output: 2
Explanation: In this example, the first mouse eats the 0th (0-indexed) and 1st types of cheese, and the second mouse does not eat any cheese.
The total points are 1 + 1 = 2.
It can be proven that 2 is the maximum total points that the mice can achieve.

Constraints:
1 <= n == reward1.length == reward2.length <= 10^5
1 <= reward1[i], reward2[i] <= 1000
0 <= k <= n

idea:
The difference of rewards will tell us which cheese to feed to which mouse.
We will do reward1[i] - reward2[i]

If difference is positive means reward1[i] is greater so we will feed that index's cheese to the first mouse.
Else that index's cheese would be fed to the second mouse.

sort the data structure which holds the difference of rewards.

The first k cheese would be fed to the first mouse, then the rest of the cheese after that would be fed to the second mouse.
*/

// Fri Mar 22 00:04:56 2024
class MiceAndCheese {
    // Naive thought
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int size = reward1.length;
        int count = 0;
        int reward = 0;
        for (int i = 0; i < size; i++) {
            int r1 = reward1[i];
            int r2 = reward2[i];
            if (r1 > r2 && count < k) {
                count++;
                reward += r1;
            } else {
                reward += r2;
            }
        }
        return reward;
    }
    

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int size = reward1.length;
        int[][] difference = new int[size][2];
        // storing the indices along with the differences
        // because we'll need to fetch the reward using the indices
        // from the two given arrays
        for (int i = 0; i < size; i++) {
            int r1 = reward1[i];
            int r2 = reward2[i];
            difference[i] = new int[] {r1 - r2, i};
        }
        
        // sort on the basis of difference (in descending order)
        Arrays.sort(difference, (a, b) -> b[0] - a[0]);
        
        int reward = 0;
        // feed the first top 'k' rewarding cheese to the first mouse
        for (int i = 0; i < k; i++) {
            reward += reward1[difference[i][1]];
        }
        // feed the leftover to the second mouse
        for (int i = k; i < size; i++) {
            reward += reward2[difference[i][1]];
        }
        
        return reward;
    }
}
