/*
You're now a baseball game point recorder.

Given a list of strings, each string can be one of the 4 following types:

Integer (one round's score): Directly represents the number of points you get in this round.
"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
Each round's operation is permanent and could have an impact on the round before and the round after.

You need to return the sum of the points you could get in all the rounds.

Example 1:
Input: ["5","2","C","D","+"]
Output: 15
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get 2 points. The sum is: 7.
Operation 1: The round 2's data was invalid. The sum is: 5.  
Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
Round 4: You could get 5 + 10 = 15 points. The sum is: 30.

Example 2:
Input: ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get -2 points. The sum is: 3.
Round 3: You could get 4 points. The sum is: 7.
Operation 1: The round 3's data is invalid. The sum is: 3.  
Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
Round 5: You could get 9 points. The sum is: 8.
Round 6: You could get -4 + 9 = 5 points. The sum is 13.
Round 7: You could get 9 + 5 = 14 points. The sum is 27.

Note:
The size of the input list will be between 1 and 1000.
Every integer represented in the list will be between -30000 and 30000.

idea:
use LinkedList to add element, removeLast(), peekLast()
run into "C", removeLast()
*/

class BaseballGame {
	public int calPoints(String[] ops) {
	    LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < ops.length; i++) {
        	String op = ops[i];
        	if (op.equals("C")) {
        		list.removeLast();
        	} else if (op.equals("D")) {
        		list.add(list.peekLast() * 2);
        	} else if (op.equals("+")) {
        		int firstToLast = list.get(list.size() - 1);
				int secondToLast = list.get(list.size() - 2);
				list.add(secondToLast + firstToLast);
        	} else {
        		list.add(Integer.parseInt(op));
        	}
        }
       	int sum = 0;
       	for (int num : list) {
       		sum += num;
       	}
       	return sum;
	}
	// failed ["-60","D","-36","30","13","C","C","-33","53","79"]
    public int calPoints(String[] ops) {
        int lastValid = 0;
        int sum = 0;
        for (int i = 0; i < ops.length; i++) {
        	String op = ops[i];
        	if (op.equals("C")) {
        		lastValid = i - 2;
        		ops[i] = Integer.parseInt(ops[i - 1]) * -1 + "C";
        	} else if (op.equals("D")) {
        		ops[i] = Integer.parseInt(ops[lastValid]) * 2 + "";
        	} else if (op.equals("+")) {
        		int firstToLast = Integer.parseInt(ops[i - 1]);
        		int secondToLast = 0;
        		if (ops[i - 2].contains("C")) {
        			secondToLast = Integer.parseInt(ops[i - 4]);
        		} else {
        			secondToLast = Integer.parseInt(ops[i - 2]);
        		}
        		ops[i] = secondToLast + firstToLast + "";
        	}
        }
		for (String s : ops) {
            if (s.contains("C")) {
                s = s.substring(0, s.length() - 1);
            }
        	sum += Integer.parseInt(s);
        }

        return sum;
    }
}