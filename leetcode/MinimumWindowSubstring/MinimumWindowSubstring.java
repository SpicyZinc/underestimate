/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

idea:
http://blog.csdn.net/linhuanmars/article/details/20343903

遇到没在Map的字符不断移动窗口右端 直到cover all T's chars
移动窗口左端的条件是当找到满足条件的串之后, 一直移动窗口左端直到有Map的字符不再在窗口里

一开始key包含字典中所有字符, value就是该字符的数量, 然后遇到字典中字符时就将对应字符的数量减一

suppose char are 256 ascii code
two count array, ascii code as index
count[] for String S
t[] for String T

the worst assumption is minWindow = S.length() + 1
take each char in S as starting point, find shortest substring containing the all t's characters

similar to SubstringWithConcatenationOfAllWords
*/
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println("S == " + S);
		System.out.println("T == " + T);
		MinimumWindowSubstring aTest = new MinimumWindowSubstring();
		String minimumWindow = aTest.minWindow(S, T);
		System.out.println("minimumWindow == " + minimumWindow);		
	}
	// Sat May 18 17:18:53 2024
	public String minWindow(String s, String t) {
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            hm.put(t.charAt(i), hm.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int n = s.length();
        String minStr = "";
        int minLen = n + 1;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) - 1);
            }

            while (doesIncludeT(hm)) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStr = s.substring(left, right + 1);
                }
                // shrink left
                char charAtLeft = s.charAt(left);
                left++;
                if (hm.containsKey(charAtLeft)) {
                    hm.put(charAtLeft, hm.get(charAtLeft) + 1);
                }
            }
        }

        return minStr;
    }
    // 这个作用就是 cntCharInTFromS == t.length()
    public boolean doesIncludeT(Map<Character, Integer> hm) {
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                return false;
            }
        }

        return true;
    }

	// Thu Feb 23 22:16:14 2023 prepare for eBay
	public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }

        int cntCharInTFromS = 0;
        int left = 0;
        int minLen = s.length() + 1;
        String minWindowStr = "";

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) - 1);
                if (hm.get(c) >= 0) {
                    cntCharInTFromS++;
                }
            }

            while (cntCharInTFromS == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;                    
                    minWindowStr = s.substring(left, right + 1);
                }

                char charAtLeft = s.charAt(left);
                if (hm.containsKey(charAtLeft)) {
                    hm.put(charAtLeft, hm.get(charAtLeft) + 1);
                    if (hm.get(charAtLeft) > 0) {
                        cntCharInTFromS--;
                    }
                }
                left++;
            }
        }

        return minWindowStr;
    }
	// Sun Jun 16 16:13:57 2019
	public String minWindow(String s, String t) {
		int sLen = s.length();
		int tLen = t.length();

		int[] sLetters = new int[256];
		int[] tLetters = new int[256];

		// build map for string t
		for (int i = 0; i < tLen; i++) {
			char c = t.charAt(i);
			tLetters[c]++;
		}

		// count unique char's count
		int tUniqueCharsCount = 0;
		for (int i = 0; i < tLetters.length; i++) {
			if (tLetters[i] > 0) {
				tUniqueCharsCount++;
			}
		}

		int right = 0;
		int uniqueTCharsCountInWindow = 0;

		String minWindowStr = "";

		for (int left = 0; left < sLen; left++) {
			
			while (right < sLen && uniqueTCharsCountInWindow < tUniqueCharsCount) {
				char c = s.charAt(right);
				sLetters[c]++;
				// 不仅有 而且个数一样
				if (sLetters[c] == tLetters[c]) {
					uniqueTCharsCountInWindow++;
				}

				right++;
			}

			// found out a window where uniqueTCharsCountInWindow == tUniqueCharsCount
			if (uniqueTCharsCountInWindow == tUniqueCharsCount) {
				// at first or found out smaller window
				if (minWindowStr.length() == 0 || right - left < minWindowStr.length()) {
					minWindowStr = s.substring(left, right);
				}
			}

			char charAtLeft = s.charAt(left);
			// left shift
			sLetters[charAtLeft]--;
			if (sLetters[charAtLeft] == tLetters[charAtLeft] - 1) {
				uniqueTCharsCountInWindow--;
			}
		}

		return minWindowStr;
	}

	// 同向双指针
	// how to define cover the t
	// 就是 sLetters[c] >= tLetters[c]
	public String minWindow(String s, String t) {
		String minWindowStr = "";

		int sLen = s.length();
		int tLen = t.length();

		if (sLen < tLen) {
			return minWindowStr;
		}

		int tUniqueCharsCount = 0;
		int[] sLetters = new int[256];
		int[] tLetters = new int[256];

		for (int i = 0; i < tLen; i++) {
			char c = t.charAt(i);
			tLetters[c]++;
			if (tLetters[c] == 1) {
				tUniqueCharsCount++;
			}			
		}

		int uniqueTCharsCountInWindow = 0;
		int right = 0;

		for (int left = 0; left < sLen; left++) {

			while (right < sLen && uniqueTCharsCountInWindow < tUniqueCharsCount) {
				char c = s.charAt(right);
				sLetters[c]++;

				if (sLetters[c] == tLetters[c]) {
					uniqueTCharsCountInWindow++;
				}

				right++;
			}
			// found a window
			if (uniqueTCharsCountInWindow == tUniqueCharsCount) {
				if (minWindowStr.length() == 0 || right - left < minWindowStr.length()) {
					minWindowStr = s.substring(left, right);
				}
			}

			// remove char at left side of window
			char charAtLeft = s.charAt(left);
			sLetters[charAtLeft]--;
			// 减少一个后还相等, 如果去掉的char 也在 t 中 可能有三个 就是少了一个 当前的window也不cover了
			if (sLetters[charAtLeft] == tLetters[charAtLeft] - 1) {
				uniqueTCharsCountInWindow--;
			}
		}

		return minWindowStr;
	}
	// 01/13/2019 jiuzhang
	// note, use map, containsKey
	public String minWindow(String s, String t) {
		String minWindowStr = "";

		int sLen = s.length();
		int tLen = t.length();

		if (sLen < tLen) {
			return minWindowStr;
		}

		Map<Character, Integer> tLetters = new HashMap<>();
		
		for (int i = 0; i < tLen; i++) {
			char c = t.charAt(i);
			tLetters.put(c, tLetters.getOrDefault(c, 0) + 1);
		}

		int cntCharsInT = 0; // total chars in String t
		int left = 0;
		int minLen = sLen + 1;

		for (int right = 0; right < sLen; right++) {
			char c = s.charAt(right);
			
			if (tLetters.containsKey(c)) {
				
				tLetters.put(c, tLetters.get(c) - 1);

				if (tLetters.get(c) >= 0) {
					cntCharsInT++;
				}

				while (cntCharsInT == tLen) {
					if (minLen > right - left + 1) {
						minLen = right - left + 1;
						// if (right >= left) {
							minWindowStr = s.substring(left, right + 1);
						// }
					}

					// 开始移动左窗口
					char charAtLeft = s.charAt(left);
					left++;
					if (tLetters.containsKey(charAtLeft)) {
						tLetters.put(charAtLeft, tLetters.get(charAtLeft) + 1);
						if (tLetters.get(charAtLeft) > 0) {
							cntCharsInT--;
						}
					}
				}
			}
		}

		return minWindowStr;
	}

	// 09/11/2018 prepare for VMware
	public String minWindow(String s, String t) {
		String minWindowStr = "";

		int sLen = s.length();
		int tLen = t.length();

		if (sLen < tLen) {
			return minWindowStr;
		}

		int left = 0;
		int tUniqueCharsCountInS = 0;
		int minLen = sLen + 1;

		Map<Character, Integer> tMap = new HashMap<>();
		for (int i = 0; i < tLen; i++) {
			char c = t.charAt(i);
			tMap.put(c, tMap.getOrDefault(c, 0) + 1);
		}

		for (int right = 0; right < sLen; right++) {
			char c = s.charAt(right);
			// only operate when current char appear in T
			if (tMap.containsKey(c)) {
				tMap.put(c, tMap.get(c) - 1);
				if (tMap.get(c) >= 0) {
					tUniqueCharsCountInS++;
				}

				while (tUniqueCharsCountInS == tLen) {
					// update minWindowStr
					if (minLen > right - left + 1) {
						minLen = right - left + 1;
						minWindowStr = s.substring(left, right + 1);
					}

					char charAtLeft = s.charAt(left);
					// start to move left on the condition that some minWindowStr found out
					if (tMap.containsKey(charAtLeft)) {
						tMap.put(charAtLeft, tMap.get(charAtLeft) + 1);
						if (tMap.get(charAtLeft) > 0) {
							tUniqueCharsCountInS--;
						}
					}
					left++;
				}
			}
		}
		
		return minWindowStr;
	}
	// 08/25/2018 prepare for Amazon
	// although fail 267 / 268 test cases passed, but good to try
	public String minWindow(String s, String t) {
		String result = "";
		if (t.length() > s.length()) {
			return result;
		}
		
		int[] letters = new int[256];
		for (int i = 0; i < t.length(); i++) {
			int c = t.charAt(i);
			letters[c]++;
		}
		
		int min = s.length() + 1;
		
		for (int i = 0; i < s.length(); i++) {
			int[] copy = Arrays.copyOf(letters, letters.length);
			for (int j = i; j < s.length(); j++) {
				int c = s.charAt(j);
				if (copy[c] >= 1) {
					copy[c]--;
				}
				if (isEmpty(copy)) {
					int len = j - i + 1;
					if (min > len) {
						min = len;
						result = s.substring(i, j + 1);
					}
				}
			}
		}
		
		return min == s.length() + 1 ? "" : result;
	}
	
	public boolean isEmpty(int[] letters) {
		for (int cnt : letters) {
			if (cnt != 0) {
				return false;
			}
		}
		
		return true;
	}

	// 07/09/2018 prepare for Google
	public String minWindow(String s, String t) {
		String result = "";
		if (t.length() > s.length()) {
			return result;
		}

		int left = 0;
		int tCharsCountInS = 0;
		int size = s.length();
		int minLen = size + 1; // 就像 initialize Integer.MAX_VALUE

		Map<Character, Integer> hm = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}

		for (int right = 0; right < size; right++) {
			char rightMost = s.charAt(right);
			if (hm.containsKey(rightMost)) {
				hm.put(rightMost, hm.get(rightMost) - 1);
				if (hm.get(rightMost) >= 0) {
					tCharsCountInS++;
				}
				// move the window's left
				while (tCharsCountInS == t.length()) {
					// update minLen
					if (right - left + 1 < minLen) {
						minLen = right - left + 1;
						result = s.substring(left, right + 1);
					}
					// start moving window's left
					char leftMost = s.charAt(left);
					if (hm.containsKey(leftMost)) {
						hm.put(leftMost, hm.get(leftMost) + 1);
						if (hm.get(leftMost) > 0) {
							tCharsCountInS--;
						}
					}
					left++;
				}
			}
		}

		return result;
	}

	// newly written version
	public String minWindow(String s, String t) {
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}
		
		int size = s.length();
		int minLen = size + 1;
		int cntCharsT = 0; // counter for appeared T's character in S
		int windowLeft = 0;
		String window = "";
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (hm.containsKey(c)) {
				hm.put(c, hm.get(c) - 1);   
				if (hm.get(c) >= 0) cntCharsT++;
			}
			while (cntCharsT == t.length()) {
				char leftChar = s.charAt(windowLeft);
				if (hm.containsKey(leftChar)) {
					// when map values zero, meaning window covers T
					// window shrinks, restore map
					hm.put(leftChar, hm.get(leftChar) + 1);
					if (hm.get(leftChar) >= 1) {
						int len = i - windowLeft + 1;
						if (minLen > len) {
							minLen = len;
							window = s.substring(windowLeft, i + 1);
						}
						cntCharsT--;
					}
				}
				windowLeft++;
			}
		}
		
		return window;
	}

	// self written, passed 267 / 268 test cases, TLE
	public String minWindow(String s, String t) {
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}
		
		int size = s.length();
		int minLen = size + 1;
		String window = "";
		int left = 0;
		while (size - left >= t.length()) {
			Map<Character, Integer> copyMap = new HashMap<Character, Integer>(hm);
			for (int i = left; i < size; i++) {
				char c = s.charAt(i);
				if (copyMap.containsKey(c)) {
					int count = copyMap.get(c);
					if (count == 1) {
						copyMap.remove(c);
					} else if (count > 1) {
						copyMap.put(c, count - 1);
					}
				}
				if (copyMap.size() == 0) {
					int windowLen = i - left + 1;
					if (minLen > windowLen) {
						minLen = windowLen;
						window = s.substring(left, i + 1);
						break;
					}
				}
			}
			left++;
		}
		
		return window;
	}
}