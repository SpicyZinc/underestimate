/*
You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
Return the array after sorting it.

idea:
count one bits
sort

why * 10001
*/

class SortIntegersByTheNumberOf1Bits {
    public int[] sortByBits(int[] arr) {
        int n = arr.length, res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = countBitOne(arr[i]) * 10001 + arr[i];
        }

        Arrays.sort(res);
        for (int i = 0; i < n; i++) {
            res[i] %= 10001;
        }

        return res;
    }

    private int countBitOne(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }

        return count;
    }
}


private int countBitOne(int n) {
    int count = 0;
    while (n != 0) {
        count += (n & 1);
        n >>= 1;
    }

    return count;
}