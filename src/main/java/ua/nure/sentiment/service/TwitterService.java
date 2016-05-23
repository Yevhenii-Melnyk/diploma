package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Component
public class TwitterService {

    @Autowired
    private Twitter twitter;

    public List<Status> getTweets(List<String> tags, int count) throws TwitterException {
        String queryText = tags.stream().map(tag -> "(" + tag + ")").collect(joining(" OR "));
        Query query = new Query(queryText + " -filter:retweets").lang("en").count(count);
        QueryResult search = twitter.search(query);
        return search.getTweets();
    }

}
