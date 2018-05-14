/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

idea:

1. recursion
count(0) = 1
count(1) = 1
count(2) = 2
count(3) = 5

A1, A2, A3, Ak, ... , An (n nodes), any node can be root
Take Ak as root, a general case
left sub tree = k-1 nodes (since it is k, so 'k - 1' the count of nodes left), 
right sub tree = n-k nodes
total = (k-1) + (n-k) = n - 1 nodes

2. DP
outer loop: 1 to n (inclusive) n nodes, use i to loop through
inner loop: total nodes is i - 1, left == j, right == i - 1 - j
*/

public class UniqueBinarySearchTrees {
    // recursive
    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        else {
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                cnt += numTrees(i - 1) * numTrees(n - i);
            }

            return cnt;
        }
    }
    // DP
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            // j < i because one node has to be root
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }
}