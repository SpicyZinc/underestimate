/*
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:str = " ", k = 3
Result: "abcabc"
The same letters are at least distance 3 from each other.

Example 2:
str = "aaabc", k = 3
Answer: ""
It is not possible to rearrange the string.

Example 3:
str = "aaadbbcc", k = 2
Answer: "abacabcd"
Another possible answer is: "abcabcda"
The same letters are at least distance 2 from each other.

idea:
http://www.programcreek.com/2014/08/leetcode-rearrange-string-k-distance-apart-java/
hashmap frequency of a character
priority queue top one is the most times appearance of a character
greedy algorithm

https://segmentfault.com/a/1190000005825133
*/

import java.util.*;

public class RearrangeStringKDistanceApart {
	public static void main(String[] args) {
		RearrangeStringKDistanceApart eg = new RearrangeStringKDistanceApart();
		// String s = "abcabc";
		// String s = "aaabc";
		String s = "aaadbbcc";
		String result = eg.rearrangeString(s, 2);
		System.out.println(result);
	}

	public String rearrangeString(String str, int k) {
		if (k == 0) {
			return str;
		}

		int len = str.length();
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
			@Override
			public int compare(Character a, Character b) {
				if (hm.get(a) != hm.get(b)) {
					return hm.get(b) - hm.get(a);
				} else {
					return a.compareTo(b);
				}
			}
		});

		StringBuilder sb = new StringBuilder();
		// first, put all different characters into the queue
		// take this as base unit
		for (char c : hm.keySet()) {
			queue.offer(c);
		}

		while (!queue.isEmpty()) {
			int cnt = Math.min(k, len);
			List<Character> repeatingUnit = new ArrayList<Character>();
			for (int i = 0; i < cnt; i++) {
				if (queue.isEmpty()) {
					return "";
				}
				char ch = queue.poll();
				sb.append(ch);
				hm.put(ch, hm.get(ch) - 1);
				// append one character, length--, because reconstruct string, the biggest length it could be is the original length.
				len--;
				// note
				// if a character is used up, cannot add it to repeatingUnit, which will cause this char to be added to the queue
				// because next while will add this character to return string
				// in addition, return '' if the queue is empty
				if (hm.get(ch) > 0) {
					repeatingUnit.add(ch);
				}
			}
			// use priority queue to get the same order in the repeating unit
			for (char c : repeatingUnit) {
				queue.offer(c);
			}
		}

		return sb.toString();
	}
}