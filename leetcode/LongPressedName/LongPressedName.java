/*
Your friend is typing his name into a keyboard.
Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.
Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.

Example 1:
Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.

Example 2:
Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.

Example 3:
Input: name = "leelee", typed = "lleeelee"
Output: true

Example 4:
Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 

Note:
name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.

idea:
two pointer
increase i conditionally and finally check i == name.length
*/

class LongPressedName {
	// method 1 is better and clear
	public boolean isLongPressedName(String name, String typed) {
		int i = 0;
		for (int j = 0; j < typed.length() && i < name.length(); j++) {
			if (name.charAt(i) == typed.charAt(j)) {
				i++;
			} else if (i > 0 && name.charAt(i - 1) == typed.charAt(j)) {
				continue;
			} else {
				return false;
			}
		}

		return i == name.length();
	}

	public boolean isLongPressedName(String name, String typed) {
		if (name.length() == typed.length()) {
			return name.equals(typed);
		}

		int j = 0;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (j == typed.length()) {
				return false;
			}

			if (c != typed.charAt(j)) {
				// 如果一开始就不同的char 立即返回false
				// 或者 不同char 没有关系 如果是同上一个char相同也行
				if (j == 0 || typed.charAt(j - 1) != typed.charAt(j)) {
					return false;
				}

				char current = typed.charAt(j);
				while (j < typed.length() && current == typed.charAt(j)) {
					j++;
				}
				if (j == typed.length() || c != typed.charAt(j)) {
					return false;
				}
			}

			j++;
		}

		return true;
	}
}