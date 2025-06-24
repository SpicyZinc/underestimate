import java.util.*;

class Merge3SortedArrays {
    public static void main(String[] args) {
        // Test Case 1: Normal case
        int[] a1 = {1, 4, 7};
        int[] b1 = {2, 5, 8};
        int[] c1 = {3, 6, 9};
        testMerge(a1, b1, c1, "Test Case 1");

        // Test Case 2: Arrays of different lengths
        int[] a2 = {1, 3};
        int[] b2 = {2};
        int[] c2 = {0, 4, 5, 6};
        testMerge(a2, b2, c2, "Test Case 2");

        // Test Case 3: One empty array
        int[] a3 = {};
        int[] b3 = {1, 2, 3};
        int[] c3 = {4, 5};
        testMerge(a3, b3, c3, "Test Case 3");

        // Test Case 4: All arrays empty
        int[] a4 = {};
        int[] b4 = {};
        int[] c4 = {};
        testMerge(a4, b4, c4, "Test Case 4");

        // Test Case 5: Arrays with duplicate values (still sorted)
        int[] a5 = {1, 2, 2};
        int[] b5 = {2, 3};
        int[] c5 = {1, 4};
        testMerge(a5, b5, c5, "Test Case 5");

        // Test Case 6: Already overlapping sorted ranges
        int[] a6 = {10, 20, 30};
        int[] b6 = {15, 25, 35};
        int[] c6 = {5, 22, 50};
        testMerge(a6, b6, c6, "Test Case 6");
    }

    private static void testMerge(int[] a, int[] b, int[] c, String label) {
        Merge3SortedArrays eg = new Merge3SortedArrays();
        int[] result = eg.mergeThreeSortedArrays(a, b, c);
        System.out.println(label + ": " + Arrays.toString(result));
    }

    public int[] mergeThreeSortedArrays(int[] a, int[] b, int[] c) {
        int m = a.length;
        int n = b.length;
        int l = c.length;

        int i = 0, j = 0, k = 0;
        int idx = 0;

        int[] result = new int[m + n + l];

        while (i < m || j < n || k < l) {
            int minVal = Integer.MAX_VALUE;

            if (i < m) {
                minVal = Math.min(minVal, a[i]);
            }

            if (j < n) {
                minVal = Math.min(minVal, b[j]);
            }

            if (k < l) {
                minVal = Math.min(minVal, c[k]);
            }

            if (i < m && a[i] == minVal) {
                result[idx++] = a[i++];
            } else if (j < n && b[j] == minVal) {
                result[idx++] = b[j++];
            } else if (k < l && c[k] == minVal) {
                result[idx++] = c[k++];
            }
        }

        return result;
    }
}