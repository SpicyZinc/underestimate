/*
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, 
so you need to return a string instead of an integer.

idea:
sort string array using comparator

http://blog.csdn.net/u013027996/article/details/42773431
write your own comparator
*/

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, new MyComparator());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        String result = sb.toString();
        if ("".equals(result.replace("0", ""))) {
            return "0";
        }
        return result;
        
    }
    
    class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (b+a).compareTo(a+b);
        }
    }
}