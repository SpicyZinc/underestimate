/*
Given a picture consisting of black and white pixels, find the number of black lonely pixels.
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example
Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]
Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:

The range of width and height of the input 2D array is [1,500].

idea:
get each row and each column's number B
loop again, if (both column and row B's count is 1) 
*/
public class LonelyPixel {
    public static void main(String[] args) {
    	LonelyPixel eg = new LonelyPixel();
    	char[][] picture = {
    		{'W', 'W', 'B'},
    		{'W', 'B', 'W'},
    		{'B', 'W', 'W'},
    	};
    	int result = eg.findLonelyPixel(picture);
    	System.out.println(result);
    }
    public int findLonelyPixel(char[][] picture) {
    	if (picture.length == 0 || picture == null) {
    		return 0;
    	}

    	int m = picture.length;
    	int n = picture[0].length;
    	int[] rowcnt = new int[m];
    	int[] colcnt = new int[n];
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (picture[i][j] == 'B') {
    				rowcnt[i]++;
    				colcnt[j]++;
    			}
    		}
    	}
    	int result = 0;
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (rowcnt[i] == 1 && colcnt[j] == 1 && picture[i][j] == 'B') {
    				result++;
    			}
    		}
    	}
    	return result;
    }
}