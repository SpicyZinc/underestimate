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
        if (nums.length == 0 || nums == null) {
            return "";
        }

        int size = nums.length;
        String[] strArray = new String[size];
        for (int i = 0; i < size; i++) {
            strArray[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strArray, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                return (t + s).compareTo(s + t);
            }
        });
        
        String largestNum = "";
        for (String str : strArray) {
            largestNum += str;
        }
        if (largestNum.charAt(0) == '0') return "0";
        
        return largestNum;
    }
}