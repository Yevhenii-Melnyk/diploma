package ua.nure.sentiment.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.service.DictionarySentimentService;
import ua.nure.sentiment.service.SparkSentimentService;
import ua.nure.sentiment.service.TwitterService;
import ua.nure.sentiment.service.CoreSentimentAnalysisService;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TwitterFacade {

    @Autowired
    private TwitterService twitterService;

    @Autowired
    private CoreSentimentAnalysisService coreSentimentAnalysisService;

    @Autowired
    private DictionarySentimentService dictionarySentimentService;

    @Autowired
    private SparkSentimentService sparkSentimentService;

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
                    tweet.setCoreSentiment(coreSentimentAnalysisService.detectSentiment(status.getText()));
                    tweet.setDictionarySentiment(dictionarySentimentService.detectSentiment(status.getText()));
                    tweet.setLogisticSentiment(sparkSentimentService.detectSentiment(status.getText()));
                    return tweet;
                })
                .collect(toList());
    }


}
