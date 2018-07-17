/*
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
For example, with A = "abcd" and B = "cdabcdab".
Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.

idea:
once sb.length > A.length() + B.length(),
if no match should return -1
*/

class RepeatedStringMatch {
	// suppose q times A and q + 1 times A, must in q, q + 1
	// 不断累加A, 当超过B时 如果包含马上返回 最多再加一个A 如果还不包含 return -1
	public int repeatedStringMatch(String A, String B) {
        int n = B.length();
        
        String combined = A;
        int repeatedTimes = 1;

        while (combined.length() < n) {
            combined += A;
            repeatedTimes++;
        }
        
        if (combined.contains(B)) {
            return repeatedTimes;
        }
        if ((combined + A).contains(B)) {
            return repeatedTimes + 1;
        }
        
        return -1;
    }

	public int repeatedStringMatch(String A, String B) {
		StringBuilder sb = new StringBuilder();
		sb.append(A);
		int count = 1;
		
		while (sb.indexOf(B) == -1) {
			if (sb.length() > A.length() + B.length()) {
				return -1;
			}
			sb.append(A);
			count++;
		}

		return count;
	}
}