/*
Given a set of keywords words and a string S, make all appearances of all keywords in S bold.
Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d".
Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:
words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.

idea:
merge interval
note, 找区间时 要while找 找干净
*/

class BoldWordsInString {
	public String boldWords(String[] words, String S) {
		List<Interval> intervals = new ArrayList<Interval>();

		for (String word : words) {
            for (int i = 0; i < S.length(); i++) {
                if (S.startsWith(word, i)) {
                    intervals.add(new Interval(i, i + word.length()));
                }
            }
        }
        
        if (intervals.size() == 0) {
			return S;
		}
        
        Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});

        // merge intervals
        List<Interval> merged = new ArrayList<>();
		merged.add(intervals.get(0));

		for (int i = 1; i < intervals.size(); i++) {
			Interval prev = merged.get(merged.size() - 1);
			Interval curr = intervals.get(i);

			if (curr.start > prev.end) {
				merged.add(curr);
			} else {
				prev.end = Math.max(prev.end, curr.end);
			}
		}
        
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (Interval interval : merged) {
            int start = interval.start;
            int end = interval.end;
            // append str not in dict
            sb.append(S.substring(last, start));
            // append str in dict, wrap it in <b></b>
            sb.append("<b>");
            sb.append(S.substring(start, end));
            sb.append("</b>");
            last = end;
        }
        sb.append(S.substring(last));

        return sb.toString();
	}

	// 要明白why wrong, [start, end) makes sense, easy for further merge
	public String boldWords(String[] words, String S) {
		int n = words.length;

		List<Interval> intervals = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String word = words[i];

			for (int j = 0; j < S.length(); j++) {
				if (S.startsWith(word, j)) {
					intervals.add(new Interval(j, j + word.length() - 1));
				}
			}

			// int index = S.indexOf(word);
			// if (index != -1) {
			// 	Interval interval = new Interval(index, index + word.length() - 1);
			// 	intervals.add(interval);
			// }
		}

		if (intervals.size() == 0) {
			return S;
		}

		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});

		// merge intervals
		List<Interval> merged = new ArrayList<>();
		merged.add(intervals.get(0));

		for (int i = 1; i < intervals.size(); i++) {
			Interval prev = merged.get(merged.size() - 1);
			Interval curr = intervals.get(i);

			if (curr.start > prev.end) {
				merged.add(curr);
			} else {
				prev.end = Math.max(prev.end, curr.end);
			}
		}

		StringBuilder sb = new StringBuilder();

		int left = 0;
		for (Interval interval : merged) {
			int start = interval.start;
			int end = interval.end;

			sb.append(S.substring(left, start));
			sb.append("<b>");
			sb.append(S.substring(start, end + 1));
			sb.append("</b>");

			left = end + 1;
		}

		sb.append(S.substring(left));

		return sb.toString();
	}
}


class Interval {
	int start;
	int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}