package ua.nure.sentiment.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Research implements Comparable<Research> {

    private String id = "";
    private List<String> tags = new ArrayList<>();

    private List<Tweet> tweets = new ArrayList<>();

    private String userName = "";
    private Date createdAt = new Date();

    private Boolean isPublic = true;

    private List<LineData> lineDatas = Collections.synchronizedList(new ArrayList<>());

    public void addLineData(LineData lineData) {
        if (lineDatas.size() > 20) {
            lineDatas.remove(0);
        }
        lineDatas.add(lineData);
    }

    public List<LineData> getLineDatas() {
        return lineDatas;
    }

    public void setLineDatas(List<LineData> lineDatas) {
        this.lineDatas = lineDatas;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Research(String id, List<String> tags, List<Tweet> tweets, String userName, Boolean isPublic) {
        this.id = id;
        this.tags = tags;
        this.tweets = tweets;
        this.userName = userName;
        this.isPublic = isPublic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Research() {

    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Tweet> getTweets() {
        int tweetCount = tweets.size();
        int begin = tweetCount - 30 > 0 ? tweetCount - 30 : 0;
        return tweets.subList(begin, tweetCount);
    }

    public List<Tweet> allTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public int compareTo(Research r) {
        return createdAt.compareTo(r.getCreatedAt());
    }

    public static class LineData {
        private int positive;
        private int negative;
        private int neutral;
        private long date = new Date().getTime();

        public LineData(int positive, int negative, int neutral) {
            this.negative = negative;
            this.neutral = neutral;
            this.positive = positive;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public int getNegative() {
            return negative;
        }

        public void setNegative(int negative) {
            this.negative = negative;
        }

        public int getNeutral() {
            return neutral;
        }

        public void setNeutral(int neutral) {
            this.neutral = neutral;
        }

        public int getPositive() {
            return positive;
        }

        public void setPositive(int positive) {
            this.positive = positive;
        }
    }
}
