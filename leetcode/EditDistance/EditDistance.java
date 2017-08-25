/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:
a) Insert a character
b) Delete a character
c) Replace a character

idea:
Edit Distance is also called levenshtein distance (Russian)
http://en.wikipedia.org/wiki/Levenshtein_distance

http://blog.unieagle.net/2012/09/19/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Aedit-distance%EF%BC%8C%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B9%8B%E9%97%B4%E7%9A%84%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB%EF%BC%8C%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
time requirement is O(mn)

note: length is length, [0, length-1]
sstr1(i) is word1's substring, its range is [0, i), length is i, sstr1(0) is empty string
sstr2(j) is word2's substring, its range is [0, j), length is j, sstr2(0) is empty string
d[i][j] is edit distance to convert sstr1(i) to sstr2(j)

dp[i][j] == the count of edits needed
to make word1[0, i-1] (first i chars) equals word2[0, j-1] (first j chars) 
*/
public class EditDistance {
	public static void main(String[] args) {
		EditDistance aTest = new EditDistance();
		System.out.print("Min of 1 2 3 == " + aTest.min(1, 2, 3));
		
	}
    // self written version
    public int minDistance(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();
        int[][] minDistance = new int[size1 + 1][size2 + 1];
        // initialization
        // the edit distance between the prefixes of word1 and an empty string
        for (int i = 0; i <= size1; i++) {
            minDistance[i][0] = i;
        }
        // the edit distance between the prefixes of word2 and an empty string
        for (int j = 0; j <= size2; j++) {
            minDistance[0][j] = j;
        }

        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);
                if (c1 == c2) {
                    minDistance[i][j] = minDistance[i-1][j-1];
                }
                else {
                    int insert = minDistance[i-1][j] + 1;
                    int replace = minDistance[i-1][j-1] + 1;
                    int remove = minDistance[i][j-1] + 1;
                    // min of insertion, deletion, replacement
                    int min = Math.min( Math.min(insert, replace), remove );
                    minDistance[i][j] = min;
                }
            }
        }

        return minDistance[size1][size2];
    }
}