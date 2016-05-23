package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static ua.nure.sentiment.util.DateUtil.today;
import static ua.nure.sentiment.util.DateUtil.week;
import static ua.nure.sentiment.util.DateUtil.weekBefore;

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

    public List<Status> getTweetsForWeek(List<String> tags, int count) throws TwitterException {
        String queryText = tags.stream().map(tag -> "(" + tag + ")").collect(joining(" OR "));
        List<Status> statuses = new ArrayList<>();
        int countByDay = count / 7 + 1;
        List<String> week = week();
        for (int i = 0; i < 7; i++) {
            Query query = new Query(queryText + " -filter:retweets")
                    .since(week.get(i)).until(week.get(i + 1))
                    .lang("en").count(countByDay);
            QueryResult search = twitter.search(query);
            statuses.addAll(search.getTweets());
        }

        return statuses;
    }
}
