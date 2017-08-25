import java.util.*;

public class Reverse {
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6};
		int[] b = {1,2,3,4,5,6,7};
		Reverse aTest = new Reverse();
		aTest.reverse(a, 0, a.length-1);
		aTest.reverse(b, 0, b.length-1);
		for (int i : a) {
			System.out.print(i + "  ");
		}
		System.out.print("\n");
		for (int j : b) {
			System.out.print(j + "  ");
		}
	}
	
	private void reverse(int[] num, int start, int end) {
		int mid = (start + end) / 2;
		// while (start <= mid) {    this one works
		while (start <= end) {
			int tmp = num[start];
			num[start] = num[end];
			num[end] = tmp;
			start++;
			end--;
		}
	}
	// self written version 
	public void reverse(int[] a, int s, int e) {
		int mid = (s + e) / 2;
		while ( s <= mid ) {
			int temp = a[s];
			a[s] = a[e];
			a[e] = temp;
			s++;
			e--;
		}
	}
}

