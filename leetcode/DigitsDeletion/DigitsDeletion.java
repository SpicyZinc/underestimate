/*
Given string A representative a positive integer which has N digits, remove any k digits of the number,
the remaining digits are arranged according to the original order to become a new positive integer.
Find the smallest integer after remove k digits.
N <= 240 and k <= N,

Example
Given an integer A = "178542", k = 4
return a string "12"

idea:
find the first decreasing point from the behind

要让一个数尽量小
那么就要把小的数字尽量放到前面
如果前面有比它大的数字
那么就到把在它前面且比它大的数字都要删除掉
直到已经删掉k个数字
所以最后留下的是一个递增数列

同时要考虑一些特殊情况
比如前置0要去掉
以及如果遍历一遍之后发现删去的数不足k个
则删去最后的k－count个数
*/


public class DigitsDeletion {
    /**
     * @param A: A positive integer which has N digits, A is a string
     * @param k: Remove k digits
     * @return: A string
     */
    // self wrong answer
    public String DeleteDigits(String A, int k) {
        char[] digits = A.toCharArray();
        Arrays.sort(digits);
        
        String largestK = "";
        for (int i = digits.length - 1; i >= digits.length - k; i--) {
            largestK += digits[i];
        }
        String smallest = "";
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (largestK.indexOf(c) != -1) {
                continue;
            }
            smallest += c;
        }
        
        return smallest;
    }

	public String DeleteDigits(String A, int k) {
		StringBuilder sb = new StringBuilder(A);
		int i, j;
		for (i = 0; i < k; i++) {
		    j = 0;
			while (j < sb.length() - 1 && sb.charAt(j) <= sb.charAt(j + 1)) {
				j++;
			}
			// find one decreasing part, delete
			sb.delete(j, j + 1);
		}
		// remove all leading zeros
		while (sb.length() > 1 && sb.charAt(0) == '0') {
			sb.delete(0, 1);
		}
		
		return sb.toString();		
	}
}