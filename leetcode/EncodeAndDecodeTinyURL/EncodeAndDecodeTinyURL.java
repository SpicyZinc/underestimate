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

// Mon Feb 27 01:12:13 2023
public class Codec {
    int size = 6;
    int base = 62;
    int counter = 0;
    String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String prefix = "http://tinyurl.com/";

    Map<Integer, String> shortToLong = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = base10To62(counter);
        shortToLong.put(counter, longUrl);
        counter++;
        return prefix + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        shortUrl = shortUrl.substring(prefix.length());
        int counter = 0;
        for (int i = 0; i < shortUrl.length(); i++) {
            char c = shortUrl.charAt(i);
            counter = counter * base + getCharVal(c);
        }
        
        return shortToLong.get(counter);
    }

    public String base10To62(int i) {
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            int index = i % base;
            sb.insert(0, s.charAt(index));
            i /= base;
        }

        while (sb.length() < size) {
            sb.insert(0, '0');
        }

        return sb.toString();
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


public class EncodeAndDecodeTinyURL {
    String elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int COUNTER = 1;
    int base = 62;

    Map<Integer, String> stol = new HashMap<>();
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