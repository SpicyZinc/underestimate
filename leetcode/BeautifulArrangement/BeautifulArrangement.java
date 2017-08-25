/*
Suppose you have N integers from 1 to N.
We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

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
*/

public class BeautifulArrangement {
    int count = 0;
    
    public int countArrangement(int N) {
        if (N == 0) {
            return 0;
        }
        helper(N, 1, new int[N + 1]);
        return count;
    }
    
    private void helper(int N, int pos, int[] visited) {
        if (pos > N) {
            count++;
            return;
        }
        
        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                visited[i] = 1;
                helper(N, pos + 1, visited);
                visited[i] = 0;
            }
        }
    }

    // self written
    int count = 0;

    public int countArrangement(int N) {
        if (N <= 1) {
            return N;
        }
        boolean[] visited = new boolean[N];
        helper(N, 0, visited);
        return count;
    }
    
    private void helper(int N, int pos, boolean[] visited) {
        if (pos == N) {
            count++;
        }
        for (int i = 0; i < N; i++) {
            if ( !visited[i] && ( (i+1) % (pos+1) == 0 || (pos+1) % (i+1) == 0 ) ) {
                visited[i] = true;
                helper(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }
}