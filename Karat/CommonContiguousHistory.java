// You are in charge of a display advertising program. Your ads are displayed on websites all over the internet. You have some CSV input data that counts how many times that users have clicked on an ad on each individual domain. Every line consists of a click count and a domain name, like this:

// counts = [ "900,google.com",
//      "60,mail.yahoo.com",
//      "10,mobile.sports.yahoo.com",
//      "40,sports.yahoo.com",
//      "300,yahoo.com",
//      "10,stackoverflow.com",
//      "2,en.wikipedia.org",
//      "1,es.wikipedia.org",
//      "1,mobile.sports" ]

// Write a function that takes this input as a parameter and returns a data structure containing the number of clicks that were recorded on each domain AND each subdomain under it. For example, a click on "mail.yahoo.com" counts toward the totals for "mail.yahoo.com", "yahoo.com", and "com". (Subdomains are added to the left of their parent domain. So "mail" and "mail.yahoo" are not valid domains. Note that "mobile.sports" appears as a separate domain as the last item of the input.)

// Sample output (in any order/format):

// calculateClicksByDomain(counts)
// 1320    com
//  900    google.com
//  410    yahoo.com
//   60    mail.yahoo.com
//   10    mobile.sports.yahoo.com
//   50    sports.yahoo.com
//   10    stackoverflow.com
//    3    org
//    3    wikipedia.org
//    2    en.wikipedia.org
//    1    es.wikipedia.org
//    1    mobile.sports
//    1    sports

// We have some clickstream data that we gathered on our client's website. Using cookies, we collected snippets of users' anonymized URL histories while they browsed the site. The histories are in chronological order and no URL was visited more than once per person.

// Write a function that takes two users' browsing histories as input and returns the longest contiguous sequence of URLs that appears in both.

// Sample input:

// user0 = ["/start.html", "/pink.php", "/register.asp", "/orange.html", "/red.html"]
// user1 = ["/start.html", "/green.html", "/blue.html", "/pink.php", "/register.asp", "/orange.html"]
// user2 = ["/red.html", "/green.html", "/blue.html", "/pink.php", "/register.asp"]
// user3 = ["/blue.html", "/logout.php"]

// Sample output:

// findContiguousHistory(user0, user1)
//    /pink.php
//    /register.asp
//    /orange.html

// findContiguousHistory(user1, user2)
//    /green.html
//    /blue.html
//    /pink.php
//    /register.asp

// findContiguousHistory(user0, user3)
//    (empty)

// findContiguousHistory(user2, user3)
//    /blue.html

// findContiguousHistory(user3, user3)
//    /blue.html
//    /logout.php


import java.io.*;
import java.util.*;

class CommonContiguousHistory {
	public static void main(String[] args) {
		List<String> user0 = Arrays.asList("/start.html", "/pink.php", "/register.asp", "/orange.html", "/red.html" );
		List<String> user1 = Arrays.asList("/start.html", "/green.html", "/blue.html", "/pink.php", "/register.asp", "/orange.html");
		List<String> user2 = Arrays.asList("/red.html", "/green.html", "/blue.html", "/pink.php", "/register.asp");
		List<String> user3 = Arrays.asList("/blue.html", "/logout.php");

		CommonContiguousHistory eg = new CommonContiguousHistory();
		List<String> common = eg.findLongestContiguousHistory(user0, user1);
		// /pink.php
		// /register.asp
		// /orange.html
		System.out.println(common);

		// /green.html
		// /blue.html
		// /pink.php
		// /register.asp
		common = eg.findLongestContiguousHistory(user1, user2);
		System.out.println(common);

		// (empty)
		common = eg.findLongestContiguousHistory(user0, user3);
		System.out.println(common);

		// /blue.html
		common = eg.findLongestContiguousHistory(user2, user3);
		System.out.println(common);

		//    /blue.html
		//    /logout.php
		common = eg.findLongestContiguousHistory(user3, user3);
		System.out.println(common);
	}

    // O(n) n is size of counts[]
    public Map<String, Integer> calculateClicksByDomain(String[] counts) {
        Map<String, Integer> hm = new HashMap<>();
        for (String count : counts) {
            String[] matches = count.split(",");
            
            int times = Integer.parseInt(matches[0]);
            String domain = matches[1];
            
            String[] domains = domain.split("\\.");
            
            String key = "";
            // n * constant dots
            for (int i = domains.length - 1; i >= 0; i--) {
                key = domains[i] + (i == domains.length - 1 ? "" : ".") + key;
                
                hm.put(key, hm.getOrDefault(key, 0) + times);
            }
        }

        return hm;
    }

	public List<String> findContiguousHistory(List<String> a, List<String> b) {
		Map<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < b.size(); i++) {
			hm.put(b.get(i), i);
		}

		List<String> common = new ArrayList<>();
		int prev = -1;

		for (int i = 0; i < a.size(); i++) {
			String history = a.get(i);

			if (hm.containsKey(history)) {
				int idx = hm.get(history);
				if (idx > prev) {
					common.add(history);
					prev = idx;
				}
			}
		}

		return common;
	}
	// 就是Longest Common Subsequence的变形
	public List<String> findLongestContiguousHistory(List<String> a, List<String> b) {
		int m = a.size();
		int n = b.size();

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			dp[i][0] = 0;
		}

		for (int j = 0; j <= n; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.get(i - 1) == b.get(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}

		List<String> longestCommon = new ArrayList<>();
		while (dp[m][n] > 0) {
			if (dp[m][n] == dp[m - 1][n]) {
				m--;
			} else if (dp[m][n] == dp[m][n - 1]) {
				n--;
			} else {
				longestCommon.add(a.get(m - 1));
				m--;
				n--;
			}
		}

		Collections.reverse(longestCommon);
		
		return longestCommon;
	}
}


/* 
Your previous Markdown content is preserved below:

Welcome to the Interview!
 */