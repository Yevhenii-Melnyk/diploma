package ua.nure.sentiment.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sentiment {

    NEGATIVE(-1, "Negative"),
    NEUTRAL(0, "Neutral"),
    POSITIVE(1, "Positive");

    private int value;

    private String text;

    @JsonValue
    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }

    Sentiment(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public static Sentiment convert(int value) {
        Sentiment result = NEUTRAL;
        if (value > 0)
            result = POSITIVE;
        else if (value < 0)
            result = NEGATIVE;
        return result;
    }


    @Override
    public String toString() {
        return text;
    }
}
