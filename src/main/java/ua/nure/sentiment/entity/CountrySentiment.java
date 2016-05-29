package ua.nure.sentiment.entity;

import java.util.ArrayList;
import java.util.List;

public class CountrySentiment {

    private Country country;
    private List<Tweet> negative = new ArrayList<>();
    private List<Tweet> positive = new ArrayList<>();
    private List<Tweet> neutral = new ArrayList<>();
    private int totalCount;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Tweet> getNegative() {
        return negative == null ? new ArrayList<>() : negative;
    }

    public void setNegative(List<Tweet> negative) {
        this.negative = negative;
    }

    public List<Tweet> getNeutral() {
        return neutral == null ? new ArrayList<>() : neutral;
    }

    public void setNeutral(List<Tweet> neutral) {
        this.neutral = neutral;
    }

    public List<Tweet> getPositive() {
        return positive == null ? new ArrayList<>() : positive;
    }

    public void setPositive(List<Tweet> positive) {
        this.positive = positive;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
