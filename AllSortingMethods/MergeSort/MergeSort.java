import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = {2, 6, 3, 5, 1};
        int[] temp = new int[a.length];

        MergeSort test = new MergeSort();
        test.mergeSort(a, temp, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public void mergeSort(int[] a, int[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center, right);
        }
    }

    public void merge(int[] a, int[] tmp, int leftStart, int mid, int rightEnd) {
        int leftEnd = mid;
        int rightStart = mid + 1;
        // note k = leftStart
        int k = leftStart;
        int len = rightEnd - leftStart + 1;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (a[leftStart] <= a[rightStart]) {
                tmp[k++] = a[leftStart++];
            } else {
                tmp[k++] = a[rightStart++];
            }
        }
        // Copy rest of first half
        while (leftStart <= leftEnd) {
            tmp[k++] = a[leftStart++];
        }
        // Copy rest of right half
        while (rightStart <= rightEnd) {
            tmp[k++] = a[rightStart++];         
        }
        // note
        // Copy tmp back
        for (int i = 0; i < len; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }

    // this is wrong version because of as below 
    public void mergeSort(int[] a, int[] tmp, int left, int right) {
        if (left < right) {
            int middle = left + ( right - left ) / 2;
            mergeSort(a, tmp, left, middle);
            mergeSort(a, tmp, middle + 1, right);
            merge(a, tmp, left, middle, right);
        }
    }

    public void merge(int[] a, int[] tmp, int leftStart, int mid, int rightEnd) {
        int leftEnd = mid;
        int rightStart = mid + 1;
        // note k = leftStart
        int k = leftStart;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (a[leftStart] < a[rightStart]) {
                tmp[k++] = a[leftStart++];
            } else {
                tmp[k++] = a[rightStart++];
            }
        }
        while (leftStart <= leftEnd) {
            tmp[k++] = a[leftStart++];
        }
        while (rightStart <= rightEnd) {
            tmp[k++] = a[rightStart++];
        }
        // the position is wrong !!!
        int size = rightEnd - leftStart + 1;
        // copy from tmp to original array
        for (int i = 0; i < size; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }
}