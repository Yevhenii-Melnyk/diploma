package ua.nure.sentiment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
public class GeneralConfig {

    @Bean
    public ForkJoinPool searchPool() {
        return new ForkJoinPool(3);
    }

}
