package com.piyush.oodesign.Twitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user and is able to see the 10 most recent tweets in the user’s news feed.
 * Your design should support the following methods:
 * <p>
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user’s news feed.
 * Each item in the news feed must be posted by users who the user followed or by the user herself.
 * Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 */

public class TwitterMain {

  public static void main(String... args) {
    Twitter twitter = new Twitter();

    // User 1 posts a new tweet (id = 5).
    twitter.postTweet(1, 5);

    // User 1's news feed should return a list with 1 tweet id -> [5].
    Integer[] tweets = twitter.getNewsFeed(1);
    System.out.println(Arrays.toString(tweets));

    // User 1 follows user 2.
    twitter.follow(1, 2);

    // User 2 posts a new tweet (id = 6).
    twitter.postTweet(2, 6);

    // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
    // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    tweets = twitter.getNewsFeed(1);
    System.out.println(Arrays.toString(tweets));

    // User 1 un-follows user 2.
    twitter.unFollow(1, 2);

    // User 1's news feed should return a list with 1 tweet id -> [5],
    // since user 1 is no longer following user 2.
    tweets = twitter.getNewsFeed(1);
    System.out.println(Arrays.toString(tweets));
  }
}

class Twitter {

  public void postTweet(int userId, int tweetId) {
    Tweet.postTweet(userId, tweetId);
  }

  public Integer[] getNewsFeed(int userId) {
    return UserTimelineBucket.getBucket(userId);
  }

  public void follow(int follower, int following) {
    UserMapping.follow(follower, following);
  }

  public void unFollow(int follower, int unFollowing) {
    UserMapping.unFollow(follower, unFollowing);
    UserTimelineBucket.removeFromBucket(follower, unFollowing);
  }
}

class Tweet {

  public static void postTweet(int userId, int tweetId) {
    // .. Other tasks you might want to do.
    UserTimelineBucket.addToBucket(userId, tweetId);
  }

}

class UserTimelineBucket {

  private static HashMap<Integer, LRUCache> bucketMap = new HashMap<>();
  private static HashMap<Integer, Queue<Integer>> userTweets = new HashMap<>();

  public static void addToBucket(int userId, int tweetId) {

    Queue<Integer> currTweetsOfUser = userTweets.getOrDefault(userId, new LinkedList<Integer>());
    currTweetsOfUser.add(tweetId);
    userTweets.put(userId, currTweetsOfUser);

    LRUCache currCacheOfUser = bucketMap.getOrDefault(userId, new LRUCache());
    currCacheOfUser.add(tweetId);
    bucketMap.put(userId, currCacheOfUser);

    HashSet<Integer> currFollowers = UserMapping.getFollowers(userId);
    for (Object currFollower : currFollowers) {
      int followerUserId = (int) currFollower;
      currCacheOfUser = bucketMap.getOrDefault(followerUserId, new LRUCache());
      currCacheOfUser.add(tweetId);
      bucketMap.put(followerUserId, currCacheOfUser);
    }
  }

  public static void removeFromBucket(int userId, int unFollowing){
    Queue<Integer> currTweetsOfUnFollowingUser = userTweets.get(unFollowing);
    LRUCache currCacheOfUser = bucketMap.getOrDefault(userId, new LRUCache());
    while (!currTweetsOfUnFollowingUser.isEmpty()){
      currCacheOfUser.remove(currTweetsOfUnFollowingUser.poll());
    }
  }

  public static Integer[] getBucket(int userId) {
    LinkedHashSet<Integer> tweetsSet = bucketMap.getOrDefault(userId, new LRUCache()).getSet();
    Integer[] tweetsArr = new Integer[tweetsSet.size()];
    return tweetsSet.toArray(tweetsArr);
  }

}

class UserMapping {

  private static HashMap<Integer, HashSet<Integer>> followingMap = new HashMap<>();
  private static HashMap<Integer, HashSet<Integer>> followersMap = new HashMap<>();

  public static void follow(int follower, int newFollowing) {
    HashSet<Integer> following = getFollows(follower);
    following.add(newFollowing);
    followingMap.put(follower, following);

    HashSet<Integer> followers = getFollowers(newFollowing);
    followers.add(follower);
    followersMap.put(newFollowing, followers);
  }

  public static void unFollow(int follower, int unFollowing) {
    HashSet<Integer> following = getFollows(follower);
    following.remove(unFollowing);
    followingMap.put(follower, following);

    HashSet<Integer> followers = getFollowers(unFollowing);
    followers.remove(follower);
    followersMap.put(unFollowing, followers);
  }

  private static HashSet<Integer> getFollows(int userId) {
    return followingMap.getOrDefault(userId, new HashSet<>());
  }

  public static HashSet<Integer> getFollowers(int userId) {
    return followersMap.getOrDefault(userId, new HashSet<>());
  }
}

class LRUCache {

  private int capacity = 10;
  private LinkedHashSet<Integer> set = new LinkedHashSet<>();

  public void add(int tweetId) {
    if (set.size() >= 10) {
      int oldestTweetId = set.iterator().next();
      set.remove(oldestTweetId);
    }
    if (set.contains(tweetId)) { // When user is updating a tweet.
      set.remove(tweetId);
      set.add(tweetId);
    } else {
      set.add(tweetId);
    }
  }

  public void remove(int tweetId){
    set.remove(tweetId);
  }

  public LinkedHashSet<Integer> getSet() {
    return set;
  }

}