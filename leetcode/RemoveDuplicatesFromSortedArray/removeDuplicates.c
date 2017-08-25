#include <stdio.h>

int removeDuplicates(int a[], int array_size);

// The main function
int main(){

	// Different test cases..
	int my_array1[] = {1, 1, 2, 3, 5, 6, 6, 7, 10, 25, 100, 123, 123};
	int my_array1_size = 13;

	int my_array2[] = {1, 2, 3, 5, 6};
	int my_array2_size = 5;

	int my_array3[] = {1, 1, 1, 1, 1};
	int my_array3_size = 5;

	int my_array4[] = {123, 123};
	int my_array4_size = 2;

	int my_array5[] = {1, 123, 123};
	int my_array5_size = 3;

	int my_array6[] = {123, 123, 166};
	int my_array6_size = 3;

	int my_array7[] = {1, 2, 8, 8 , 24, 60, 60, 60, 60, 75, 100, 100, 123};
	int my_array7_size = 13;


	my_array1_size = removeDuplicates(my_array1, my_array1_size);
	my_array2_size = removeDuplicates(my_array2, my_array2_size);
	my_array3_size = removeDuplicates(my_array3, my_array3_size);
	my_array4_size = removeDuplicates(my_array4, my_array4_size);
	my_array5_size = removeDuplicates(my_array5, my_array5_size);
	my_array6_size = removeDuplicates(my_array6, my_array6_size);
	my_array7_size = removeDuplicates(my_array7, my_array7_size);

	return(0);
}


// Function to remove the duplicates
int removeDuplicates(int a[], int array_size){
	int i, j;
	j = 0;
	// Print old array...
	printf("\n\nOLD : ");
	for(i = 0; i < array_size; i++){
		printf("[%d] ", a[i]);
	}

	// Remove the duplicates ...
	for (i = 1; i < array_size; i++){
		if (a[i] != a[j]){
			j++;
			a[j] = a[i]; // Move it to the front
		}
	}

	// The new array size..
	array_size = (j + 1);


	// Print new array...
	printf("\n\nNEW : ");
	for(i = 0; i < array_size; i++){
		printf("[%d] ", a[i]);
	}
	printf("\n\n");

	// Return the new size...
	return(j + 1);
}
