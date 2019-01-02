/*
Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
str = "aabbcc", k = 3
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
https://www.cnblogs.com/grandyang/p/5586009.html
这道题的关键就是 当 PriorityQueue poll时 因为有排序 先poll出频率高的char
这是heap 只关注堆顶

{a=3, b=2, c=2, d=1}
[a, b, c, d]
a
b
repeatingUnit = [a, b]
{a=2, b=1, c=2, d=1}
[a, b, c, d]
ab
==========
{a=2, b=1, c=2, d=1}
[a, b, c, d]
a
c
repeatingUnit = [a, c]
{a=1, b=1, c=1, d=1}
[a, c, b, d]
abac
==========
{a=1, b=1, c=1, d=1}
[a, c, b, d]
a
b
repeatingUnit = []
{a=0, b=0, c=1, d=1}
[c, d]
abacab
==========
{a=0, b=0, c=1, d=1}
[c, d]
c
d
repeatingUnit = []
{a=0, b=0, c=0, d=0}
[]
abacabcd

greedy algorithm
hashmap frequency of a character
priority queue top one is the character appearing the most times

2. https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83193/Java-15ms-Solution-with-Two-auxiliary-array.-O(N)-time.
*/

import java.util.*;

public class RearrangeStringKDistanceApart {
	public static void main(String[] args) {
		RearrangeStringKDistanceApart eg = new RearrangeStringKDistanceApart();
		// String s = "abcabc";
		// String s = "aaabc";
		String s = "aaadbbcc";
		String result = eg.rearrangeString(s, 2);
		// System.out.println(result);

		Map<Character, Integer> hm = new HashMap<Character, Integer>();

		hm.put('a', 3);
		hm.put('c', 1);
		hm.put('f', 1);
		hm.put('y', 1);
		hm.put('x', 1);
		hm.put('h', 5);

		PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
			@Override
			public int compare(Character a, Character b) {
				// return a.compareTo(b);
				return b.compareTo(a);
				// if (hm.get(a) != hm.get(b)) {
				// 	return hm.get(b) - hm.get(a);
				// } else {
				// 	return a.compareTo(b);
				// }
			}
		});

		queue.offer('x');
		queue.offer('c');
		queue.offer('a');

		System.out.println(queue);

		// char c = queue.poll();
		// System.out.println(c);

		// System.out.println(queue);
		// queue.offer('y');

		// System.out.println(queue);
	}

	// 01/01/2019, 56/57 passed
	public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        
        int size = s.length();
            
        Map<Character, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                if (hm.get(a) != hm.get(b)) {
                    return hm.get(b) - hm.get(a);
                } else {
                    return a.compareTo(b);
                }
            }
        });
        
        // first, put all different characters into the queue
		// take this as starting repeating unit
		pq.addAll(hm.keySet());
        
        StringBuilder sb = new StringBuilder();
            
        while (!pq.isEmpty()) {
            List<Character> repeatingUnit = new ArrayList<>();
            int cnt = Math.min(k, size);
            
            for (int i = 0; i < cnt; i++) {
                if (pq.isEmpty()) {
                    return "";
                }
                
                char c = pq.poll();
                
                sb.append(c);
                // update length, reconstructed string, its length cannot be bigger the original length
                size--;
                // update map
                hm.put(c, hm.get(c) - 1);

                if (hm.get(c) > 0) {
                    repeatingUnit.add(c);    
                }
            }
            
            // if a character is used up, cannot add it to repeatingUnit, which will cause this char to be added to the queue
            // because next while will add this character to return string
            // in addition, return '' if the queue is empty
            for (char c : repeatingUnit) {
                pq.offer(c);
            }
        }
        
        return sb.toString();
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
		// take this as starting repeating unit
		for (char c : hm.keySet()) {
			queue.offer(c);
		}

		while (!queue.isEmpty()) {
			// System.out.println(hm);
			// System.out.println(queue);
			int cnt = Math.min(k, len);
			List<Character> repeatingUnit = new ArrayList<Character>();
			for (int i = 0; i < cnt; i++) {
				if (queue.isEmpty()) {
					return "";
				}
				char ch = queue.poll();
				sb.append(ch);
				// System.out.println(ch);
				// update length, reconstructed string, its length cannot be bigger the original length
				len--;
				// update map
				hm.put(ch, hm.get(ch) - 1);

				// if a character is used up, cannot add it to repeatingUnit, which will cause this char to be added to the queue
				// because next while will add this character to return string
				// in addition, return '' if the queue is empty
				if (hm.get(ch) > 0) {
					repeatingUnit.add(ch);
				}
			}
			// System.out.println("repeatingUnit = " + repeatingUnit);
			for (char c : repeatingUnit) {
				queue.offer(c);
			}

			// System.out.println(hm);
			// System.out.println(queue);
			// System.out.println(sb.toString());
			// System.out.println("==========");
		}

		return sb.toString();
	}

	public String rearrangeString(String str, int k) {
		int len = str.length();
		int[] count = new int[26];
		int[] valid = new int[26];

		for (int i = 0; i < len; i++) {
			count[str.charAt(i) - 'a']++;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int candidatePos = findValidMax(count, valid, i);
			if (candidatePos == -1) {
				return "";
			}

			count[candidatePos]--;
			valid[candidatePos] = i + k;
			sb.append((char) ('a' + candidatePos));
		}

		return sb.toString();
	}

	private int findValidMax(int[] count, int[] valid, int index) {
		int max = Integer.MIN_VALUE;
		int candidatePos = -1;
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0 && count[i] > max && index >= valid[i]) {
				max = count[i];
				candidatePos = i;
			}
		}

		return candidatePos;
	}
}