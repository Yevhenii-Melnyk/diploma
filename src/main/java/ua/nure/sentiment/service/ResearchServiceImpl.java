package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import twitter4j.*;
import ua.nure.sentiment.converter.TweeterConverter;
import ua.nure.sentiment.entity.Research;
import ua.nure.sentiment.entity.Tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component
public class ResearchServiceImpl implements ResearchService {

    private Map<String, Research> map = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private TweeterConverter tweeterConverter;

    @Async
    public void doResearch(List<String> tags, String id) {
        List<Tweet> tweets = new ArrayList<>();
        map.put(id, new Research(tags, tweets));
        FilterQuery fq = new FilterQuery();
        fq.track(tags.toArray(new String[0]));
        fq.language("en");

        TwitterStream twitterStream = twitterStream();
        twitterStream.addListener(new StreamListener(id, tweets));
        twitterStream.filter(fq);
    }

    public Research getTweets(String id) {
        return map.get(id);
    }

    @Lookup
    public TwitterStream twitterStream() {
        return null;
    }

    private String tagsToString(List<String> tags) {
        return tags.stream().map(tag -> "(" + tag + ")").collect(joining(" AND ")) + " -filter:retweets";
    }

    private class StreamListener implements StatusListener {

        private String researchId;
        private List<Tweet> tweets;

        StreamListener(String researchId, List<Tweet> tweets) {
            this.researchId = researchId;
            this.tweets = tweets;
        }

        @Override
        public void onStatus(Status status) {
            Tweet tweet = tweeterConverter.convert(status);
            tweets.add(tweet);
            template.convertAndSend("/tweet/" + researchId, tweet);
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
        }

        @Override
        public void onStallWarning(StallWarning warning) {
        }

        @Override
        public void onException(Exception ex) {
        }
    }
}
