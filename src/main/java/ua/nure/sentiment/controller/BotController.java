package ua.nure.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.entity.Tweet;
import ua.nure.sentiment.facade.TwitterFacade;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/bot")
public class BotController {

    @Autowired
    private TwitterFacade twitterFacade;

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public List<Tweet> sample(@RequestParam("tags") String tags, @RequestParam("count") Integer count)
            throws TwitterException, ExecutionException, InterruptedException {
        return twitterFacade.getTweets(Arrays.asList(tags.split(",")), count);
    }

    @RequestMapping(value = "/opinion", method = RequestMethod.GET)
    public List<Tweet> opinion(@RequestParam("tags") String tags)
            throws TwitterException, ExecutionException, InterruptedException {
        return twitterFacade.getOpinion(Arrays.asList(tags.split(",")));
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Sentiment check(@RequestParam("text") String text)
            throws TwitterException, ExecutionException, InterruptedException {
        return twitterFacade.getSentiment(text);
    }


}
