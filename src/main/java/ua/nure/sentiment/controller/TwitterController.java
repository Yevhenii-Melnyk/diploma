package ua.nure.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.facade.TwitterFacade;

import java.util.List;

@RestController
public class TwitterController {

    @Autowired
    private TwitterFacade twitterFacade;

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public List<Tweet> searchTweets(@RequestParam("tags") List<String> tags) throws TwitterException {
        List<Tweet> tweets = twitterFacade.getTweets(tags, 100);
        System.out.println(tweets.size());
        return tweets;
    }

}
