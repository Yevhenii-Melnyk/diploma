package ua.nure.sentiment.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.service.CoreSentimentAnalysisService;
import ua.nure.sentiment.service.DictionarySentimentService;
import ua.nure.sentiment.service.SparkSentimentService;

import java.util.Arrays;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@Component
public class TweeterConverter {

    @Autowired
    private CoreSentimentAnalysisService coreSentimentAnalysisService;

    @Autowired
    private DictionarySentimentService dictionarySentimentService;

    @Autowired
    private SparkSentimentService sparkSentimentService;

    public Tweet convert(Status status) {
        Tweet tweet = new Tweet();
        tweet.setId(status.getId());
        tweet.setCreatedAt(status.getCreatedAt());
        tweet.setHashTags(Arrays.stream(status.getHashtagEntities()).map(HashtagEntity::getText).collect(toList()));
        tweet.setRetweetCount(status.getRetweetCount());
        tweet.setText(status.getText());
        tweet.setUserName(status.getUser().getName());

        setMedia(status, tweet);
        setLink(status, tweet);
//        tweet.setCoreSentiment(coreSentimentAnalysisService.detectSentiment(status.getText()));
//        tweet.setDictionarySentiment(dictionarySentimentService.detectSentiment(status.getText()));
//        tweet.setLogisticSentiment(sparkSentimentService.detectSentiment(status.getText()));

        tweet.setSentiment(getSentiment());
        return tweet;
    }

    private void setLink(Status status, Tweet tweet) {
        tweet.setLink("https://twitter.com/" + status.getUser().getScreenName()
                + "/status/" + status.getId());
    }

    private void setMedia(Status status, Tweet tweet) {
        MediaEntity[] mediaEntities = status.getMediaEntities();
        if (mediaEntities.length > 0) {
            tweet.setMediaUrl(mediaEntities[0].getMediaURL());
        }
    }

    private Sentiment getSentiment() {
        if (new Random().nextBoolean())
            return Sentiment.NEGATIVE;
        if (new Random().nextBoolean())
            return Sentiment.NEUTRAL;
        else return Sentiment.POSITIVE;
    }

}
