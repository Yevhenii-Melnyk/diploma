package ua.nure.sentiment.config;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SentimentConfig {

    @Bean
    public StanfordCoreNLP getCoreNLP() {
        Properties nlpProps = new Properties();
        nlpProps.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        return new StanfordCoreNLP(nlpProps);
    }

    @Bean
    public JavaSparkContext sparkContext() {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("Simple Application");
        return new JavaSparkContext(conf);
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sparkContext());
    }


}
