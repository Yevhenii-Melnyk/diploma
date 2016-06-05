package ua.nure.sentiment.config;

import com.github.scribejava.core.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class TwitterConfig {

    @Value("${consumer.appId}")
    private String consumerKey;

    @Value("${consumer.appSecret}")
    private String consumerSecret;

    @Value("${access.token}")
    private String accessToken;

    @Value("${access.secret}")
    private String accessSecret;

    @Bean
    public Twitter getTwitter() {
        twitter4j.conf.Configuration twitterConfig = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret)
                .build();
        TwitterFactory twitterFactory = new TwitterFactory(twitterConfig);
        return twitterFactory.getInstance();
    }

    @Bean
    @Scope("prototype")
    public TwitterStream getTwitterStream() {
        twitter4j.conf.Configuration twitterConfig = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessSecret)
                .build();
        return new TwitterStreamFactory(twitterConfig).getInstance();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ServiceBuilder twitterOauthService() {
        return new ServiceBuilder()
                .apiKey(consumerKey)
                .apiSecret(consumerSecret);

    }

}
