/*
How would you design a URL shortening service that is similar to TinyURL?

Background:
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Requirements:
For instance, "http://tinyurl.com/4e9iAk" is the tiny url for the page "https://leetcode.com/problems/design-tinyurl".
The identifier (the highlighted part) can be any string with 6 alphanumeric characters containing 0-9, a-z, A-Z.
Each shortened URL must be unique; that is, no two different URLs can be shortened to the same URL.

Note about Questions:
Below are just a small subset of questions to get you started.
In real world, there could be many follow ups and questions possible and the discussion is open-ended (No one true or correct way to solve a problem).
If you have more ideas or questions, please ask in Discuss and we may compile it here!

Questions:
How many unique identifiers possible? Will you run out of unique URLs?
Should the identifier be increment or not? Which is easier to design? Pros and cons?
Mapping an identifier to an URL and its reversal - Does this problem ring a bell to you?
How do you store the URLs? Does a simple flat file database work?
What is the bottleneck of the system? Is it read-heavy or write-heavy?
Estimate the maximum number of URLs a single machine can store.
Estimate the maximum number of queries per second (QPS) for decoding a shortened URL in a single machine.
How would you scale the service? For example, a viral link which is shared in social media could result in a peak QPS at a moment's notice.
How could you handle redundancy? i,e, if a server is down, how could you ensure the service is still operational?
Keep URLs forever or prune, pros/cons? How we do pruning? (Contributed by @alex_svetkin)
What API would you provide to a third-party developer? (Contributed by @alex_svetkin)
If you can enable caching, what would you cache and what's the expiry time? (Contributed by @Humandroid)

idea:
https://segmentfault.com/a/1190000006140476

why 62 based? 26 upper and lower case letters plus 10 digits
use incremental COUNTER as short url, in order to avoid conflicts, just convert it 62 based

62^6=570亿
*/

import java.util.*;

public class URLService {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			// sb.insert(0, i + 1);
			sb.append(i + 1);
		}
		URLService eg = new URLService();
		String url = "https://leetcode.com/problems/design-tinyurl";
		String shortUrl = eg.longToShort(url);
		String longUrl = eg.shortToLong(shortUrl);
		System.out.println(url + " -> " + shortUrl);
		System.out.println(shortUrl + " -> " + longUrl);
		System.out.println("longUrl == url " + longUrl.equals(url));
	}

    Map<String, Integer> ltos;
    Map<Integer, String> stol;
    static int COUNTER;
    String elements;
    String prefix = "http://tiny.url/";

    URLService() {
        this.ltos = new HashMap<>();
        this.stol = new HashMap<>();
        this.COUNTER = 1;
        this.elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;

        return prefix + shorturl;
    }

    public String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        // make sure tiny url at least 6-letter long
        while (sb.length() < 6) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }

    public String shortToLong(String url) {
        url = url.substring(prefix.length());
        int n = base62ToBase10(url);

        return stol.get(n);
    }
    
    public int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }

        return n;
    }

    // '0' - 48
    // 'A' - 65
    // 'a' - 97
    // +10 and + 36 是为了凑成连续的数字
    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }

        return -1;
    }
}