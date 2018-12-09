/*
Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2.
subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system.
For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format,
return the length of the longest absolute path to file in the abstracted file system.
If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.

idea:
1. use hashmap
2. stack, debug as below
need to go back, did not understand
dir
    \tsubdir1
    \tsubdir2
        \t\tfile.ext

stack is to save each depth length
if stack size is longer than depth length, pop()

st [0]

tmp = [dir]
last = dir
st dir.length + 1 = 4
st [0, 4]

tmp = ["", subdir1]
last = subdir1
st subdir1.length + 1 = 8
st [0, 4, 8]

tmp = ["", subdir2]
last = subdir2
pop till st [0, 4]
st subdir2.length() + 1 = 8
st [0, 4, 12]

tmp = ["", "", "file.ext"]
last = "file.ext"
st "file.ext".length + 1 = 9
st [0, 4, 12, 21]

ret = 20

dir/subdir2/file.ext = 20
*/

import java.util.*;

public class LongestAbsoluteFilePath {
	public static void main(String[] args) {
		// String s = "\t\tfile.ext";
        // String s = "\tsubdir1";
		String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		String[] temp = s.split("\n");

		for (String ss : temp) {
			System.out.println(ss);
		}

        LongestAbsoluteFilePath eg = new LongestAbsoluteFilePath();
        eg.lengthLongestPath(s);
	}

    // hashmap depth => length of all characters till this depth
    // depth == # of "/t/t", not consecutively increase
    public int lengthLongestPath(String input) {
        int maxlen = 0;
        Map<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 0);

        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] names = line.split("\t");
            String name = names[names.length - 1];
            int depth = line.length() - name.length(); // # of "/t/t"
            if (name.contains(".")) {
                maxlen = Math.max(maxlen, hm.get(depth) + name.length());
            } else {
                hm.put(depth + 1, hm.get(depth) + name.length() + 1);
            }
        }

        return maxlen;
    }

    public int lengthLongestPath(String input) {
        int result = 0;
        String[] paths = input.split("\n");
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        for (String path : paths) {
            String[] tmp = path.split("\t");
            String last = tmp[tmp.length - 1];
            while (stack.size() > tmp.length) {
            	stack.pop();
            }
            stack.push(stack.peek() + last.length() + 1); // plus 1 is for "/"
            // if last is a file not directory
            if (last.contains(".")) {
            	result = Math.max(result, stack.peek() - 1); // minus 1 is for n parts will have n-1 "/"
            }
        }

        return result;
    }
}

