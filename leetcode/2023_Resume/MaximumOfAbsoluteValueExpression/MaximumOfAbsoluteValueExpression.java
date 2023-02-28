
// idea:
// 4 cases

// Sun Feb 26 17:51:24 2023
class MaximumOfAbsoluteValueExpression {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int a = Math.abs(arr1[i] - arr1[j]);
                int b = Math.abs(arr2[i] - arr2[j]);
                int diff = j - i;
                
                max = Math.max(max, a + b + diff);
            }
        }
        
        return max;
    }

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int max4 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int min4 = Integer.MAX_VALUE;

        int n = arr1.length;

        for (int i = 0; i < n; i++) {
            // 1st scenario arr1[i] + arr2[i] + i
            max1 = Math.max(arr1[i] + arr2[i] + i, max1);
            min1 = Math.min(arr1[i] + arr2[i] + i, min1);
            // 2nd scenario arr1[i] + arr2[i] - i
            max2 = Math.max(arr1[i] + arr2[i] - i, max2);
            min2 = Math.min(arr1[i] + arr2[i] - i, min2);
            // 3rd scenario arr1[i] - arr2[i] - i
            max3 = Math.max(arr1[i] - arr2[i] - i, max3);
            min3 = Math.min(arr1[i] - arr2[i] - i, min3);
            // 4th scenario arr1[i] - arr2[i] + i
            max4 = Math.max(arr1[i] - arr2[i] + i, max4);
            min4 = Math.min(arr1[i] - arr2[i] + i, min4);
        }

        int diff1 = max1 - min1;
        int diff2 = max2 - min2;
        int diff3 = max3 - min3;
        int diff4 = max4 - min4;

        return Math.max(Math.max(diff1, diff2), Math.max(diff3, diff4));
    }
}
