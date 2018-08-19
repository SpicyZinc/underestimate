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
		PascalTriangle eg = new PascalTriangle();
		List<List<Integer>> aTriangle = eg.generate(5);
		for (List<Integer> layer : aTriangle) {
			System.out.println(layer);
		}
	}

	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        
        List<Integer> rowOne = new ArrayList<>();
        rowOne.add(1);
        result.add(rowOne);
        
        for (int r = 1; r < numRows; r++) {
            List<Integer> layer = new ArrayList<>();
            // 开头
            layer.add(1);
            // 中间
            List<Integer> previousLayer = result.get(r - 1);
            for (int i = 1; i < previousLayer.size(); i++) {
                int first = previousLayer.get(i - 1);
                int second = previousLayer.get(i);
                layer.add(first + second);
            }
            // 结尾
            layer.add(1);
            
            result.add(layer);
        }
        
        return result;
    }
}
