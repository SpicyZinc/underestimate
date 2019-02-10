/*
Given a chemical formula (given as a string), return the count of each atom.
An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow.
For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form:
the first name (in sorted order), followed by its count (if that count is more than 1),
followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.

Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

Note:
All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

idea:
recursion is not good,
stack is better to understand
*/

import java.util.*;

class NumberOfAtoms {
	public static void main(String[] args) {
		NumberOfAtoms eg = new NumberOfAtoms();
		String formula = "Mg(OH)2";
		String result = eg.countOfAtoms(formula);
		System.out.println(result);

		int x = eg.getWhat();
		System.out.println(x);
	}

	public int getWhat() {
		int i = 100;
		increase(i);
		return i;
	}

	public void increase(int i) {
		i += 20;
		System.out.println(i);
	}

	//02/08/2019
	public String countOfAtoms(String formula) {
		Stack<Map<String, Integer>> stack = new Stack<>();
		Map<String, Integer> map = new HashMap<>();

		int n = formula.length();
		int i = 0;

		while (i < n) {
			char c = formula.charAt(i++);
			// '(' means starts nested, starts to deal with a new round
			// 先push进去 what we have, then re-initialize the map
			if (c == '(') {
				stack.push(map);

				map = new HashMap<>();
			} else if (c == ')') {
				int[] cntAndPos = getCntAndPos(formula, i);
				int cnt = cntAndPos[0];
				int pos = cntAndPos[1];
				i = pos;

				if (!stack.isEmpty()) {
					Map<String, Integer> current = map;
					map = stack.pop();
					// 兵和一处
					for (String key : current.keySet()) {
						map.put(key, map.getOrDefault(key, 0) + current.get(key) * cnt);
					}
				}
			} else {
				int start = i - 1;

				while (i < n && Character.isLowerCase(formula.charAt(i))) {
					i++;
				}
				// elements
				String s = formula.substring(start, i);

				int[] cntAndPos = getCntAndPos(formula, i);
				int cnt = cntAndPos[0];
				int pos = cntAndPos[1];
				i = pos;

				map.put(s, map.getOrDefault(s, 0) + cnt);
            }
        }

		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list);

        for (String atom : list) {
			int cnt = map.get(atom);
			sb.append(atom + (cnt > 1 ? cnt : ""));
		}

        return sb.toString();
    }

	// get the count of the element, default is 1
	// get the next element starting position
	public int[] getCntAndPos(String formula, int i) {
		int n = formula.length();
		int k = i;

		int val = 0;

		while (k < n && Character.isDigit(formula.charAt(k))) {
			val = val * 10 + formula.charAt(k++) - '0';
		}
		if (val == 0) {
			val = 1;
		}

		return new int[] {val, k};
	}

    
    public String countOfAtoms(String formula) {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> map = dfs(formula);
        List<String> atoms = new ArrayList<>(map.keySet());
        
        Collections.sort(atoms);
        for (String atom : atoms) {
            int cnt = map.get(atom);
            sb.append(atom + (cnt > 1 ? cnt : ""));
        }

        return sb.toString();
    }

    private Map<String, Integer> dfs(String formula) {
        Map<String, Integer> hm = new HashMap<>();
        if (formula == null || formula.length() == 0) {
            return hm;
        }

        int i = 0;
        int len = formula.length();
        int[] posAndCnt;
        // 1. == '('
        // 2. != '('
        while (i < len) {
            if (formula.charAt(i) == '(') {
                int j = i;
                int parenthesisCnt = 0;
                for (j = i; j < len; j++) {
                    if (formula.charAt(j) == '(') {
                        parenthesisCnt++;
                    } else if (formula.charAt(j) == ')') {
                        parenthesisCnt--;
                    }

                    if (parenthesisCnt == 0) {
                        break;
                    }
                }

                posAndCnt = positionAndCount(formula, j + 1);
                Map<String, Integer> subMap = dfs(formula.substring(i + 1, j));
                for (String atom : subMap.keySet()) {
                    hm.put(atom, subMap.get(atom) * posAndCnt[1] + hm.getOrDefault(atom, 0));
                } 
            } else {
                int j = i + 1;
                // get an element rest letters
                while (j < len && Character.isLowerCase(formula.charAt(j))) {
                    j++; 
                }
                posAndCnt = positionAndCount(formula, j);
                String atom = formula.substring(i, j);
                hm.put(atom, posAndCnt[1] + hm.getOrDefault(atom, 0)); 
            }

            i = posAndCnt[0];
        }
        
        return hm;
    }


    private int[] positionAndCount(String formula, int cntStart) {
        int cnt = 1;
        int k = cntStart;

        while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
            k++;
        }
        if (k > cntStart) {
            cnt = Integer.parseInt(formula.substring(cntStart, k));
        }

        return new int[] {k, cnt};
    }
}