/*
Given an array A of strings made only from lowercase letters,
return a list of all characters that show up in all strings within the list (including duplicates).

For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
You may return the answer in any order.

Example 1:
Input: ["bella","label","roller"]
Output: ["e","l","l"]

Example 2:
Input: ["cool","lock","cook"]
Output: ["c","o"]

Note:
1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter

idea:
用第一个string建立 dictionary
然后更新 dictionary
最后产生结果
*/

class FindCommonCharacters {
public List<String> commonChars(String[] A) {
		// Common characters dictionary
		int[] dict = new int[26];
		for (int i = 0; i < A[0].length(); i++) {
			char c = A[0].charAt(i);
			dict[c - 'a']++;
		}

		for (int i = 1; i < A.length; i++) {
			String str = A[i];
			int[] currDict = new int[26];

			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);

				currDict[c - 'a']++;
			}

			// update the common dictionary
			// 以出现次数少的为准 这是common
			for (int j = 0; j < dict.length; j++) {
				if (currDict[j] < dict[j]) {
					dict[j] = currDict[j];
				}
			}
		}

		List<String> result = new ArrayList<>();
		for (int i = 0; i < dict.length; i++) {
			// 出现次数 dict[i] > 1的话
			for (int j = 0; j < dict[i]; j++) {
				result.add(Character.toString((char) (i + 'a')));
			}
		}

		return result;
	}
}