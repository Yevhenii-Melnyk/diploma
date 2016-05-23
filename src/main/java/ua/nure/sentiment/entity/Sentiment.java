package ua.nure.sentiment.entity;

public enum Sentiment {

    NOT_UNDERSTOOD(-1, -1, "Not understood"),
    VERY_NEGATIVE(0, 1, "Very negative"),
    NEGATIVE(1, 2, "Negative"),
    NEUTRAL(2, 3, "Neutral"),
    POSITIVE(3, 4, "Positive"),
    VERY_POSITIVE(4, 5, "Very positive");

    private int from;
    private int to;
    private String text;

    public String getText() {
        return text;
    }

    Sentiment(int from, int to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public static Sentiment convert(double value) {
        Sentiment result = NOT_UNDERSTOOD;
        for (Sentiment sentiment : values()) {
            if (value >= sentiment.from && value <= sentiment.to) {
                result = sentiment;
                break;
            }
        }
        return result;
    }
}
