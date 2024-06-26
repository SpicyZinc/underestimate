/*
Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
You need to help them find out their common interest with the least list index sum.
If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".

Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.

idea:
HashMap for sure
note: hm.get() != null is key
*/

public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (int i = 0; i < list1.length; i++) {
       		hm.put(list1[i], i);
        }
        int sumOfIndex = Integer.MAX_VALUE;
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < list2.length; i++) {
        	Integer j = hm.get(list2[i]);
        	if (j != null && j + i <= sumOfIndex) {
        		if (i + j < sumOfIndex) {
        			// reset array list
        			result = new ArrayList<String>();
        			sumOfIndex = i + j;
        		}
        		result.add(list2[i]);
        	}
        }
        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }
}