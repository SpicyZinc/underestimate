/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h).
One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

idea:
extension of longest increase subsequence LIS
必须先sort
LIS input is 1D, Russian Doll input is 2D
nothing to say, just remember it, so simple
inclusive i

Comparator<Type> don't forget
*/

public class RussianDollEnvelopes {
    // 02/13/2019
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes == null) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });
        
        int n = envelopes.length;
        int[] dp = new int[n];
        int max = 1;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            int length = envelopes[i][0];
            int width = envelopes[i][1];

            for (int j = 0; j < i; j++) {
                int currLength = envelopes[j][0];
                int currWidth = envelopes[j][1];
                
                if (length > currLength && width > currWidth) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            max = Math.max(max, dp[i]);
        }

        return max;
    }


	public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0 || envelopes == null) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                } else {
                    return a[1] - b[1];
                }
            }
        });
        
        int max = Integer.MIN_VALUE;
        // dp[i] till i + 1 envelopes, how many (max) can Russian doll, but count at i position may not the max
        // when max, i could be 2 , 5, n - 2, so need a separate max value
        int[] dp = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}