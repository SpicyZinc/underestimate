/*
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
Each LED represents a zero or one, with the least significant bit on the right.

For example, the above binary watch reads "3:25".
Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

idea:
recursion for sure
typical dfs recursive search, I did it by myself
*/

public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
		String[] times = new String[] {"1h","2h","4h","8h","1m","2m","4m","8m","16m","32m"};
		List<String> total = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		dfs(0, num, times, result, total);

		return total;
    }

    private void dfs(int start, int num, String[] times, List<String> result, List<String> total) {
    	if (result.size() == num) {
    		String timeRepresent = getTime(result);
    		if (timeRepresent != null) {
    			total.add(timeRepresent);
    		}
    		return;
    	}
    	for (int i = start; i < times.length; i++) {
    		String time = times[i];
    		result.add(time);
    		dfs(i + 1, num, times, result, total);
    		result.remove(result.size() - 1);
    	}
    }

    private String getTime(List<String> list) {
    	int hours = 0;
    	int minutes = 0;
    	for (int i = 0; i < list.size(); i++) {
    		String time = list.get(i);
    		if (time.contains("h")) {
    			String[] h = time.split("h");
    			hours += Integer.parseInt(h[0]);
    		}
    		if (time.contains("m")) {
    			String[] m = time.split("m");
    			minutes += Integer.parseInt(m[0]);
    		}
    	}
    	String connection = minutes < 10 ? ":0" : ":";
    	if (minutes < 60 && hours < 12) {
    		return hours + connection + minutes;
    	}
    	return null;
    }
}
