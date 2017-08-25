/*
Given an index k, return the kth row of the Pascal's triangle.
For example, given k = 3,
Return [1,3,3,1].

idea:
Remember pascal's triangle each row both ends are "1"

require space O(k), so use dp to save space.
left and righ ends are always "1"
two loops
for each row
from the behind to front
the result list both keep the preceding row and the current row

so we have to start from the behind to front
the one in the list gets overwritten to be current position in the list would not be used 

Take getting row==3 as an example
[2] = [2] + [1]
[1] = [1] + [0]
*/

import java.util.*;

public class PascalTriangleII {
	public static void main(String[] args) {
		PascalTriangleII aTest = new PascalTriangleII();
		int[] thirdRow = aTest.generateRowIndexTH(2);
		for (int i=0; i<thirdRow.length; i++) {
			System.out.print(thirdRow[i] + " ");
		}
		System.out.print("\n");
		int[] fifthRow = aTest.generateRowIndexTH(5);
		for (int i=0; i<fifthRow.length; i++) {
			System.out.print(fifthRow[i] + " ");
		}
		System.out.println();
	}
	// self written version passed test again
	public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return ret;
        }
        ret.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = ret.size() - 1; j>=1; j--) {
                ret.set( j, ret.get(j) + ret.get(j-1) );
            }
            
            ret.add(1);
        }
        
        return ret;
    }

	// self written version passed test
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<Integer>();  
	    if (rowIndex < 0) {
	        return res;
	    }
	    res.add(1);  
	    for (int i=1; i<=rowIndex; i++) {  
	        for (int j=res.size()-2; j>=0; j--) {  
	            res.set(j+1, res.get(j) + res.get(j+1));  
	        }  
	        res.add(1);  
	    }  
	    return res;
    }

    public int[] generateRowIndexTH(int rowIndex) {
		int[] res = new int[rowIndex + 1];
		// left end == 1
		res[0] = 1;
		// i is index of row
		// each i each row
		for (int i = 1; i < rowIndex + 1; i++) {
			// how to populate each row
			// populate current row with last row above current row
			// so it is i-1
			// at the same time res[j] is the penultimate element in the current row because i-1
			for (int j = i - 1; j > 0; j--) {
				res[j] = res[j] + res[j-1];
			}
			// right end == 1
			res[i] = 1;
		}

        return res;
    }
}
