/*
Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
idea:
Remember pascal's triangle each row both ends are "1"
numbers in between are sum of numbers at position of (i, i+1) in the preceding line
use the previous line to get the current line

*/

import java.util.*;

public class PascalTriangle {
	public static void main(String[] args) {
		PascalTriangle aTest = new PascalTriangle();
		ArrayList<ArrayList<Integer>> aTriangle = aTest.generateTriangle(5);
		for (int i=0; i<5; i++) {
			ArrayList<Integer> aRow = aTriangle.get(i);
			for (int j=0; j<aRow.size(); j++) {
				System.out.print(aRow.get(j) + " ");
			}
			System.out.println();
		}
	}
    public ArrayList<ArrayList<Integer>> generateTriangle(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            if (i > 0) {
                for (int j = 0; j < res.get(i-1).size()-1; j++) {
                    temp.add( res.get(i-1).get(j) + res.get(i-1).get(j+1) );
                }
            	temp.add(1);
            }
            
            res.add(temp);
        }

        return res;
    }

    // self written version passed test
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (numRows == 0) {
            return ret;
        }
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            
            if (i > 0) {
                ArrayList<Integer> preceding = ret.get(i-1);
                for ( int j = 0; j < preceding.size() - 1; j++ ) {
                    temp.add( preceding.get(j) + preceding.get(j+1) );
                }
                temp.add(1);
            }
            
            ret.add(temp);
        }
        
        return ret;
    }
}
