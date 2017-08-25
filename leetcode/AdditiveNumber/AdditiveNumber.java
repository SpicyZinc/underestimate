/*
Additive number is a positive integer whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers.
Except for the first two numbers, each subsequent number in the sequence
must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros,
so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string represents an integer, write a function to determine if it's an additive number.

idea:
https://leetcode.com/discuss/70102/java-recursive-and-iterative-solutions
https://leetcode.com/discuss/70157/java-easy-understand-dfs

Generate the first and second of the sequence, check if the rest of the string match the sum recursively.
i and j are length of the first and second number.
i should in the range of [0, n/2].
The length of their sum should be >= Math.max(i, j)

later not pass the test for this case "0235813"
*/

public class AdditiveNumber {
	// recursion
	public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(j, i) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) {
                	break;
                }
                BigInteger x2 = new BigInteger(num.substring(i, i + j));
                if (isValid(x1, x2, j + i, num)) {
                	return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
        if (start == num.length()) {
        	return true;
        }
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, start + sum.length(), num);
    }
    // iteration
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            for (int j = 1; Math.max(i, j) <= n - i - j; j++) {
                if (isValid(i, j, num)) {
                	return true;
                }
            }
        }
        return false;
    }
    private boolean isValid(int i, int j, String num) {
        if (num.charAt(i) == '0' && j > 1) {
        	return false;
        }
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) {
            	return false;
            }
        }
        return true;
    }
    // very intuitive way
    public boolean isAdditiveNumber(String s) {
		for (int i = 1; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				int part1 = Integer.parseInt(s.substring(0, i));
				int part2 = Integer.parseInt(s.substring(i, j));
				int index = j;
				int rest = Integer.parseInt(s.substring(j));
				while (part1 + part2 <= rest) {
					int part3 = part1 + part2;
					String str = String.valueOf(part3);
					int length = str.length();
					if (index + length > s.length()) {
						break;
					}
					if (s.substring(index, index + length).equals(str)) {
						index = index + length;
						if (index == s.length()) {
							return true;
						}
						part1 = part2;
						part2 = part3;
						rest = Integer.parseInt(s.substring(index));
					}
					else {
						break;
					}
				}
			}
		}
		return false;
	}
}
