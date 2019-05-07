/*
Given a string s, return all the palindromic permutations (without duplicates) of it.
Return an empty list if no palindromic permutation could be form.

Example 1:
Input: "aabb"
Output: ["abba", "baab"]

Example 2:
Input: "abc"
Output: []

idea:
https://www.cnblogs.com/grandyang/p/5315227.html

有多于一个奇数个char的 return empty
半个 palindrome %2 加入chars
helper method permute() to 得到所有的 permutations + mid + permutation.reverse()
用set to remove duplicates
*/
import java.util.*;

class PalindromePermutation {
	public static void main(String[] args) {
		PalindromePermutation eg = new PalindromePermutation();
		// String s = "aabb";
		String s = "a";
		List<String> result = eg.generatePalindromes(s);
		System.out.println(result);
	}

	// 27 / 29 test cases passed.
	// Memory Limit Exceeded
	public List<String> generatePalindromes(String s) {
		Map<Character, Integer> hm = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}

		String mid = "";
		String palindromeHalf = "";

		for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
			char key = entry.getKey();
			int value = entry.getValue();
			if (value % 2 == 1) {
				mid += key;
			}
			palindromeHalf += populateCharArray(key, value / 2);
			if (mid.length() > 1) {
				return new ArrayList<String>();
			}
		}

		List<StringBuilder> permutations = new ArrayList<>();
		permute(palindromeHalf.toCharArray(), permutations);

		if (permutations.size() == 0 && mid.length() > 0) {
			List<String> result = new ArrayList<>();
			result.add(mid);
			return result;
		}

		Set<String> hs = new HashSet<>();
		for (StringBuilder permutation : permutations) {
			String palindrome = permutation.toString() + mid + permutation.reverse().toString();
			hs.add(palindrome);
		}

		return new ArrayList<String>(hs);
	}

	public String populateCharArray(char c, int times) {
		char[] chars = new char[times];
		for (int i = 0; i < times; i++) {
			chars[i] = c;
		}

		return new String(chars);
	}

	public void permute(char[] chars, List<StringBuilder> permutations) {
		permute(chars, 0, permutations);
	}

	public void permute(char[] chars, int i, List<StringBuilder> permutations) {
		if (i == chars.length - 1) {
			StringBuilder sb = new StringBuilder();
			for (char c : chars) {
				sb.append(c);
			}
			permutations.add(sb);
		} else {
			for (int j = i; j < chars.length; j++) {
				swap(chars, i, j);
				permute(chars, i + 1, permutations);
				swap(chars, i, j);
			}
		}
	}

	public void swap(char[] chars, int x, int y) {
		char temp = chars[x];
		chars[x] = chars[y];
		chars[y] = temp;
	}
}