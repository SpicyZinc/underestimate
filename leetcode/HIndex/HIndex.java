/*
Given an array of citations (each citation is a non-negative integer) of a researcher, 
write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: 
"A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N - h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], 
which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, 
his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

idea:
1. based on the definition of HIndex, 3 papers have at least 3 citations.
Thus, for a array of n citations, hIndex can only be [0..n], and cannot exceed n, so open a new array. 
for the first pass, it counts all citations, and the second loop tries to find the possible hIndex.

2. direct thought
Array sort first, get Math.min(citations[i], n - i);
*/

public class Solution {
    // method 1
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] hs = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                hs[n]++;
            }
            else {
                hs[citations[i]]++;
            }
        }
        int papers = 0;
        // until here, citation as index, value is the how many of such citation
        // start from biggest citation
        for (int i = n; i >= 0; i--) {
            papers += hs[i];
            // "3 papers have at least 3 citations" translated into code, it should be as below
            if (papers >= i) {
                return i;
            }
        }

        return 0;
    }
    // method 2
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int maxCit = 0;
        for ( int i = 0; i < citations.length; i++ ) {
            int cit = Math.min(citations[i], citations.length - i);
            if ( cit >= maxCit ) {
                maxCit = cit;
                continue;
            }
            break;
        }
        return maxCit;
    }
}