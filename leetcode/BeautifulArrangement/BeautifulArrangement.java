/*
Suppose you have N integers from 1 to N.
We define a beautiful arrangement as an array that is constructed by these N numbers successfully
if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:
The number at the ith position is divisible by i.
i is divisible by the number at the ith position.

Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2

Explanation:
The first beautiful arrangement is [1, 2]:
Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:
Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

Note:
N is a positive integer and will not exceed 15.

idea:
https://massivealgorithms.blogspot.com/2017/02/leetcode-526-beautiful-arrangement.html

1--N inclusive, each position has N options
N * (N-1) * (N-2) * ... * 1
use visited to mark if one number is used before
for each position, for loop from 1 to N
how to implement it, dfs or backtracking
typical dfs in for loop and visited array to help
*/

public class BeautifulArrangement {
	public int countArrangement(int N) {
        int[] count = new int[1];
        boolean[] visited = new boolean[N];
        dfs(N, 0, count, visited);
        
        return count[0];
    }
    
    public void dfs(int N, int pos, int[] count, boolean[] visited) {
        // found a beautiful arrangement
        if (pos == N) {
            count[0]++;
            return;
        }
        
        for (int i = 0; i < N; i++) {
            boolean divisible = (i + 1) % (pos + 1) == 0 || (pos + 1) % (i + 1) == 0;
            if (!visited[i] && divisible) {
                visited[i] = true;
                dfs(N, pos + 1, count, visited);
                visited[i] = false;
            }
        }
    }
}