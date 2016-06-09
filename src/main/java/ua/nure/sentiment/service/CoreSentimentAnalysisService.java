package ua.nure.sentiment.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ua.nure.sentiment.entity.Sentiment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static ua.nure.sentiment.util.TextCleaner.cleanTweet;

@Component
public class CoreSentimentAnalysisService {

    public Sentiment detectSentiment(String message) {
        message = Arrays.stream(cleanTweet(message)).collect(joining(" "));

        StanfordCoreNLP pipeline = getCoreNLP();

        List<Integer> sentiments = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();

        int longest = 0;
        int mainSentiment = 0;

        Annotation annotation = pipeline.process(message);
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
            String partText = sentence.toString();

            if (partText.length() > longest) {
                mainSentiment = sentiment;
                longest = partText.length();
            }

            sentiments.add(sentiment);
            sizes.add(partText.length());
        }

        double averageSentiment = -1;
        if (sentiments.size() > 0)
            averageSentiment = sentiments.stream().mapToDouble(Integer::doubleValue).sum() / sentiments.size();

        List<Integer> weightedSentiments = new ArrayList<>(sentiments.size());
        for (int i = 0; i < sentiments.size(); i++) {
            weightedSentiments.add(sentiments.get(i) * sizes.get(i));
        }
        double weightedSentiment = weightedSentiments.stream().mapToDouble(Integer::doubleValue).sum() /
                sizes.stream().mapToInt(Integer::intValue).sum();

//        System.out.println(averageSentiment);
//        System.out.println(weightedSentiment);
//        System.out.println(mainSentiment);

        Sentiment result = Sentiment.NEUTRAL;
        if (weightedSentiment > 2)
            result = Sentiment.POSITIVE;
        else if (weightedSentiment <= 2)
            result = Sentiment.NEGATIVE;
        return result;
    }

    @Lookup
    public StanfordCoreNLP getCoreNLP() {
        return null;
    }


}
