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
    private Map<Integer, List<Integer>> followMap;
    private Map<Integer, List<Integer>> tweetMap;
    private List<Integer> tweetList;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        followMap = new HashMap<Integer, List<Integer>>();
        tweetMap = new HashMap<Integer, List<Integer>>();
        tweetList = new ArrayList<Integer>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        List<Integer> list = new ArrayList<Integer>();
        if (tweetMap.containsKey(userId)) {
            list = tweetMap.get(userId);
        }
        list.add(tweetId);
        tweetMap.put(userId, list);
        tweetList.add(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> list = new ArrayList<Integer>();
        if (tweetMap.containsKey(userId)) {
            list = tweetMap.get(userId);
        }
        List<Integer> allList = new ArrayList<Integer>(list);
        List<Integer> followees = new ArrayList<Integer>();
        if (followMap.containsKey(userId)) {
            followees = followMap.get(userId);
        }
        for (int i = 0; i < followees.size(); i++) {
            int followee = followees.get(i);
            if (tweetMap.containsKey(followee)) {
                allList.addAll(tweetMap.get(followee));
            }
        }
        // all tweets posted by self and followees
        Set<Integer> set = new HashSet<Integer>(allList);
        List<Integer> feedList = new ArrayList<Integer>();
        int k = 0;
        for (int i = tweetList.size() - 1; i >= 0; i--) {
            if (k == 10) {
                break;
            }
            int tweetId = tweetList.get(i);
            if (set.contains(tweetId)) {
                feedList.add(tweetId);
                k++;
            }
        }
        return feedList;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        List<Integer> list = new ArrayList<Integer>();
        if (followMap.containsKey(followerId)) {
            list = followMap.get(followerId);
        }
        if (!list.contains(followeeId)) {
            list.add(followeeId);
        }
        followMap.put(followerId, list);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            List<Integer> list = followMap.get(followerId);
            list.remove((Integer) followeeId);
            followMap.put(followerId, list);
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