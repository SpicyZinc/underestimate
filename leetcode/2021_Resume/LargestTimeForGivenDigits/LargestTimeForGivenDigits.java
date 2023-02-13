/*
Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.
24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59.
The earliest 24-hour time is 00:00, and the latest is 23:59.

Return the latest 24-hour time in "HH:MM" format.
If no valid time can be made, return an empty string.

Example 1:
Input: arr = [1,2,3,4]
Output: "23:41"
Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.

Example 2:
Input: arr = [5,5,5,5]
Output: ""
Explanation: There are no valid 24-hour times as "55:55" is not valid.

Example 3:
Input: arr = [0,0,0,0]
Output: "00:00"

Example 4:
Input: arr = [0,0,1,0]
Output: "10:00"

Constraints:
arr.length == 4
0 <= arr[i] <= 9

idea: brute force no big deal just 4! = 4 * 3 * 2 * 1 = 24 possibilities
*/

class LargestTimeForGivenDigits {
    public String largestTimeFromDigits(int[] arr) {
        int largestTime = -1;
        // 暴力枚举出每种可能
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != i) {
                    for (int k = 0; k < 4; k++) {
                        if (k != i && k != j) {
                            // 0,1,2,3 总和为6, 故剩下的index为6-
                            int l = 6 - i - j - k;
                            int time = getLargestTimeInMins(arr, i, j, k, l);
                            largestTime = Math.max(time, largestTime);
                        }
                    }
                }
            }
        }

        if (largestTime == -1){
            return "";
        }

        return String.format("%2d:%2d", largestTime / 60, largestTime % 60).replaceAll("\\s", "0");
    }

    /**
     * 判断输入的四个数字按照输入顺序组成的时间是否合法, 如果合法, 返回分钟数;
     */
    public int getLargestTimeInMins(int[] arr, int i, int j, int k, int l) {
        int a = arr[i];
        int b = arr[j];
        int c = arr[k];
        int d = arr[l];

        int hours = a * 10 + b;
        int min = c * 10 + d;

        if (hours < 24 && min < 60) {
            // 返回分钟数
            return hours * 60 + min;
        }

        return -1;
    }
}
