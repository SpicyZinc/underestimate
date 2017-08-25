/*
You are given a string representing an attendance record for a student.
The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True

Example 2:
Input: "PPALLL"
Output: False

idea:
direct thought
3 consecutive L
count number of A
*/

public class StudentAttendanceRecord {
    public boolean checkRecord(String s) {
        int[] chars = new int[256];
        chars[s.charAt(0) - '0']++;
        for (int i = 1; i < s.length(); i++) {
        	char prev = s.charAt(i - 1);
        	char curr = s.charAt(i);
        	chars[curr - '0']++;
        	if (prev == 'L' && i < s.length() - 1 && prev == curr && curr == s.charAt(i + 1)) {
        		return false;
        	}
        }
        return chars['A' - '0'] >= 2 ? false : true;
    }
}