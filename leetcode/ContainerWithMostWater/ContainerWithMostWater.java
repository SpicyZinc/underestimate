/*
Given n non-negative integers a1, a2, ..., an, 
where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container.

idea: diagram			     			
				 |           
				 |        |                
|			     |        |                   
|        		 |        |        |        	
|        |		 |        |        |        			
|        |		 |        |        |        	
|        |		 |        |        |        |   			
|        |		 |        |        |        |   	
|        |		 |        |        |        |   	
---------------------------------------------

Our final goal is to find i, j, such that
A(i,j) = (j−i) * min(height(i), height(j)) is maximized. 
At every step, if height[i] < height[j], move i up by one. 
Otherwise move j down by one, until the two pointers meet.

两边搜索, 短板往里走. 因为往里走, 代表宽度减小, 那么宽度小的时候, 只有遇上更高的高度才能组成更加大的container.
*/

import java.util.*;

public class ContainerWithMostWater {
	public static void main(String[] args) {
		Random aRandom = new Random();
		ArrayList<Integer> height = new ArrayList<Integer>();
		
		for (int i=0; i < 10; i++) {
			height.add(aRandom.nextInt(100));
			System.out.println(height.get(i));
		}
		
		System.out.println("The max area is ");
		System.out.print(maxArea(height));
	}

	// self written version passed test 
	public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;

        while (i < j) {
			int area = (j - i) * Math.min( height[i], height[j] );
            maxArea = Math.max(maxArea, area);
            if (height[i] > height[j]) {
                j--;
            }
            else {
                i++;
            }
        }
		
        return maxArea;
    }
}