/*
Palindrome Partitioning II *** FINAL VERSION ***

Given a string s, 
partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example 
given s = "aab"

["aa", "b"]:		one cut
["a", a","b"]: 		two cuts

Return 1 since it is minimum cut(s).

idea:
http://blog.csdn.net/ljphhj/article/details/22573983
凡是求最优解的，一般都是走DP的路线.
首先求dp函数，
定义函数
D[i,n] = 区间[i,n]之间最小的cut数，n为字符串长度

a   b   a   b   b   b   a   b   b   a   b   a
                i                           n
如果现在求[i,n]之间的最优解？应该是多少？简单看一看，至少有下面一个解
a   b   a   b   b   b   a   b   b   a   b   a
                i               j   j+1     n

此时 D[i,n] = min(D[i, j] + D[j+1, n]) i <= j < n
这是个二维的函数，实际写代码时维护比较麻烦。所以要转换成一维DP。
如果每次，从i往右扫描，每找到一个回文就算一次DP的话，
就可以转换为
D[i] = min(D[j+1] + 1) (i <= j < n)
*** when found a palindrome

Define:
D[i] = [i, n]之间最小的cut数，n为字符串长度
有个转移函数之后，一个问题出现了, 就是如何判断[i, j]是否是回文?
每次都从i到j比较一遍?

这也是一个DP问题
define function
P[i][j] = true if [i, j] is palindrome

so
P[i][j] = (str[i] == str[j] && P[i+1][j-1])
OR
P[i][j] = (str[i] == str[j] && (j-i < 2));
*/
public class PalindromePartitioningMinCut {
	public int minCut(String s) {
        int length = s.length();
        int[] cuts = new int[length + 1];
        boolean[][] isPalindromeMatrix = new boolean[length][length];
        
        for (int i = length; i >= 0; i--) {
            cuts[i] = length - i;
        }
        
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                // both works
                // if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalindromeMatrix[i + 1][j - 1])) {
                if ( s.charAt(i) == s.charAt(j) && (j - i < 2) || (s.charAt(i) == s.charAt(j) && isPalindromeMatrix[i + 1][j - 1]) ) {
                    isPalindromeMatrix[i][j] = true;
                    cuts[i] = Math.min(cuts[i], cuts[j + 1] + 1);
                }
            }
        }
        
        return cuts[0] - 1;
    }
	
	private boolean isPalindrome(String s) {
		int len = s.length();
		int i = 0;
		
		while (i < len / 2) {
			if (s.charAt(i) != s.charAt(len - 1 - i)) {
				return false;
			}
			i++;
		}
		
		return true;
	}
}