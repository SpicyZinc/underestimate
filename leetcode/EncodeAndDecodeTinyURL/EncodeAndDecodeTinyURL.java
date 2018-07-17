/*
TinyURL is a URL shortening service where you enter a URL
such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service.
There is no restriction on how your encode/decode algorithm should work.
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

idea:
https://leetcode.com/problems/encode-and-decode-tinyurl/solution/

note elements need to referred to
combined with Design TinyURL problem
*/

public class EncodeAndDecodeTinyURL {
    String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int COUNTER = 1;
    Map<Integer, String> stol = new HashMap<>();
    int base = 62;
    String prefix = "http://tinyurl.com/";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = base10ToBase62(COUNTER);
        stol.put(COUNTER, longUrl);
        COUNTER++;

        return prefix + shortUrl;
    }
    // 10 to any nary format base n
    private String base10ToBase62(int counter) {
        StringBuilder sb = new StringBuilder();
        while (counter > 0) {
            sb.insert(0, elements.charAt(counter % 62));
            counter /= base;
        }

        while (sb.length() != 6) {
            sb.insert(0, '0');
        }
        
        return sb.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        shortUrl = shortUrl.substring(prefix.length());
        int counter = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            char c = shortUrl.charAt(i);
            
            counter = counter * base + getCharVal(c);
        }
        
        return stol.get(counter);
    }
    
    public int getCharVal(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }
}