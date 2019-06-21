/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.

idea:
very similar to Remove Element leetcode

note: always save first element (index = 0) to result array

i for all elements in A array, starting from 1
j for unique elements in A array,  starting from 0

for example
1 1 1 2 3 =>
1 2 1 2 3 =>
1 2 3 2 3

j controls 1 2 3
i controls 1 1 1 2 3 
*/

class RemoveDuplicates {
	public static void main(String[] args) {
		int my_array1[] = {1, 1, 2, 3, 5, 6, 6, 10, 25, 100, 123, 123};
		int my_array3[] = {1, 1, 1, 1, 1};
		int my_array5[] = {1, 123, 123};

		RemoveDuplicates eg = new RemoveDuplicates();
		System.out.println("New size of array1: " + my_array1.length + " " + eg.removeDuplicates(my_array1));
		System.out.println("New size of array3: " + my_array3.length + " " + eg.removeDuplicates(my_array3));
		System.out.println("New size of array5: " + my_array5.length + " " + eg.removeDuplicates(my_array5));
	}

	// Tue Jun 18 23:07:43 2019
	public int removeDuplicates(int[] nums) {
		// 记住 这个方法里 j 是 index 
		// 最后 j + 1
		int j = 0;
		
		int size = nums.length;
		
		for (int i = 1; i < size; i++) {
			int prev = nums[i - 1];
			int curr = nums[i];
			
			if (prev != curr) {
				nums[++j] = curr;
			}
		}
		
		return j + 1;
	}
	// similar as above, just while()
	public int removeDuplicates(int[] nums) {
		if (nums.length < 2) {
			return nums.length;
		}

		int i = 1;
		int j = 0;
		
		while (i < nums.length) {
			if (nums[j] != nums[i]) {
				j++;
				// this is to make sure all unique values go to left front
				nums[j] = nums[i];
			}
			i++;
		}
		
		return j + 1;
	}

	// best method
	public int removeDuplicates(int[] nums) {
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != nums[j]) {
				// note j still starts from 1
				nums[++j] = nums[i];
			}
		}

		return j + 1;
	}
	// method 2
	public int removeDuplicatesTwo(int a[]) {
		int i, j = 0;
		System.out.printf("\nOLD : ");
		for (i = 0; i < a.length; i++) {
			System.out.printf("[%d] ", a[i]);
		}
		// Remove the duplicates
		for (i = 1; i < a.length; i++) {
			if (a[i] != a[j]) {
				j++;
				a[j] = a[i]; // Move it to the front
			}
		}
		System.out.printf("\nNEW : ");
		for (i = 0; i < j + 1; i++) {
			System.out.printf("[%d] ", a[i]);
		}
		System.out.printf("\n");

		// The new array size, j + 1
		return j + 1;
	}
}
