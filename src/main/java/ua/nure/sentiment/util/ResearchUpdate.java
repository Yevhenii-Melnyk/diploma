package ua.nure.sentiment.util;

import ua.nure.sentiment.entity.Research;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.entity.Tweet;

import java.util.List;
import java.util.Map;

public class ResearchUpdate {

    List<Tweet> tweets;
    private Map<Sentiment, Long> tweetsBySentiment;
    private List<Research.LineData> lineData;

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

    public void setLineData(List<Research.LineData> lineData) {
        this.lineData = lineData;
    }

    public List<Research.LineData> getLineData() {
        return lineData;
    }
}
