package ua.nure.sentiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import twitter4j.TwitterException;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.service.CoreSentimentAnalysisService;
import ua.nure.sentiment.service.DictionarySentimentService;
import ua.nure.sentiment.service.SparkSentimentService;

@SpringBootApplication
@EnableAsync
public class SpringBootMain {

    public static void main(String[] args) throws TwitterException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootMain.class, args);
    }

}
