package ua.nure.sentiment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.sentiment.entity.Sentiment;
import ua.nure.sentiment.util.AfinnDictionary;

import static ua.nure.sentiment.util.TextCleaner.cleanTweet;

@Component
public class DictionarySentimentService {

    @Autowired
    private AfinnDictionary dictionary;

    public Sentiment detectSentiment(String text) {
        String[] words = cleanTweet(text);
        int sum = 0;
        for (String word : words) {
            sum += dictionary.getDictionary().getOrDefault(word, 0);
            System.out.println(word + " : " + dictionary.getDictionary().getOrDefault(word, 0));
        }
        return Sentiment.convert(sum);
    }


}
