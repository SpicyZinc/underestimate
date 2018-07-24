/*
This method is based on a sorted array which is ascending order.
idea:
the array must be sorted first
*/
import java.util.*;

class TwoSumToANumber {
	public static void main(String[] args){
		int[] a = {1,2,3,4,5,6,7,8,9,10,11};
		twoSum(a, 12);
	}
	
	private static void twoSum(int[] a, int aNumber) {
		int left = 0;
		int right = a.length - 1;

		while (left < right) {
			int val = a[left] + a[right];
			if (a[left] + a[right] > aNumber) {
				right--;
			} else if (a[left] + a[right] < aNumber) {
				left++;
			} else {
				System.out.println("a[" + a[left] + ", " + a[right] + "] == " + aNumber);
				left++;
				right--;
			}
		}
	}
}