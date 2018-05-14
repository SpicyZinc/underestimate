/*
Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string 
by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. 
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"
Return 3

idea:

http://blog.csdn.net/linhuanmars/article/details/23589057
http://blog.csdn.net/abcbc/article/details/8978146
http://stackoverflow.com/questions/20459262/distinct-subsequences-dp-explanation

1. DP
(1). 1D DP
(2). 2D DP is easy to understand
            S
        r a b b b i t
      1 1 1 1 1 1 1 1
    r 0 1 1 1 1 1 1 1
    a 0 0 1 1 1 1 1 1
T   b 0 0 0 1 2 3 3 3
    b 0 0 0 0 1 3 3 3
    i 0 0 0 0 0 0 3 3
    t 0 0 0 0 0 0 0 3

res[i][j] == S的前i个字符和T的前j个字符有多少个可行的序列
假设S的第i个字符 !== T的第j个字符, 那么就意味着res[i][j]的值跟res[i-1][j]是一样的, 前面该是多少还是多少, 第i个字符的加入也不会多出来任何可行结果.
如果S的第i个字符和T的第j个字符相同, 那么所有res[i-1][j-1]中满足的结果都会成为新的满足的序列, 当然res[i-1][j]的也仍是可行结果, 
所以res[i][j] = res[i-1][j-1] + res[i-1][j].
递推式应该是res[i][j]=(S[i]==T[j]?res[i-1][j-1]:0)+res[i][j].

算法进行两层循环, 时间复杂度是O(m*n), 

The function is f(i, j) = f(i - 1, j) + S[i] == T[j] ? f(i - 1, j - 1) : 0 

Where f(i, j) is the number of distinct sub-sequences for T[0:j] in S[0:i]. 
We can use O(T) space since the ith-iteration only depends on the (i-1)th iteration 
and when we calculate from f(i, j) to f(i, 0) for the ith iteration.

2. without DP
do not understand it at all
http://gongxuns.blogspot.com/2012/12/leetcodedistinct-subsequences.html

3. best tutorial
https://www.cnblogs.com/yuzhangcmu/p/4196373.html

若原字符串和子序列都为空时, 返回1, 因为空串也是空串的一个子序列
若原字符串不为空, 而子序列为空, 返回1, 因为空串也是任意字符串的一个子序列
若当原字符串为空, 子序列不为空时, 返回0, 因为非空字符串不能当空字符串的子序列
*/

public class DistinctSubsequences {
    // 1D DP
    public int numDistinct(String S, String T) {
        int tLen = T.length();
        int sLen = S.length();

        if (tLen == 0) {  
            return 1;  
        }  
        if (sLen == 0) {
            return 0;
        }  
        int[] dp = new int[tLen + 1];  
        dp[0] = 1;
        for (int i = 0; i < sLen; i++) {  
            for (int j = tLen - 1; j >= 0; j--) {
                dp[j+1] += S.charAt(i) == T.charAt(j) ? dp[j] : 0;
            }
        }

        return dp[tLen];
    }
    // 2D DP
    // dp[i][j] the number of distinct subsequences of first j characters in T in first i characters in S
    // dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0)
    // note, S.charAt(i - 1) == T.charAt(j - 1), because it is first i, j characters, T.charAt(i - 1) corresponding to first i
    public int numDistinct(String S, String T) {
        int sLen = S.length();
        int tLen = T.length();
 
        int[][] dp = new int[sLen + 1][tLen + 1];
        dp[0][0] = 1; // note, dp[0][0] has to be 1

        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                dp[i][j] = S.charAt(i - 1) == T.charAt(j - 1) ? dp[i - 1][j] + dp[i - 1][j - 1] : dp[i - 1][j];
            }
        }

        return dp[sLen][tLen];
    }

	// without DP
	public int numDistinct(String S, String T) {
        Map<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
        for (int i=0; i<T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.get(T.charAt(i)).add(i);
            }
			else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                map.put(T.charAt(i), temp);
            }
        }
        
        int[] res = new int[T.length() + 1];
        res[0] = 1;
        
        for (int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            if (map.containsKey(c)) {
                ArrayList<Integer> temp = map.get(c);
                int[] old = new int[temp.size()];
                for (int j=0; j<temp.size(); j++) {
					old[j] = res[temp.get(j)];
				}					
                for (int j=0; j<temp.size(); j++) {
					res[temp.get(j) + 1] += old[j];
				}					
            }
        }
		
        return res[T.length()];
    }

    public int numDistinct(String S, String T) {
        if (S == null && T != null) {
            return 0;
        }
        if (T == null) {
            return 1;
        }

        return dfs(S, T, 0, 0);
    }

    public int dfs(String S, String T, int indexS, int indexT) {
        int sLen = S.length();
        int tLen = T.length();

        // base case, T is empty
        if (indexT >= tLen) {
            return 1;
        }
        // S is empty but T is not empty
        if (indexS >= sLen) {
            return 0;
        }

        int count = 0;
        if (S.charAt(indexS) == T.charAt(indexT)) {
            count += dfs(S, T, indexS + 1, indexT + 1);
        }

        // can skip the first character in S
        count += dfs(S, T, indexS + 1, indexT);

        return count;
    }
}