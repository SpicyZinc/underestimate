/**
insertion sort

idea: 
from the 2nd element, which is i=1, and move this index to end of the array,
while this i moves
temporarily save a[i] as tmp, compare each element before a[i] with a[i]
depends on ascending or descending order
choose tmp > a[j-1]
move a[j-1] to a[j]
last assign tmp to a[j] where the second for or while loop stops

*/

public class InsertionSort {
	public static void main(String[] args) {
		int[] a = {5,6,8,2,3,9,4,9};
		System.out.print("Before Insertion Sort\n");
		for(int i : a) {
			System.out.print( i + "  ");
		}
		System.out.println("\nAfter Insertion Sort");
		InsertionSort aTest = new InsertionSort();
		int[] b = aTest.insertion_sort(a);
		for(int k: b) {
			System.out.print( k + "  ");
		}
		System.out.print("\n");
		aTest.insertionSort(a);		
		for(int i : a) {
			System.out.print( i + "  ");
		}
	}
	// two for loop to insertion sort
	// outer loop starts from
	// descending order
	public int[] insertion_sort(int[] a) {
		for(int i=1; i<a.length; i++) {
			int tmp = a[i];
			int j = 0;
			for(j=i; j > 0 && tmp > a[j-1]; j--) {
				a[j] = a[j-1];
			}
			// because --, so j position is where tmp is
			a[j] = tmp;
		}
		return a;
	}
	// one for loop 
	// one while loop
	// ascending order
	public void insertionSort(int[] a) {
		for(int i=1; i<a.length; i++) {
			int tmp = a[i];
			int j = i;
			while(j > 0 && tmp < a[j-1]) {
				a[j] = a[j-1];
				j--;
			}
			// if ends out of while loop, that j is where I should put tmp
			// ideally or naively thinking a[0] = tmp;
			a[j] = tmp;
		}
		// return a;
	}
}