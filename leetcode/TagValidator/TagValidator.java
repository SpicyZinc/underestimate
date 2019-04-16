/*
Given a string representing a code snippet,
you need to implement a tag validator to parse the code and return whether it is valid.
A code snippet is valid if all the following rules hold:

Valid Code Examples:

Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
Output: True
Explanation: 
The code is wrapped in a closed tag : <DIV> and </DIV>.
The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata. 
Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as tag.
So TAG_CONTENT is valid, and then the code is valid. Thus return true.

Input: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
Output: True
Explanation:
We first separate the code into : start_tag|tag_content|end_tag.
start_tag -> "<DIV>"
end_tag -> "</DIV>"
tag_content could also be separated into : text1|cdata|text2.
text1 -> ">>  ![cdata[]] "
cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"
text2 -> "]]>>]"
The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of the rule 7.

Invalid Code Examples:

Input: "<A>  <B> </A>   </B>"
Output: False
Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be unmatched, and vice versa.

Input: "<DIV>  div tag is not closed  <DIV>"
Output: False

Input: "<DIV>  unmatched <  </DIV>"
Output: False

Input: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
Output: False

Input: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
Output: False

Input: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
Output: False

Note:
For simplicity, you could assume the input code (including the any characters mentioned above)
only contain letters, digits, '<','>','/','!','[',']' and ' '.

idea:
if () {}
else if () {}
else if () {}

order matters
DTATA has to be check at first
close tag
open tag

stack
*/

import java.util.*;

class TagValidator {
	public static void main(String[] args) {
		TagValidator eg = new TagValidator();
		String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
		boolean x = eg.isValid(code);
		System.out.println(x);
	}

    public boolean isValid(String code) {
        Stack<String> st = new Stack<>();

        for (int i = 0; i < code.length(); i++) {
            if (i > 0 && st.isEmpty()) {
            	return false;
            }

            if (i + 9 < code.length() && code.substring(i, i + 9).equals("<![CDATA[")) {
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0) {
                	return false;
                }
                // why +2, not 3, because for loop +1
                i += 2;
            } else if (code.substring(i, i + 2).equals("</")) {
                // [start, end] refers to "</ ...TAG...  >"
                int start = i + 2;
                int end = code.indexOf(">", start);
                
                if (end < 0) {
                	return false;
                }

                String tag = code.substring(start, end);
                // 如果首先遇到 close tag, return false
				// stack peek() not equal tag, return false
				if (st.isEmpty() || !st.peek().equals(tag)) {
					return false;
				}
                // match, so pop it out
                st.pop();
                i = end;
            } else if (code.substring(i, i + 1).equals("<")) {
                // [start, end] refers to "<>"
                int start = i + 1;
				int end = code.indexOf(">", i + 1);
                // 包括<> 最多 9 个chars
				if (end < 0 || end == start || end - start + 1 > 10) {
					return false;
				}
                
                for (int j = start; j < end; j++) {
                    // no uppercase
					if (code.charAt(j) < 'A' || code.charAt(j) > 'Z') {
						return false;
					}
                }
                
                String tag = code.substring(start, end);
				st.push(tag);
                i = end;
            }
        }

        return st.isEmpty();
    }
}