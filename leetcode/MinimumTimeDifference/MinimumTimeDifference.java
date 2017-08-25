/*
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.

Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

idea:
24 * 60
find min and max of the array,
there could be the minDiff between two elements
note: also could be 24 * 60 - (max - min) 
*/


public class MinimumTimeDifference {
	public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() == 0 || timePoints == null) {
            return 0;
        }
        boolean[] hourSlots = new boolean[24 * 60];
        for (int i = 0; i < timePoints.size(); i++) {
            String time = timePoints.get(i);
            String[] matches = time.split(":");
            int minutes = Integer.parseInt(matches[0]) * 60 + Integer.parseInt(matches[1]);
            if (hourSlots[minutes] == true) {
                return 0;
            }
            hourSlots[minutes] = true;
        }
        
        int minDiff = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int prev = 0;
        for (int i = 0; i < 24 * 60; i++) {
            if (hourSlots[i] == true) {
            	// skip the first one, otherwise i = 0, prev = 0, then minDiff always 0
                if (min != Integer.MAX_VALUE) {
                    minDiff = Math.min(minDiff, i - prev);    
                }
                min = Math.min(min, i);
                max = Math.max(max, i);
                prev = i;
            }
        }

        return Math.min( minDiff, 24 * 60 - (max - min) );
    }


    public int findMinDifference(List<String> timePoints) {
		if (timePoints.size() == 0 || timePoints == null) {
			return 0;
		}

		Collections.sort(timePoints, new Comparator<String>() {
			@Override
			public int compare(String s, String t) {
				String[] sMatches = s.split(":");
				String[] tMatches = t.split(":");
				int sHour = Integer.parseInt(sMatches[0]);
				int sMin = Integer.parseInt(sMatches[1]);
				int tHour = Integer.parseInt(tMatches[0]);
				int tMin = Integer.parseInt(tMatches[1]);
				if (sHour == tHour) {
					return sMin - tMin;
				}
				else {
					return sHour - tHour;
				}
			}
		});
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < timePoints.size() - 1; i++) {
			String curr = timePoints.get(i);
			String next = timePoints.get(i+1);
			if (getDiff(next, curr) < min) {
				min = getDiff(next, curr);
			}
		}

		return min;
    }

    private int getDiff(String s, String t) {
    	String[] sMatches = s.split(":");
		String[] tMatches = t.split(":");
		int sHour = Integer.parseInt(sMatches[0]);
		int sMin = Integer.parseInt(sMatches[1]);
		int tHour = Integer.parseInt(tMatches[0]);
		int tMin = Integer.parseInt(tMatches[1]);

		int diff = 0;
		if (sMin < tMin) {
			diff += (sHour - 1 - tHour) + (60 + sMin - tMin);
		}
		else {
			diff += (sHour - tHour) + (sMin - tMin);
		}

		return diff;
    }
}
