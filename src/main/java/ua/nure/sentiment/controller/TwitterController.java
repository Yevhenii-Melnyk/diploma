package ua.nure.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.FastSearchDto;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.facade.TwitterFacade;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TwitterController {

    @Autowired
    private TwitterFacade twitterFacade;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public FastSearchDto searchTweets(@RequestParam("tags") List<String> tags) throws TwitterException {
        List<Tweet> tweets = twitterFacade.getTweets(tags, 100);
        Map<Integer, Long> positiveTweetsByDay = twitterFacade.groupTweetsByDayOfWeek(tweets, Sentiment.POSITIVE);
        Map<Integer, Long> negativeTweetsByDay = twitterFacade.groupTweetsByDayOfWeek(tweets, Sentiment.NEGATIVE);
        Map<Sentiment, Long> tweetsBySentiment = twitterFacade.groupTweetsBySentiment(tweets);
        return new FastSearchDto(tweets, negativeTweetsByDay, positiveTweetsByDay, tweetsBySentiment, tweets.size());
    }

}
