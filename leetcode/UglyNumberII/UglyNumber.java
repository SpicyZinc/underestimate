/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:
The naive approach is to call isUgly for every number until you reach the nth one. 
Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers.
Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

idea:

method 1:
i from 1 to 6
(1) 1×2, 2×2, 3×2, 4×2, 5×2, 6×2
(2) 1×3, 2×3, 3×3, 4×3, 5×3, 6×3
(3) 1×5, 2×5, 3×5, 4×5, 5×5, 6×5

every sub-array is composed of numbers which are multiplication of ugly number and 2, 3, or 5, 
while this ugly number is picked from the generated array, also the smallest number

i2 i3 i5 are indices, smaller number * 5 ~ bigger number * 2
min of the three

method 2:
1, 2, 3, 4,   5, 6,   8,     9,   10,  12,      15,  16,      18,    20.... is the sequence of the ugly numbers
   2, 3, 2*2, 5, 2*3, 2*2*2, 3*3, 2*5, 2*2*3,   3*5, 2*2*2*2, 2*3*3, 2*2*5

=== 2^a * 3^b * 5^c
*/

public class UglyNumber {
	// Sun May 19 17:03:56 2019
	public int nthUglyNumber(int n) {
		List<Integer> result = new ArrayList<>();
		result.add(1);

		int i = 0;
		int j = 0;
		int k = 0;

		for (int idx = 1; idx < n; idx++) {
			int prev = result.get(result.size() - 1);

			int a = result.get(i) * 2;
			int b = result.get(j) * 3;
			int c = result.get(k) * 5;

			int min = Math.min(a, Math.min(b, c));

			if (a == min) {
				i++;
			}
			if (b == min) {
				j++;
			}
			if (c == min) {
				k++;
			}

			result.add(min);
		}

		return result.get(n - 1);
	}

    // method 1
    public int nthUglyNumber(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        while (result.size() < n) {
            int min2 = result.get(i2) * 2;
            int min3 = result.get(i3) * 3;
            int min5 = result.get(i5) * 5;
            
            int min = minOfThree(min2, min3, min5);
            // move forward to avoid duplicate elements
            if (min2 == min) {
                i2++;
            }
            if (min3 == min) {
                i3++;
            }
            if (min5 == min) {
                i5++;
            }

            result.add(min);
        }

        return result.get(n - 1);
    }

    public int minOfThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // method 2
    public int nthUglyNumber(int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int i = -1; i * i <= n; i++) {
            for (int j = -1; j * j <= n; j++) {
                for (int k = -1; k * k <= n; k++) {
                    pq.add((int) (Math.pow(2, i + 1) * Math.pow(3, j + 1) * Math.pow(5, k + 1)));
                }
            }
        }
        
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = pq.poll();
        }
        
        return result;
    }  
}