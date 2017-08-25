/*
Remove Element
Given an array and a value, remove all instances of that value in place and return the new length.

idea:

The order of elements can be changed. 
It doesn't matter what you leave beyond the new length

in place, I did not build another array
The thought is direct
go througth the array, whenever finding an element equals to the target(elem), skip it.
*/
public class RemoveElement {
	// the cleanest way 
	public int removeElement(int[] A, int elem) {
		int j = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != elem) {
				A[j++] = A[i];
			}
		}

		return j;
	}

	// common solution: direct and clear
	// return length with removing all "elements"
    public int removeElement(int[] A, int elem) {
        int i=0, j=0;
        while (j<A.length) {
            if (A[j] != elem) {
                A[i] = A[j];
                i++; 
            }
            j++;
        }
        return i;
    }
	// method 2
	// Count the number of unique elements
	public int countUnique(int[] A) {
		int count = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == A[i + 1]) {
				count++;
			}
		}
		return (A.length - count);
	}
    // method 3
    // return new array
	public int[] remove_0(int[] array, int element) {
		int i,j;
		for (i=j=0; j<array.length; j++) {
			if (array[j] != element) {
				array[i++] = array[j];
			}
		}
		
		return Arrays.copyOf(array, i);
	}
	// method 4 used an array of boolean as the same size of the original array
	// to record elements to be deleted as "true", and got the new size of newArray 	
	public int[] remove_1(int[] array, int element) {
		boolean[] toBeDeleted = new boolean[array.length];
		int newArray_size = 0;
		for (int i=0; i<array.length; i++) {
			if (array[i] == element) {
				toBeDeleted[i] = true;
			}
			else if (array[i] != element) {
				toBeDeleted[i] = false;
				newArray_size++;
			}
		}
		
		int[] newArray = new int[newArray_size];
		int index = 0;
		for (int i=0; i<array.length; i++) {
			if (!toBeDeleted[i]) {
				newArray[index++] = array[i];
			}
		}
		
		return newArray;
	}
}



	