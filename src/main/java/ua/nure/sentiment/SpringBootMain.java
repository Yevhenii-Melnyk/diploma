package ua.nure.sentiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import twitter4j.*;

@SpringBootApplication
@EnableAsync
public class SpringBootMain {

    public static void main(String[] args) throws TwitterException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootMain.class, args);
    }

}
