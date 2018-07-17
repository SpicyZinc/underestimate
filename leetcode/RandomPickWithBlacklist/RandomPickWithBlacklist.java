/*
Given a blacklist B containing unique integers from [0, N),
write a function to return a uniform random integer from [0, N) which is NOT in B.
Optimize it such that it minimizes the call to system’s Math.random().

Note:
1 <= N <= 1000000000
0 <= B.length < min(100000, N)
[0, N) does NOT include N. See interval notation.

Example 1:
Input: 
["Solution","pick","pick","pick"]
[[1,[]],[],[],[]]
Output: [null,0,0,0]

Example 2:
Input: 
["Solution","pick","pick","pick"]
[[2,[]],[],[],[]]
Output: [null,1,1,1]

Example 3:
Input: 
["Solution","pick","pick","pick"]
[[3,[1]],[],[],[]]
Output: [null,0,0,2]

Example 4:
Input: 
["Solution","pick","pick","pick"]
[[4,[2]],[],[],[]]
Output: [null,1,3,1]

Explanation of Input Syntax:
The input is two lists: the subroutines called and their arguments.
Solution's constructor has two arguments, N and the blacklist B. pick has no arguments.
Arguments are always wrapped with a list, even if there aren't any.

idea:
https://leetcode.com/problems/random-pick-with-blacklist/discuss/144624/Java-O(B)-O(1)-HashMap
*/

class RandomPickWithBlacklist {

	// TLE
	int N;
	Set<Integer> hs;
	Random r;

	public Solution(int N, int[] blacklist) {
		this.N = N;
		this.hs = new HashSet<Integer>();
		for (int i : blacklist) {
			this.hs.add(i);
		}

		this.r = new Random();
	}

	public int pick() {
		int random = r.nextInt(N);
		while (hs.contains(random)) {
			random = r.nextInt(N);
		}

		return random;
	}
	// MLE
	int N;
	Random r;
	int[] whitelist;

	public Solution(int N, int[] blacklist) {
		this.N = N;
		this.whitelist = new int[N - blacklist.length];
		Set<Integer> hs = new HashSet<Integer>();
		for (int i : blacklist) {
			hs.add(i);
		}
		int j = 0;
		for (int i = 0; i < N; i++) {
			if (!hs.contains(i)) {
				whitelist[j++] = i;
			}
		}

		this.r = new Random();
	}

	public int pick() {
		int randomIdx = r.nextInt(whitelist.length);

		return whitelist[randomIdx];
	}

	// passed, need to come back
	Map<Integer, Integer> m;
    Random r;
    int wlen;

    public Solution(int n, int[] b) {
        m = new HashMap<>();
        r = new Random();
        wlen = n - b.length;
        Set<Integer> w = new HashSet<>();
        for (int i = wlen; i < n; i++) w.add(i);
        for (int x : b) w.remove(x);
        Iterator<Integer> wi = w.iterator();
        for (int x : b)
            if (x < wlen)
                m.put(x, wi.next());
    }

    public int pick() {
        int k = r.nextInt(wlen);
        return m.getOrDefault(k, k);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */