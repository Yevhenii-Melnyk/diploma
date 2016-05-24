package ua.nure.sentiment.entity;

import java.util.Date;
import java.util.List;

public class Tweet {

    private long id;
    private String text;
    private Date createdAt;
    private int retweetCount;
    private List<String> hashTags;
    private String userName;
    private Sentiment coreSentiment;
    private Sentiment dictionarySentiment;
    private Sentiment logisticSentiment;
    private Sentiment sentiment;

    public Sentiment getSentiment() {
        if (sentiment != null)
            return sentiment;
        return Sentiment.convert(coreSentiment.getValue() + dictionarySentiment.getValue() + logisticSentiment.getValue());
    }

    public void setSentiment(Sentiment sentiment) {
        this.sentiment = sentiment;
    }

    public Sentiment getLogisticSentiment() {
        return logisticSentiment;
    }

    public void setLogisticSentiment(Sentiment logisticSentiment) {
        this.logisticSentiment = logisticSentiment;
    }

    public Sentiment getDictionarySentiment() {
        return dictionarySentiment;
    }

    public void setDictionarySentiment(Sentiment dictionarySentiment) {
        this.dictionarySentiment = dictionarySentiment;
    }

    public Sentiment getCoreSentiment() {
        return coreSentiment;
    }

    public void setCoreSentiment(Sentiment coreSentiment) {
        this.coreSentiment = coreSentiment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
