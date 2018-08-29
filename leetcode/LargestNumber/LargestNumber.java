/*
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

idea:
http://blog.csdn.net/u013027996/article/details/42773431
sort string array using self-defied comparator
e.g. 3 > 30 because '330' > '303'
*/

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        int i = 0;
        for (int num : nums) {
            numStrs[i++] = String.valueOf(num);
        }
        
        Arrays.sort(numStrs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });
        
        // concatenate the sorted str array
        String largestNumber = "";
        for (String str : numStrs) {
            largestNumber += str;
        }
        // if first char is 0, not valid, thus return zero
        if (largestNumber.charAt(0) == '0') {
            return "0";
        }

        return largestNumber;
    }
}