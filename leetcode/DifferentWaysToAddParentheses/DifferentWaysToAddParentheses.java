/*
Given a string of numbers and operators, 
return all possible results from computing all the different possible ways to group numbers and operators. 
The valid operators are +, - and *.

Example 1
Input: "2-1-1"

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]

Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

idea:
backtracking
separate the input string based on operators + - *
m in part1
n in part2
case 1 +: m + n
case 2 -: m - n
case 3 *: m * n

TC: 2^n
*/

public class DifferentWaysToAddParentheses {
    // Mon May 13 20:57:23 2024
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>();
        solve(input, map);
        return map.get(input);
    }

    public List<Integer> solve(String input, Map<String, List<Integer>> map) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> l1 = map.getOrDefault(left, solve(left, map));
                List<Integer> l2 = map.getOrDefault(right, solve(right, map));
                for (Integer i1 : l1) {
                    for (Integer i2 : l2) {
                        int r = 0;
                        switch (c) {
                            case '+':
                                r = i1 + i2;
                                break;
                            case '-':
                                r = i1 - i2;
                                break;
                            case '*':
                                r = i1 * i2;
                                break;
                        }
                        res.add(r);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);

        return res;
    }

    // Mon May 13 20:51:59 2024
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        if ( input.length() == 0 || input == null ) {
            return res;
        }

        for ( int i = 0; i < input.length(); i++ ) {
            char ch = input.charAt(i);
            if (ch != '+' && ch != '-' && ch != '*') {
                continue;
            }

            List<Integer> before = diffWaysToCompute(input.substring(0, i));
            List<Integer> after = diffWaysToCompute(input.substring(i + 1, input.length()));

            for (int a = 0; a < before.size(); a++) {
                for (int b = 0; b < after.size(); b++) {
                    int m = before.get(a);
                    int n = after.get(b);
                    switch (ch) {
                        case '+' :
                            res.add(m + n);
                            break;
                        case '-' :
                            res.add(m - n);
                            break;
                        case '*' :
                            res.add(m * n);
                            break;
                    }
                }
            }
        }
        // this line is so important
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }

        return res;
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<Integer>();

        if (input == null || input.length() == 0) {
            return result;
        }
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '+' && c != '-' && c != '*') {
                continue;
            }
            
            List<Integer> part1Result = diffWaysToCompute(input.substring(0, i));
            List<Integer> part2Result = diffWaysToCompute(input.substring(i + 1, input.length()));
            
            for (Integer m : part1Result) {
                for (Integer n : part2Result) {
                    if (c == '+') {
                        result.add( m + n );
                    } else if (c == '-') {
                        result.add( m - n );
                    } else if (c == '*') {
                        result.add( m * n );
                    }
                }
            }
        }
        
        if (result.size() == 0) {
            result.add(Integer.parseInt(input));
        }
        
        return result;
    }
}
