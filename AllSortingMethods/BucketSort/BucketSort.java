/**
 * idea: find the maxVal of the array, build 0 - maxVal inclusive length array which is maxVal + 1 buckets
 * so each bucket will be either zero element or multiple same elements
 * then collect them together
 * http://www.growingwiththeweb.com/2015/06/bucket-sort.html
 */

import java.util.*;
 
public class BucketSort {
    public static void main(String[] args) {
        BucketSort eg = new BucketSort();
        int[] data = {5,3,0,2,4,1,0,5,2,3,1,4};
        System.out.println("Before: " + Arrays.toString(data));
        eg.bucketSort(data);
        System.out.println("After:  " + Arrays.toString(data));
    }

    public void bucketSort(int[] a) {
        // Get max value of array
        int maxVal = 0;
        for (int val : a) {
            maxVal = Math.max(val, maxVal);
        }

        // note, the bucket[] index is value to sort
        int[] buckets = new int[maxVal + 1];
        for (int val : a) {
            buckets[val]++;
        }

        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            int countOfSameValue = buckets[i];
            for (int j = 0; j < countOfSameValue; j++) {
                a[index++] = i;
            }
        }
    }
}