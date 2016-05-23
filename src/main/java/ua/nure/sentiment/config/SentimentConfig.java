package ua.nure.sentiment.config;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Configuration
public class SentimentConfig {

    @Bean
    @Scope("prototype")
    public StanfordCoreNLP getCoreNLP() {
        Properties nlpProps = new Properties();
        nlpProps.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        return new StanfordCoreNLP(nlpProps);
    }


}
