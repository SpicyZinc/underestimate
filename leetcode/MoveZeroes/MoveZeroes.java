/*
Given an array nums, write a function to move all 0's to the end of it 
while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], 
after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

idea
Method 1:
like bubble sort, self implemented
swap any two adjacent elements until the zero reaches the end of array
because zero reaches to the end of array
next time, m--
repeat this the length of array times 

Method 2 (the most direct method)
move all nonzero elements to the front part of the array
then count the nonzero elements length, after that append 0s

Method 3
count zeroes
nums[i - zeroCounts] = nums[i] to move all non-zero element to the front of the array
and keep the relative order, then manually set nums[i] = 0;
*/


public class MoveZeroes {
	// 07/17/2018
	public void moveZeroes(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
	        for (int j = 0; j < nums.length - 1; j++) {
	            if (nums[j] == 0) {    
	                swap(nums, j, j + 1);
	            }
	        }
	    }
    }
	// method 1
    public void moveZeroes(int[] nums) {
    	int n = nums.length;
    	int k = 0;
    	for (int m = n - 1; m >= 0; m--) {
	    	for (int i = 0; i < n - 1; i++) {
			    k = i + 1;
			    if (nums[i] == 0) {
			        swap(nums, i, k);
			    }
			}
    	}
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    // method 2
    public void moveZeroes(int[] nums) { 
        int lastNonZeroPos = 0; 
        for (int i = 0; i < nums.length; i++) { 
            if (nums[i] != 0) { 
                nums[lastNonZeroPos++] = nums[i]; 
            } 
        } 
        while (lastNonZeroPos < nums.length) { 
            nums[lastNonZeroPos++] = 0; 
        }
    }
    // method 3
    public void moveZeroes(int[] nums) {
	    int zeroCounts = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (nums[i] == 0) {
	            zeroCounts++;
	        } else {
	            nums[i - zeroCounts] = nums[i];
	            if (zeroCounts != 0) {
	            	nums[i] = 0;
	            }
	        }
	    }
	}
	// method 3', even shorter
	public void moveZeroes(int[] nums) {
        int zeroCounts = 0; 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCounts++;
            } else if (nums[i] != 0 && zeroCounts != 0) {
                nums[i - zeroCounts] = nums[i];
                nums[i] = 0;    
            }
        }
    }
	// JavaScript
	var moveZeroes = function(nums) {
	    var length = nums.length;
	    for ( let i = 0; i < length; i++ ) {
	        if (nums[i] === 0) {
	            nums.splice(i, 1);
	            i--;
	        }
	    }
	    var currentLength = nums.length; // have to save to a variable, otherwise nums length keeps changing
	    for (let i = 0; i < length - currentLength; i++) {
	        nums.push( 0 );
	    }
	};
}