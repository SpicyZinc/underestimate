/*
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
凡是求最优解的, 一般都是走DP的路线
Define dp函数
D[i, n] = [i, n]之间最小的cut数, n为字符串长度
D[i] = min(D[j+1] + 1) (i <= j < n)

check [i, j]是否是回文 也是一个DP问题
define function
P[i][j] = true if [i, j] is palindrome

P[i][j] = (str[i] == str[j] && P[i+1][j-1])
OR
P[i][j] = (str[i] == str[j] && (j-i < 2));
*/
public class PalindromePartitioningMinCut {
	public int minCut(String s) {
        int length = s.length();
        int[] cuts = new int[length + 1];
        boolean[][] isPalindromeMatrix = new boolean[length][length];

        // length - 1 chars, length - 1 - 1 spaces, min cuts = length - 1 - 1
        for (int i = 0; i <= length; i++) {
            cuts[i] = length - i - 1;
        }
        
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalindromeMatrix[i + 1][j - 1])) {
                    isPalindromeMatrix[i][j] = true;
                    // since i, j is palindrome, 补割一刀
                    cuts[i] = Math.min(cuts[i], cuts[j + 1] + 1);
                }
            }
        }
        
        return cuts[0];
    }
}