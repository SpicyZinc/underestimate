// Given an array of strings sort such that the anagrams are next to each other

import java.util.*;

class Word {
	String word;
	int index;

	public Word(String word, int index) {
		this.word = word;
		this.index = index;
	}
}

class SortArrayByGroupingAnagrams {
	public static void main(String[] args) {
		String[] strs = {"aba", "army", "foo", "listen", "bar", "mary", "silent", "aab"};

		SortArrayByGroupingAnagrams eg = new SortArrayByGroupingAnagrams();
		String[] result = eg.sortByAnagram(strs);

		System.out.println(Arrays.toString(result));
	}


	public String[] sortByAnagram(String[] strs) {
		int size = strs.length;

		Word[] copy = new Word[size];

		for (int i = 0; i < size; i++) {
			String word = strs[i];
			String s = normalize(word);
			copy[i] = new Word(s, i);
		}

		Arrays.sort(copy, (a, b) -> a.word.compareTo(b.word));

		String[] sorted = new String[size];

		for (int i = 0; i < size; i++) {
			Word word = copy[i];
			sorted[i] = strs[word.index];
		}

		return sorted;
	}

	public String normalize(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);

		return new String(chars);
	}
}