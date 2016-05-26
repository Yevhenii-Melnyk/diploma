package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;
import ua.nure.sentiment.entity.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static java.util.stream.Collectors.joining;
import static ua.nure.sentiment.util.DateUtil.week;

@Component
public class TwitterService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ForkJoinPool searchPool;

    public List<Status> getTweets(List<String> tags, int count) throws TwitterException {
        String queryText = tagsToString(tags);
        Query query = new Query(queryText).lang("en").count(count);
        QueryResult search = twitter.search(query);
        return search.getTweets();
    }


    public List<Status> getTweetsForWeek(List<String> tags, int count)
            throws TwitterException, ExecutionException, InterruptedException {
        String queryText = tagsToString(tags);
        int countByDay = count / 7 + 1;
        List<Status> statuses = new ArrayList<>();
        List<String> week = week();
        for (int i = 0; i < 7; i++) {
            Query query = new Query(queryText)
                    .since(week.get(i)).until(week.get(i + 1))
                    .lang("en").count(countByDay);
            QueryResult search = twitter.search(query);
            statuses.addAll(search.getTweets());
        }

        return statuses;
    }

    public Map<Country, List<Status>> getTweetsByGeoLocation(List<String> tags, List<Country> locs, int count) throws TwitterException, ExecutionException, InterruptedException {
        Map<Country, List<Status>> statuses = new ConcurrentHashMap<>();
        searchPool.submit(() -> locs.stream().forEach(loc -> {
            Query query = new Query(tagsToString(tags))
                    .lang("en")
                    .geoCode(loc.getLocation(), 500, Query.Unit.km.toString()).count(count);
            QueryResult result = null;
            try {
                result = twitter.search(query);
                statuses.put(loc, result.getTweets());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        })).get();

        return statuses;
    }


    private String tagsToString(List<String> tags) {
        return tags.stream().map(tag -> "(" + tag + ")").collect(joining(" OR ")) + " -filter:retweets";
    }

}
