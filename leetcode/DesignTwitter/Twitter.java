/*
Design a simplified version of Twitter where users can post tweets,
follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed.
Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
Each item in the news feed must be posted by users who the user followed or by the user herself.
Tweets must be ordered from most recent to least recent.

follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.

Example:

Twitter twitter = new Twitter();
// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);
// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);
// User 1 follows user 2.
twitter.follow(1, 2);
// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);
// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);
// User 1 unfollows user 2.
twitter.unfollow(1, 2);
// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);

idea:

followMap userId => followerIds;
tweetMap  userId => tweetIds;
tweetList array of tweetIds;

then the rest are straightforward, implement them as requested
*/

public class Twitter {
    Map<Integer, List<Integer>> userTweetsMap;
    Map<Integer, List<Integer>> followMap;
    List<Integer> tweetsList;
    /** Initialize your data structure here. */
    public Twitter() {
        userTweetsMap = new HashMap<Integer, List<Integer>>();
        followMap = new HashMap<Integer, List<Integer>>();
        tweetsList = new ArrayList<Integer>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        // userTweetsMap.put(userId, userTweetsMap.getOrDefault(userId, new ArrayList<Integer>()).add(tweetId));
        List<Integer> postedTweets = userTweetsMap.get(userId);
        if (postedTweets == null) {
            postedTweets = new ArrayList<Integer>();
        }
        postedTweets.add(tweetId);
        userTweetsMap.put(userId, postedTweets);
        tweetsList.add(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // self posted tweets
        List<Integer> selfPosted = userTweetsMap.get(userId);
        if (selfPosted == null) {
            selfPosted = new ArrayList<Integer>();
        }
        // all possible tweets to feed this userId
        List<Integer> allTweets = new ArrayList<Integer>(selfPosted);
        // followees posted tweets
        if (followMap.containsKey(userId)) {
            for (int followee : followMap.get(userId)) {
                if (userTweetsMap.containsKey(followee)) {
                    allTweets.addAll(userTweetsMap.get(followee));
                }
            }
        }
        // get the 10 most recent, use set to avoid duplicate
        Set<Integer> hs = new HashSet<Integer>();
        List<Integer> recentFeed = new ArrayList<Integer>();
        for (int i = tweetsList.size() - 1; i >= 0; i--) {
            // the most recent
            Integer tweetId = tweetsList.get(i);
            if (hs.add(tweetId) && allTweets.contains(tweetId)) {
                recentFeed.add(tweetId);
            }
            if (recentFeed.size() == 10) break; 
        }
        
        return recentFeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        // followMap.put(followerId, followMap.getOrDefault(followerId, new ArrayList<Integer>()).add(followeeId));
        List<Integer> followees = followMap.get(followerId);
        if (followees == null) {
            followees = new ArrayList<Integer>();
        }
        followees.add(followeeId);
        followMap.put(followerId, followees);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> followees = followMap.get(followerId);
        if (followees != null) {
            followees.remove((Integer) followeeId);
            followMap.put(followerId, followees);
        }
    }
}


// waste space to get time, clear
public class Twitter {
    private int postCount;
    private Map<Integer, Integer> tweetCountMap;
    private Map<Integer, List<Integer>> tweetIdMap;
    private Map<Integer, Integer> tweetOwnerMap;
    private Map<Integer, Set<Integer>> followeeMap;
    
    /** Initialize your data structure here. */
    public Twitter() {
        tweetCountMap = new HashMap<Integer, Integer>();
        tweetIdMap = new HashMap<Integer, List<Integer>>();
        tweetOwnerMap = new HashMap<Integer, Integer>();
        followeeMap = new HashMap<Integer, Set<Integer>>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweetCountMap.put(tweetId, ++postCount);
        tweetOwnerMap.put(tweetId, userId);
        getTweetIdList(userId).add(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<Integer>();
        Set<Integer> followeeSet = getFolloweeSet(userId);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return tweetCountMap.get(b) - tweetCountMap.get(a);
            }
        });
        Map<Integer, Integer> idxMap = new HashMap<Integer, Integer>();
        for (Integer followeeId : followeeSet) {
            List<Integer> tweetIdList = getTweetIdList(followeeId);
            int idx = tweetIdList.size() - 1;
            if (idx >= 0) {
                idxMap.put(followeeId, idx - 1);
                pq.add(tweetIdList.get(idx));
            }
        }
        while (result.size() < 10 && !pq.isEmpty()) {
            Integer topTweetId = pq.poll();
            result.add(topTweetId);
            Integer ownerId = tweetOwnerMap.get(topTweetId);
            int idx = idxMap.get(ownerId);
            if (idx >= 0) {
                List<Integer> tweetIdList = getTweetIdList(ownerId);
                pq.add(tweetIdList.get(idx));
                idxMap.put(ownerId, idx - 1);
            }
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        getFolloweeSet(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            getFolloweeSet(followerId).remove(followeeId);
        }
    }

    /** Get a non-empty followee set of an user. */
    private Set<Integer> getFolloweeSet(int userId) {
        Set<Integer> followeeSet = followeeMap.get(userId);
        if (followeeSet == null) {
            followeeSet = new HashSet<Integer>();
            followeeSet.add(userId);
            followeeMap.put(userId, followeeSet);
        }
        return followeeSet;
    }
    
    /** Get a non-empty tweet id list of an user. */
    private List<Integer> getTweetIdList(int userId) {
        List<Integer> tweetIdList = tweetIdMap.get(userId);
        if (tweetIdList == null) {
            tweetIdList = new ArrayList<Integer>();
            tweetIdMap.put(userId, tweetIdList);
        }
        return tweetIdList;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */