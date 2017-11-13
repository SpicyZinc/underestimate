/*
idea:
check each line, each character, use string builder to record each char in each line
*/

class RemoveComments {
	public List<String> removeComments(String[] source) {
		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		boolean blockComment = false;
		for (String line : source) {
			for (int i = 0; i < line.length(); i++) {
				if (blockComment) {
					// find '*/', note i NOT out of boundary
					if (line.charAt(i) == '*' && i < line.length() - 1 && line.charAt(i + 1) == '/') {
						blockComment = false;
						// skip '/' on next iteration of i
						i++;
					}
				} else {
					// find '//', ignore the rest characters after //
					if (line.charAt(i) == '/' && i < line.length() - 1 && line.charAt(i + 1) == '/') {
						break;
					}
					else if (line.charAt(i) == '/' && i < line.length() - 1 && line.charAt(i + 1) == '*') {
						blockComment = true;
						// skip '/' on next iteration of i
						i++;
					}
					else {
						sb.append(line.charAt(i));
					}
				}
			}
			if (!blockComment && sb.length() > 0) {
				result.add(sb.toString());
				// reset next line string builder
				sb = new StringBuilder();
			}
		}

		return result;
	}
}