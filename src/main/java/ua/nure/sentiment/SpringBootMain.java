package ua.nure.sentiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import twitter4j.*;
import ua.nure.sentiment.service.TwitterService;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootMain {

    public static void main(String[] args) throws TwitterException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootMain.class, args);

    }

}
