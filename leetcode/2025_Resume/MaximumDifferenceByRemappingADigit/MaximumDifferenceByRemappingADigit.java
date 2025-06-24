/**
You are given an integer num. You know that Bob will sneakily remap one of the 10 possible digits (0 to 9) to another digit.

Return the difference between the maximum and minimum values Bob can make by remapping exactly one digit in num.

Notes:
When Bob remaps a digit d1 to another digit d2, Bob replaces all occurrences of d1 in num with d2.
Bob can remap a digit to itself, in which case num does not change.
Bob can remap different digits for obtaining minimum and maximum values respectively.
The resulting number after remapping can contain leading zeroes.

Example 1:
Input: num = 11891
Output: 99009
Explanation: 
To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
The difference between these two numbers is 99009.

Example 2:
Input: num = 90
Output: 99
Explanation:
The maximum value that can be returned by the function is 99 (if 0 is replaced by 9) and the minimum value that can be returned by the function is 0 (if 9 is replaced by 0).
Thus, we return 99.

Constraints:
1 <= num <= 10^8


idea:
just direct method
*/


class MaximumDifferenceByRemappingADigit {
    public int minMaxDifference(int num) {
        String n = String.valueOf(num);

        // Maximize - find first non-'9' digit and replace all its occurrences with '9'
        char toReplaceMax = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '9') {
                toReplaceMax = n.charAt(i);
                break;
            }
        }
        String maxStr = n;
        if (toReplaceMax != 0) {
            // Replace all occurrences of toReplaceMax with '9'
            maxStr = maxStr.replace(toReplaceMax, '9');
        }

        // Minimize - find first non-'0' digit and replace all its occurrences with '0'
        char toReplaceMin = 0;
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) != '0') {
                toReplaceMin = n.charAt(i);
                break;
            }
        }
        String minStr = n;
        if (toReplaceMin != 0) {
            // Replace all occurrences of toReplaceMin with '0'
            minStr = minStr.replace(toReplaceMin, '0');
        }

        // Step 4: Convert both strings to integers and return the difference
        return Integer.parseInt(maxStr) - Integer.parseInt(minStr);
    }
}
