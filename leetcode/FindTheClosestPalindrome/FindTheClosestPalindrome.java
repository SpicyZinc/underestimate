/*
Given an integer n, find the closest integer (not including itself), which is a palindrome.
The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.

idea:
1. nearest palindrome, take left part containing the mid part for odd, for even, exactly left half
e.g. 12345 left = 123, then apply -1, 0, 1 to left, 122, 123, 124, then mirror it, find the minDiff one
note, all 9 string special case

2. this is to find next closest palindrome
http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/
If one digit is greater than the corresponding digit in right side digit,
then copying the left side to the right side is sufficient to get the closest palindrome
if not sufficient, then add 1 to the middle digit 
*/

public class FindTheClosestPalindrome {
    public String nearestPalindromic(String n) {
        String nearest = n;
        int size = n.length();
        if (size >= 2 && areAll9(n)) {
            return generatePalindromForAll9(size);
        }
        boolean isOdd = size % 2 == 1;
        // whether even or odd, left is (size + 1) / 2
        String left = n.substring(0, (size + 1) / 2);
        long minDiff = Long.MAX_VALUE;
        // increment applied to left
        long[] increments = {-1, 0, 1};
        for (long increment : increments) {
            String possible = Long.toString(Long.valueOf(left) + increment);
            // 1000
            // if possible is 10, it is even
            // 99 is answer, but 999 is nearest palindrome
            String closestPalindrome = getPalindrome(possible, isOdd);
            // note, need to check
            if (size >= 2 && (closestPalindrome.length() < size || Long.parseLong(closestPalindrome) == 0)) {
                closestPalindrome = generateAll9Palindrome(size);
            }
            // avoid the case which is input is palindrome, output should not the same as input
            long diff = closestPalindrome.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(closestPalindrome) - Long.parseLong(n));
            if (diff < minDiff) {
                minDiff = diff;
                nearest = closestPalindrome;
            }
        }

        return nearest;
    }
    // check if string is all 9
    public boolean areAll9(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }
    // generate palindrome for 999
    // note the number of digits
    public String generatePalindromForAll9(int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 1; i < size; i++) {
            sb.append(0);
        }
        sb.append(1);

        return sb.toString();
    }
    // e.g. 123 => 12321, 12 => 1221
    // construct palindrome based on left
    public String getPalindrome(String s, boolean isOdd) {
        String leftMirrorAsRight = new StringBuilder(s).reverse().toString();
        return isOdd ? s.substring(0, s.length() - 1) + leftMirrorAsRight : s + leftMirrorAsRight;
    }
    
    public String generateAll9Palindrome(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < size; i++) {
            sb.append(9);
        }
        return sb.toString();
    }

	// method 2, this is to find next closest palindrome
	public String nearestPalindromic(String n) {
        int size = n.length();
        if ( AreAll9s(n) ) {
        	return generateClosestAll9s(size);
        }
        // other regular cases
        int[] nums = convert(n.toCharArray());
        int mid = size / 2;
        boolean leftsmaller = false;
        int i = mid - 1;
        int j = size % 2 == 1 ? mid + 1 : mid;
        // Initially, ignore the middle same digits 
        while (i >= 0 && j < size && nums[i] == nums[j]) {
            i--;
            j++;
        }
        // Find if the middle digit(s) need to be incremented or not (or copying left side is not sufficient)
        // Indices i & j cross the boundary or left is smaller than right part for sure, use one boolean leftsmaller
        if (i < 0 || j > size || nums[i] < nums[j]) {
            leftsmaller = true;
        }
        // Copy the mirror of left to tight
        while (i >= 0) {
            nums[j] = nums[i];
            j++;
            i--;
        }
        // Handle the case where middle digit(s) must be incremented
        if (leftsmaller) {
            int carry = -1;
            i = mid - 1;
            // If there are odd digits, then increment the middle digit and store the carry
            if (size % 2 == 1) {
                nums[mid] += carry;
                carry = nums[mid] / 10;
                nums[mid] %= 10;
                j = mid + 1;
            } else {
                j = mid;
            }
            // Add 1 to the rightmost digit of the left side, propagate the carry towards MSB digit
            // and simultaneously copying mirror of the left side to the right side
            while (i >= 0) {
                nums[i] += carry;
                carry = nums[i] / 10;
                nums[i] %= 10;
                nums[j++] = nums[i--]; // copy mirror to right
            }
        }

        return convertToString(nums);
    }

    public boolean AreAll9s(String nums) {
        for (int i = 0; i < nums.length(); i++) {
            if ( nums.charAt(i) != '9' ) {
                return false;
            }
        }

        return true;
    }

    private int[] convert(char[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] - '0';
        }
        return result;
    }

    private String convertToString(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }

        return sb.toString();
    }

    private String generateClosestAll9s(int size) {
		StringBuilder sb = new StringBuilder();
        sb.append('1');
        for (int i = 1; i < size; i++) {
            sb.append('0');
        }
        sb.append('1');

        return sb.toString();
    }
}