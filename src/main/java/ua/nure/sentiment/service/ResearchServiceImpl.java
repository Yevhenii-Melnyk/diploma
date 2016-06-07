package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import twitter4j.*;
import ua.nure.sentiment.converter.TweeterConverter;
import ua.nure.sentiment.entity.Research;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.facade.TwitterFacade;
import ua.nure.sentiment.util.ResearchUpdate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static ua.nure.sentiment.util.DateUtil.dayBefore;

@Component
public class ResearchServiceImpl implements ResearchService {

    @Autowired
    private TwitterFacade twitterFacade;

    private Map<String, Research> map = new ConcurrentHashMap<>();

    {
        Research research = new Research();
        research.setCreatedAt(dayBefore(1));
        research.setTags(Arrays.asList("Spring", "Mongo", "MEAN stack"));
        research.setUserName("Евгений");
        map.put("1234", research);

        Research research2 = new Research();
        research2.setCreatedAt(dayBefore(2));
        research2.setTags(Arrays.asList("Akka", "Scala", "Learn in 15 minutes"));
        research2.setUserName("Евгений");
        map.put("12345", research2);
    }

    public ResearchServiceImpl() {
    }

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void setUp() {
        Updater runnable = new Updater();
        scheduler.scheduleAtFixedRate(runnable, 5, 5, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
    }

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private TweeterConverter tweeterConverter;

    @Async("searchPool")
    public void doResearch(List<String> tags, String id, String userName, Boolean isPublic) {
        List<Tweet> tweets = Collections.synchronizedList(new ArrayList<>());
        map.put(id, new Research(id, tags, tweets, userName, isPublic));
        FilterQuery fq = new FilterQuery();
        fq.track(tags.toArray(new String[0]));
        fq.language("en");

        TwitterStream twitterStream = twitterStream();
        twitterStream.addListener(new StreamListener(tweets));
        twitterStream.filter(fq);
    }

    public Research getTweets(String id) {
        return map.get(id);
    }

    @Override
    public List<Research> getPublicResearches() {
        return map.values().stream()
                .filter(Objects::nonNull)
                .filter(Research::getPublic)
                .sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    @Override
    public List<Research> getResearchByUserName(String name) {
        return map.values()
                .stream()
                .filter(Objects::nonNull)
                .filter(r -> name.equals(r.getUserName()))
                .sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    @Lookup
    public TwitterStream twitterStream() {
        return null;
    }

    private class Updater implements Runnable {

        @Override
        public void run() {
            for (Research research : map.values()) {
                try {
                    ResearchUpdate researchUpdate = new ResearchUpdate();
                    researchUpdate.setTweets(research.getTweets());
                    Map<Sentiment, Long> tweetsBySentiment = twitterFacade.groupTweetsBySentiment(research.allTweets());
                    researchUpdate.setTweetsBySentiment(tweetsBySentiment);

                    research.addLineData(new Research.LineData(tweetsBySentiment.getOrDefault(Sentiment.POSITIVE, 0L).intValue(),
                            tweetsBySentiment.getOrDefault(Sentiment.NEGATIVE, 0L).intValue(),
                            tweetsBySentiment.getOrDefault(Sentiment.NEUTRAL, 0L).intValue()
                    ));
                    researchUpdate.setLineData(research.getLineDatas());

                    template.convertAndSend("/tweet/" + research.getId(), researchUpdate);
                } catch (Throwable th) {
                    System.out.println(th.getMessage());
                }
            }
        }
    }

    private class StreamListener implements StatusListener {

        private List<Tweet> tweets;

        StreamListener(List<Tweet> tweets) {
            this.tweets = tweets;
        }

        @Override
        public void onStatus(Status status) {
            Tweet tweet = tweeterConverter.convert(status);
            tweets.add(tweet);

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
