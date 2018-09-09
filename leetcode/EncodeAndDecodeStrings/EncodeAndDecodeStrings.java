/*
Design an algorithm to encode a list of strings to a string.
The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:
string encode(vector<string> strs) {
    // ... your code
    return encoded_string;
}

Machine 2 (receiver) has the function:
vector<string> decode(string s) {
    //... your code
    return strs;
}

So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:
vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.

idea:
need to encode each string length
*/

public class EncodeAndDecodeStrings {
    public String encode(List<String> strs) {
        StringBuilder output = new StringBuilder();
        for (String str : strs) {
            output.append(str.length() + "#" + str);
        }

        return output.toString();
    }

    public List<String> decode(String s) {
        List<String> result = new LinkedList<String>();
        int start = 0;
        while (start < s.length()) {
            // 找到从start开始的第一个#, 这个#前面是长度
            int idx = s.indexOf('#', start);
            int size = Integer.parseInt(s.substring(start, idx));
            // 根据这个长度截取子串
            result.add(s.substring(idx + 1, idx + 1 + size));
            // 更新start为子串后面一个位置
            start = idx + 1 + size;
        }

        return result;
    }

    // this version passed lintcode
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            sb.append(i == strs.size() - 1 ? str : str + "-");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        String[] matches = s.split("-");
        for (String str : matches) {
            result.add(str);
        }
        return result;
    }
}