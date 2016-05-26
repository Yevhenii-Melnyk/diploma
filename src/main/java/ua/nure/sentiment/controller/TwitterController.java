package ua.nure.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.*;
import ua.nure.sentiment.facade.TwitterFacade;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
public class TwitterController {

    @Autowired
    private TwitterFacade twitterFacade;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public FastSearchDto searchTweets(@RequestParam("tags") List<String> tags)
            throws TwitterException, ExecutionException, InterruptedException {
        List<Tweet> tweets = twitterFacade.getTweets(tags, 100);
        Map<Integer, Long> positiveTweetsByDay = twitterFacade.groupTweetsByDayOfWeek(tweets, Sentiment.POSITIVE);
        Map<Integer, Long> negativeTweetsByDay = twitterFacade.groupTweetsByDayOfWeek(tweets, Sentiment.NEGATIVE);
        Map<Sentiment, Long> tweetsBySentiment = twitterFacade.groupTweetsBySentiment(tweets);
        return new FastSearchDto(tweets, negativeTweetsByDay, positiveTweetsByDay, tweetsBySentiment, tweets.size());
    }

    @RequestMapping(value = "geo", method = RequestMethod.POST)
    public List<CountrySentiment> geoSearch(@RequestBody GeoSearchDto dto) throws ExecutionException, InterruptedException {
        List<Tweet> geoTweets = twitterFacade.getGeoTweets(dto.getTags(), dto.getCountries(), 100);
        return twitterFacade.getCountrySentiment(geoTweets);
    }


}
