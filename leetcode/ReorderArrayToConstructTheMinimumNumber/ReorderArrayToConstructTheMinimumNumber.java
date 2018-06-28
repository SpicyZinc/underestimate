/*
Construct minimum number by reordering a given non-negative integer array.
Arrange them such that they form the minimum number.

Notice
The result may be very large, so you need to return a string instead of an integer.

Example
Given [3, 32, 321], there are 6 possible numbers can be constructed by reordering the array:

3+32+321=332321
3+321+32=332132
32+3+321=323321
32+321+3=323213
321+3+32=321332
321+32+3=321323

So after reordering, the minimum number is 321323, and return it.

idea:
use Arrays.sort(nums, new Comparator());
how to compare to satisfy the minimum, (a + b).compareTo(b + a)
note the leading zeros
*/

public class ReorderArrayToConstructTheMinimumNumber {
    /**
     * @param nums: n non-negative integer array
     * @return: A string
     */
    public String minNumber(int[] nums) {
    	if (nums.length == 0 || nums == null) {
    		return "";
    	}
    	int n = nums.length;
    	String[] numsStr = new String[n];
    	int i = 0;
    	for (int num : nums) {
    		numsStr[i++] = String.valueOf(num);
    	}

        Arrays.sort(numsStr, new Comparator<String>() {
        	@Override
        	public int compare(String a, String b) {
        		return (a + b).compareTo(b + a);
        	}
        });

        // ascending sort, generate from the behind
        String answer = "";
        for (String str : numsStr) {
        	answer += str;
        }
        // remove all leading 0s
        int j = 0;
        while (j < n && answer.charAt(j) == '0') {
        	j++;
        }
        if (j == n) {
        	return "0";
        }

        return answer.substring(j);
    }
}