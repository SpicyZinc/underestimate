/*
idea:
* In bubble sort, we basically traverse the array from first
* to array_length - 1 position and compare the element with the next one.
* Element is swapped with the next element if the next element is greater.
*
* Bubble sort steps are as follows.
*
* 1. Compare array[0] & array[1]
* 2. If array[0] > array [1] swap it.
* 3. Compare array[1] & array[2]
* 4. If array[1] > array[2] swap it.
* ...
* 5. Compare array[n-1] & array[n]
* 6. if [n-1] > array[n] then swap it.
*
* After this step we will have the largest element at the last index.
*
* Repeat the same steps for array[1] to array[n-1]
*/

class BubbleSort {
        public static void main(String[] args) {
                new BubbleSort();
        }
        // constructor
        public BubbleSort() {
                int[] a = {54, 93, 94, 91, 22, 25, 24, 17, 10, 2, 3, 9, 8, 7, 66};
                bubbleSort(a);

                for (int num : a) {
                        System.out.print(num + " ");
                }
                System.out.print("\n");
        }

        public void bubbleSort(int[] intArray) {     
                int n = intArray.length;
                int temp = 0;
               
                for (int i = 0; i < n; i++) {
                        for (int j = 1; j < (n-i); j++) {
                                if (intArray[j-1] > intArray[j]) {
                                        // swap the elements
                                        temp = intArray[j-1];
                                        intArray[j-1] = intArray[j];
                                        intArray[j] = temp;
                                }
                               
                        }
                }
       
        }
}