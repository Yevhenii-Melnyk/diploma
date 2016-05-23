package ua.nure.sentiment.entity;

import java.util.List;
import java.util.Map;

public class FastSearchDto {

    private List<Tweet> tweets;
    Map<Integer, Long> positiveTweetsByDay;
    Map<Integer, Long> negativeTweetsByDay;
    private Map<Sentiment, Long> tweetsBySentiment;
    private int total;


    public FastSearchDto(List<Tweet> tweets, Map<Integer, Long> negativeTweetsByDay,
                         Map<Integer, Long> positiveTweetsByDay, Map<Sentiment, Long> tweetsBySentiment, int total) {
        this.negativeTweetsByDay = negativeTweetsByDay;
        this.positiveTweetsByDay = positiveTweetsByDay;
        this.tweets = tweets;
        this.tweetsBySentiment = tweetsBySentiment;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Map<Integer, Long> getNegativeTweetsByDay() {
        return negativeTweetsByDay;
    }

    public void setNegativeTweetsByDay(Map<Integer, Long> negativeTweetsByDay) {
        this.negativeTweetsByDay = negativeTweetsByDay;
    }

    public Map<Integer, Long> getPositiveTweetsByDay() {
        return positiveTweetsByDay;
    }

    public void setPositiveTweetsByDay(Map<Integer, Long> positiveTweetsByDay) {
        this.positiveTweetsByDay = positiveTweetsByDay;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Map<Sentiment, Long> getTweetsBySentiment() {
        return tweetsBySentiment;
    }

    public void setTweetsBySentiment(Map<Sentiment, Long> tweetsBySentiment) {
        this.tweetsBySentiment = tweetsBySentiment;
    }
}
