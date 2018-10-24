/*
Given a nested list of integers represented as a string, implement a parser to deserialize it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:
String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, ,, ].

Example 1:
Given s = "324",
You should return a NestedInteger object which contains a single integer 324.

Example 2:
Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:
1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.

idea:
need go back
to get a taste of full implementation of NestedInteger,
take a look at https://buttercola.blogspot.com/2015/11/airbnb-mini-parser.html 

http://blog.csdn.net/yeqiuzs/article/details/52208388

"[123,[654],[456,[789]]]"
when [ create a NestedInteger object into stack
when , Integer.parseInt(), create a NestedInteger object with this number, to the peek() NestedInteger
when ] pop()
*/

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger();

    // Constructor initializes a single integer.
    public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


public class MiniParser {
	public NestedInteger deserialize(String s) {
		if (s.length() == 0 || s == null) {
			return new NestedInteger();
		}

		if (s.charAt(0) != '[') {
			return new NestedInteger(Integer.parserInt(s));
		}
		if (s.length() <= 2) {
			return new NestedInteger();
		}

		NestedInteger result = new NestedInteger();
		int start = 1;
		int cnt = 0;
		for (int i = 1; i < s.length(); i++) {
			if (cnt == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
				result.add(deserialize(s.substring(start, i)));
				start = i + 1;
			} else if (s.charAt(i) == '[') {
				cnt++;
			} else if (s.charAt(i) == ']') {
				cnt--;
			}
		}

		return result;
	}

    public NestedInteger deserialize(String s) {
        if (s.length() == 0 || s == null) {
        	return null;
        }
        // since only , [ ] and number, not [ , ] must be an integer
        if (s.charAt(0) != '[') {
        	return new NestedInteger(Integer.parseInt(s));
        }

        int i = 0;
        int numStart = 0;

        Stack<NestedInteger> stack = new Stack<NestedInteger>();
        NestedInteger ni = null;

        while (i < s.length()) {
        	char c = s.carAt(i);
        	if (c == '[') {
        		NestedInteger num = new NestedInteger();
        		if (!stack.isEmpty()) {
        			stack.peek().add(num);
        		}
        		stack.push(num);
        		numStart = i + 1;
        	} else if (c == ',' || c == ']') {
        		if (numStart < i) {
        			int value = Integer.parseInt(s.substring(numStart, i));
        			NestedInteger num = new NestedInteger(value);
        			stack.peek().add(num);
        		}
        		if (c == ']') {
        			ni = stack.pop();
        		}
        		numStart = i + 1;
        	}
        	i++;
        }

        return ni;
    }
}