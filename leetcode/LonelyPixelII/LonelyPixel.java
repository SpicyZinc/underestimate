/*
Given a picture consisting of black and white pixels, and a positive integer N,
find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                        
    [['W', 'B', 'W', 'B', 'B', 'W'],    
     ['W', 'B', 'W', 'B', 'B', 'W'],    
     ['W', 'B', 'W', 'B', 'B', 'W'],    
     ['W', 'W', 'B', 'W', 'B', 'W']] 
N = 3
Output: 6  
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).  
            0    1    2    3    4    5         column index                                            
    0    [['W', 'B', 'W', 'B', 'B', 'W'],    
    1     ['W', 'B', 'W', 'B', 'B', 'W'],    
    2     ['W', 'B', 'W', 'B', 'B', 'W'],    
    3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows that have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Note:
The range of width and height of the input 2D array is [1,200].

idea:
record each row and column how many 'B'
based on rules, for rows whose column' B == N, equal to the ith row
reset colcnt[j] = 0
https://www.cnblogs.com/grandyang/p/6754987.html
*/

import java.util.*;

public class LonelyPixel {
	public static void main(String[] args) {
		LonelyPixel eg = new LonelyPixel();
		char[][] picture = {
			{'W', 'B', 'W', 'B', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'B', 'W'},
			{'W', 'B', 'W', 'B', 'B', 'W'},
			{'W', 'W', 'B', 'W', 'B', 'W'},
		};
		int N = 3;
		int result = eg.findBlackPixel(picture, N);
		System.out.println("result == " + result);
	}
    public int findBlackPixel(char[][] picture, int N) {
    	if (picture.length == 0 || picture[0].length == 0) {
    		return 0;
    	}
    	int m = picture.length;
    	int n = picture[0].length;
    	int[] rowcnt = new int[m];
    	int[] colcnt = new int[n];
    	String[] rows = new String[m];
    	for (int i = 0; i < m; i++) {
    		rows[i] = new String(picture[i]);
    		for (int j = 0; j < n; j++) {
    			if (picture[i][j] == 'B') {
    				rowcnt[i]++;
    				colcnt[j]++;
    			}
    		}
    	}
    	int cnt = 0;
    	int k = 0;
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (rowcnt[i] == N && colcnt[j] == N) {
    				for (k = 0; k < m; k++) {
    					// the rows that have black pixel 
    					if (picture[k][j] == 'B') {
    						if (!rows[k].equals(rows[i])) {
    							break;
    						}
    					}
    				}
    				if (k == m) {
    					cnt += colcnt[j];
    					// set this column to be zero
    					colcnt[j] = 0;
    				}
    			}
    		}
    	}
    	return cnt;
    }
}