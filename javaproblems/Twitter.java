package javaproblems;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// https://leetcode.com/problems/design-twitter/
public class Twitter {
    /**
     * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
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

     */

    class Tweet{
        int tweetId;
        int creationTime;
        int createdBy;
        Tweet(int tweetId, int userId, int time){
            this.tweetId = tweetId;
            this.createdBy = userId;
            this.creationTime = time;
        }
    }
    
    class TweetTimeComparator implements Comparator<Tweet>{
        public int compare(Tweet first, Tweet second){
            return Integer.compare(first.creationTime, second.creationTime);
        }
    }
    
    /** Initialize your data structure here. */
    Map<Integer, LinkedList<Tweet>> posts;
    Map<Integer, Set<Integer>> followedUsers;
    AtomicInteger time; // so that it can be updated by multiple threads
    public Twitter() {
        posts = new HashMap<>();
        followedUsers = new HashMap<>();
        time = new AtomicInteger(0);
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        int currTime = time.incrementAndGet();
        Tweet tweet = new Tweet(tweetId, userId, currTime);
        if(posts.containsKey(userId)){
            posts.get(userId).add(tweet);
        } else{
            LinkedList<Tweet> userPosts = new LinkedList<>();
            userPosts.addFirst(tweet);
            posts.put(userId, userPosts);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> users = new LinkedList<>();
        users.add(userId);
        // get people followed by user
        Set<Integer> followed = followedUsers.get(userId);
        System.out.println(followed);
        if(followed != null){
            users.addAll(followed);   
        }
        // get 10 most recent tweets from all follower posts + user's posts
        PriorityQueue<Tweet> recentTweets = new PriorityQueue<>(new TweetTimeComparator());
        int k=0;
        for(int user : users){
            LinkedList<Tweet> userPosts = posts.get(user);
            if(userPosts != null){
                for(Tweet tweet: userPosts){
                    if(k<10){
                        recentTweets.add(tweet);
                        k+=1;
                    } else if(tweet.creationTime > recentTweets.peek().creationTime){
                        recentTweets.poll();
                        recentTweets.add(tweet);
                    } // we can break for else: because any further tweet will have smaller creation time than this one.
                }
            }
        }
        // heap - had it in reverse order (min time at the top)
        LinkedList<Integer> finalTweets = new LinkedList<>();
        while(recentTweets.size() > 0){
            finalTweets.addFirst(recentTweets.poll().tweetId);
        }
        return finalTweets;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        if(followedUsers.containsKey(followerId)){
            followedUsers.get(followerId).add(followeeId);
        } else {
            Set<Integer> userFollowers = new HashSet<>();
            userFollowers.add(followeeId);
            followedUsers.put(followerId, userFollowers);
        }
        System.out.println(followedUsers.get(followerId));
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followedUsers.containsKey(followerId)){
            followedUsers.get(followerId).remove(followeeId);
        }
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