/*
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
{
	{' '}, // 0
	{},  // 1
	{'a', 'b', 'c'}, // 2
	{'d', 'e', 'f'}, // 3
	{'g', 'h', 'i'}, // 4
	{'j', 'k', 'l'}, // 5
	{'m', 'n', 'o'}, // 6
	{'p', 'q', 'r', 's'}, // 7
	{'t', 'u', 'v'}, // 8
	{'w', 'x', 'y', 'z'} // 9
}

idea: 
Do not forget to delete StringBuilder, because append will automatically change sb to a new sb
sb.deleteCharAt(sb.length() - 1); after append, must delete the just appended 'char' 

convert String "23" into char array digit[], level is index of digit[], 
then get index by converting '2' to 2, use this index to find 3 or 4 letters under this index 2
use i to walk through the 3 or 4 letters under one index

*/
import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber eg = new LetterCombinationsOfAPhoneNumber();
		List<String> result = eg.letterCombinations("234");
		for (String s : result) {
			System.out.println(s);
		}
	}

	// best method 1
	public List<String> letterCombinations(String digits) {
        Map<Character, String> hm = new HashMap<Character, String>();
        hm.put('2', "abc");
        hm.put('3', "def");
        hm.put('4', "ghi");
        hm.put('5', "jkl");
        hm.put('6', "mno");
        hm.put('7', "pqrs");
        hm.put('8', "tuv");
        hm.put('9', "wxyz");
        
        List<String> result = new ArrayList<String>();
        if (digits.length() == 0 || digits == null) {
            return result;
        }
        dfs(digits, 0, hm, "", result);

        return result;
    }
    
    private void dfs(String digits, int index, Map<Character, String> hm, String path, List<String> result) {
        if (path.length() == digits.length()) {
            result.add(path);
            return;
        }

        char digit = digits.charAt(index);
        String letters = hm.get(digit);
        for (int j = 0; j < letters.length(); j++) {
            dfs(digits, index + 1, hm, path + letters.charAt(j), result);
        }
    }

    // method 2
   	public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        combine(result, digits, 0, new StringBuilder());
        return result;
    }

    public void combine(List<String> result, String digits, int index, StringBuilder sb) {
        char[][] map = {
            {},
            {}, 
            {'a','b','c'},
            {'d','e','f'},
            {'g','h','i'},
            {'j','k','l'},
            {'m','n','o'}, 
            {'p','q','s','r'}, 
            {'t','u','v'},
            {'w','x','y','z'}
        };

        if (index == digits.length()) {
            result.add(new String(sb.toString()));
            return;
        }

        int key = digits.charAt(index) - '0';
        char[] chars = map[key];
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            combine(result, digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

	// method 3
    public ArrayList<String> letterCombinations(String digits) {
		if (digits.startsWith("0") || digits.startsWith("1")) {
            return new ArrayList<String>();
        }

		ArrayList<String> result = new ArrayList<String>();		
		char[] digit = digits.toCharArray();
		letterCombine(result, 0, digit, new StringBuilder());

		return result;        
    }
	// eg. 563, level is the pointer for each digit
	public void letterCombine(ArrayList<String> result, int level, char[] digit, StringBuilder sb) {
		for (int i = 0; i < getLength(digit[level]); i++) {
			if (level == digit.length - 1) {
				result.add( sb.append(getChar(digit[level], i)).toString() ); 
				sb.deleteCharAt( sb.length() - 1 );
			} else {
				letterCombine( result, level+1, digit, sb.append(getChar(digit[level], i)) );
				sb.deleteCharAt( sb.length() - 1 );
			}
		}
	}
	
	public char getChar(char x, int i) {
		char[][] mapping = {
			{' '}, // 0
			{},  // 1
			{'a', 'b', 'c'}, // 2
			{'d', 'e', 'f'}, // 3
			{'g', 'h', 'i'}, // 4
			{'j', 'k', 'l'}, // 5
			{'m', 'n', 'o'}, // 6
			{'p', 'q', 'r', 's'}, // 7
			{'t', 'u', 'v'}, // 8
			{'w', 'x', 'y', 'z'} // 9
		};

		return mapping[x - '0'][i];
	}
	
	public int getLength(char x) {
		char[][] mapping = {
			{' '}, // 0
			{},  // 1
			{'a', 'b', 'c'}, // 2
			{'d', 'e', 'f'}, // 3
			{'g', 'h', 'i'}, // 4
			{'j', 'k', 'l'}, // 5
			{'m', 'n', 'o'}, // 6
			{'p', 'q', 'r', 's'}, // 7
			{'t', 'u', 'v'}, // 8
			{'w', 'x', 'y', 'z'} // 9
		};

		return mapping[x - '0'].length;
	}
}
