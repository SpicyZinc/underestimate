/*
Given an array of integers, return the 3rd Maximum Number in this array, if it doesn't exist, return the Maximum Number.
The time complexity must be O(n) or less.

idea:
1. first thought of getKth
2. find the first three maximum numbers
note: (num >= third) don't forget equal sign
try to avoid duplicate elements
			if (num == first || num == second) {
    	        continue;
    	    }
*/
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
    	int first = Integer.MIN_VALUE;
    	int second = Integer.MIN_VALUE;
    	int third = Integer.MIN_VALUE;
    	int cnt = 0;

    	for (int num : nums) {
    	    if (num == first || num == second) {
    	        continue;
    	    }
    		if (num > first) {
    			third = second;
    			second = first;
    			first = num;

    			cnt++;
    		}
    		else if (num > second) {
    			third = second;
    			second = num;

    			cnt++;
    		}
    		else if (num >= third) {
    			third = num;
    			cnt++;
    		}
    	}

    	if (cnt >= 3) {
    		return third;
    	}
    	else {
    		return first;
    	}
    }
}