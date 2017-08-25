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
this is to find next closest palindrome
http://www.geeksforgeeks.org/given-a-number-find-next-smallest-palindrome-larger-than-this-number/

If this digit is greater than the corresponding digit in right side digit, then copying the left side to the right side is sufficient
*/

public class FindTheClosestPalindrome {
	public static void main(String[] args) {
		FindTheClosestPalindrome eg = new FindTheClosestPalindrome();
		String res = eg.nearestPalindromic("1001");
		System.out.println(res);
	}

    // self written correct method
    public String nearestPalindromic(String n) {
        int size = n.length();
        if (size >= 2 && areAll9(n)) {
            return generateForAll9(size);
        }
        String ret = n;
        boolean isOdd = size % 2 == 1;
        String left = n.substring(0, (size + 1) / 2);
        long minDiff = Long.MAX_VALUE;
        long[] increment = {-1, 0, 1};
        for (long i : increment) {
            String temp = Long.toString( Long.parseLong(left) + i );
            String closestStr = getPalindrome(temp, isOdd);
            if (size >= 2 && (closestStr.length() < size || Long.parseLong(closestStr) == 0)) {
                closestStr = generateAll9(size);
            }
            // avoid the case which is input is palindrome, output should not the same as input
            long diff = closestStr.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(closestStr) - Long.parseLong(n));
            if (diff < minDiff) {
                minDiff = diff;
                ret = closestStr;
            }
        }
        return ret;
    }
    
    private boolean areAll9(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }
    
    private String generateForAll9(int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 1; i < size; i++) {
            sb.append(0);
        }
        sb.append(1);
        return sb.toString();
    }
    
    private String getPalindrome(String s, boolean isOdd) {
        String leftMirrorAsRight = new StringBuilder(s).reverse().toString();
        return isOdd ? s.substring(0, s.length() - 1) + leftMirrorAsRight : s + leftMirrorAsRight;
    }
    
    private String generateAll9(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < size; i++) {
            sb.append(9);
        }
        return sb.toString();
    }

	// this is to find next closest palindrome
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
        if (leftsmaller == true) {
            int carry = -1;
            i = mid - 1;
            // If there are odd digits, then increment the middle digit and store the carry
            if (size % 2 == 1) {
                nums[mid] += carry;
                carry = nums[mid] / 10;
                nums[mid] %= 10;
                j = mid + 1;
            }
            else {
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





    // self play, finally passed 136/212, gave up
    private boolean multipleOfTen(String n) {
        long num = Long.parseLong(n);
        long origin = num;
        while ( num > 1 ) {
            num /= 10;
        }
        return origin % 10 == 0 && num == 1;
    }
    private String generateAll9(String n) {
        int num = Integer.parseInt(n);
        return num - 1 + "";
    }

    public String nearestPalindromic(String n) {
        int size = n.length();
        if (size == 1) {
            return Integer.parseInt(n) - 1 + "";    
        }
        if ( AreAll9s(n) ) {
        	return generateClosestAll9s(size);
        }
        if (multipleOfTen(n)) {
            return generateAll9(n);
        }
        
        if (n.matches("^10*1$")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < size; i++) {
                sb.append(9);
            }
            return sb.toString();
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
        // Indices i & j cross the boundary, use one boolean leftsmaller
        if ( i < 0 ) {
            leftsmaller = true;
        }
        // Copy the mirror of left to tight
        while (i >= 0) {
            nums[j] = nums[i];
            j++;
            i--;
        }
        // Handle the case where middle digit(s) must be incremented.
        if (leftsmaller == true) {
        	// note this is -1
            int carry = -1;
            i = mid - 1;
            // If there are odd digits, then increment the middle digit and store the carry
            if (size % 2 == 1) {
                nums[mid] += carry;
                carry = nums[mid] / 10;
                nums[mid] %= 10;
                j = mid + 1;
            }
            else {
                j = mid;
            }
            // Add 1 to the rightmost digit of the left side, propagate the carry towards MSB digit
            // and simultaneously copying mirror of the left side to the right side.
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
