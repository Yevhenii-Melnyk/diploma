package ua.nure.sentiment.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.service.TwitterService;
import ua.nure.sentiment.util.SentimentAnalysisService;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TwitterFacade {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    public List<Tweet> getTweets(List<String> tags, int count) throws TwitterException {
        return twitterService.getTweets(tags, count).stream()
                .map(status -> {
                    Tweet tweet = new Tweet();
                    tweet.setId(status.getId());
                    tweet.setCreatedAt(status.getCreatedAt());
                    tweet.setHashTags(Arrays.stream(status.getHashtagEntities()).map(HashtagEntity::getText).collect(toList()));
                    tweet.setRetweetCount(status.getRetweetCount());
                    tweet.setText(status.getText());
                    tweet.setUserName(status.getUser().getScreenName());
                    tweet.setSentiment(sentimentAnalysisService.detectSentiment(status.getText()));
                    return tweet;
                })
                .collect(toList());
    }


}
