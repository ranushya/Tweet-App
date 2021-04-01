package com.tweetapp.tweetproject.model;


public class TweetsModel {
    
    private int userId;
    private String tweet;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getTweet() {
        return tweet;
    }
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
    public TweetsModel(int userId, String tweet) {
        this.userId = userId;
        this.tweet = tweet;
    }
    public TweetsModel() {
    }
    
}
