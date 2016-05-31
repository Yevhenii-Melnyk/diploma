package ua.nure.sentiment.entity;

import java.util.List;

public class Research {

    private List<String> tags;

    private List<Tweet> tweets;

    public Research(List<String> tags, List<Tweet> tweets) {
        this.tags = tags;
        this.tweets = tweets;
    }

    public Research() {

    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
