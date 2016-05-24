package ua.nure.sentiment.service;

import org.eclipse.jetty.util.BlockingArrayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static ua.nure.sentiment.util.DateUtil.today;
import static ua.nure.sentiment.util.DateUtil.week;
import static ua.nure.sentiment.util.DateUtil.weekBefore;

@Component
public class TwitterService {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ForkJoinPool searchPool;

    public List<Status> getTweets(List<String> tags, int count) throws TwitterException {
        String queryText = tags.stream().map(tag -> "(" + tag + ")").collect(joining(" OR "));
        Query query = new Query(queryText + " -filter:retweets").lang("en").count(count);
        QueryResult search = twitter.search(query);
        return search.getTweets();
    }

    public List<Status> getTweetsForWeek(List<String> tags, int count)
            throws TwitterException, ExecutionException, InterruptedException {
        String queryText = tags.stream().map(tag -> "(" + tag + ")").collect(joining(" OR "));
        int countByDay = count / 7 + 1;
        List<Status> statuses = new BlockingArrayQueue<>(countByDay * 7);
        List<String> week = week();
        searchPool.submit(() -> IntStream.range(0, 7).parallel().forEach(i -> {
            Query query = new Query(queryText + " -filter:retweets")
                    .since(week.get(i)).until(week.get(i + 1))
                    .lang("en").count(countByDay);
            QueryResult search = null;
            try {
                search = twitter.search(query);
                statuses.addAll(search.getTweets());
            } catch (TwitterException e) {
            }
        })).get();

        return statuses;
    }
}
