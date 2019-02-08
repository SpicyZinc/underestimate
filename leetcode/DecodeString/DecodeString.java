/*
Given an encoded string, return it's decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there won't be input like 3a or 2[4].

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

idea:
1. stack
one stack is to save repeat times
one stack is to save character in bracket
note: when getting the repeat times, need to s.charAt(i++), if assign to a variable, not working

2. recursion
*/
public class DecodeString {
    // 01/30/2019
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Stack<Integer> count = new Stack<>();
        Stack<String> stack = new Stack<>();
        stack.push("");

        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                val = val * 10 + (c - '0');
            } else if (c == '[') {
                count.push(val);
                val = 0;

                stack.push("");
            } else if (c == ']') {
                String str = stack.pop();
                int times = count.pop();
                
                stack.push(stack.pop() + getString(str, times));
            } else {
                stack.push(stack.pop() + c);
            }
        }
        
        return stack.pop();
    }
    
    public String getString(String s, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            result += s;
        }
        
        return result;
    }


    public String decodeString(String s) {
        if (s.length() == 0 || s == null) {
            return s;
        }

        Stack<Integer> countStack = new Stack<Integer>();
        Stack<String> result = new Stack<String>();

        int i = 0;
        result.push("");

        while (i < s.length()) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int start = i;
                while (Character.isDigit(s.charAt(i + 1))) {
                	i++;
                }
                countStack.push( Integer.parseInt(s.substring(start, i + 1)) );
            } else if (c == '[') {
                result.push("");
            } else if (c == ']') {
                String str = result.pop();
                int times = countStack.pop();
                result.push( result.pop() + repeatString(str, times) );
            } else {
                result.push(result.pop() + c);
            }
            i++;
        }

        return result.pop();
    }

    private String repeatString(String s, int times) {
        String result = "";
        for (int i = 0; i < times; i++) {
            result += s;
        }

        return result;
    }


    // note, this index is global
	private int index = 0;

	public String decodeString(String s) {
		return dfs("1[" + s + "]").toString();
	}

	public StringBuilder dfs(String s) {
		int count = 0;
		StringBuilder result = new StringBuilder();

		for (; index < s.length(); index++) {
			if (Character.isDigit(s.charAt(index))) {
				count = count * 10 + s.charAt(index) - '0';
			} else if (s.charAt(index) == '[') {
				index++;
				StringBuilder sb = dfs(s);
				// append 'count' times
				for (; count > 0; count--) {
					result.append(sb);
				}
			} else if (s.charAt(index) == ']') {
				break;
			} else {
				result.append(s.charAt(index));
			}
		}

		return result;
	}
}