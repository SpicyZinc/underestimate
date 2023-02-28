/*
LeetCode company workers use key-cards to unlock office doors.
Each time a worker uses their key-card, the security system saves the worker's name and the time when it was used. The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.

Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending order alphabetically.
Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "22:51" - "23:52" is not considered to be within a one-hour period.

Example 1:
Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
Output: ["daniel"]
Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").

Example 2:
Input: keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
Output: ["bob"]
Explanation: "bob" used the keycard 3 times in a one-hour period ("21:00", "21:20", "21:30").


Constraints:
1 <= keyName.length, keyTime.length <= 10^5
keyName.length == keyTime.length
keyTime[i] is in the format "HH:MM".
[keyName[i], keyTime[i]] is unique.
1 <= keyName[i].length <= 10
keyName[i] contains only lowercase English letters.

idea:
Map
key is employee name
value is a list of times using key-card, sort them
*/

class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int size = keyName.length;
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String key = keyName[i];
            String time = keyTime[i];

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            String[] matches = time.split(":");
            int minutes = Integer.parseInt(matches[0]) * 60 + Integer.parseInt(matches[1]);

            map.get(key).add(minutes);
        }

        List<String> result = new ArrayList<>();
        for (String s : map.keySet()) {
            List<Integer> current = map.get(s);
            Collections.sort(current);
            for (int i = 0; i < current.size() - 2; i++) {
                int t1 = current.get(i), t2 = current.get(i + 1), t3 = current.get(i + 2);
                // 3 times in a one-hour period
                if (t2 <= t1 + 60 && t3 <= t1 + 60) {
                    result.add(s);
                    break;
                }
            }
        }
        Collections.sort(result);

        return result;
    }
}
