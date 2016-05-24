package ua.nure.sentiment.util;

import java.util.HashMap;
import java.util.Map;

public class SentimentNormalizer {

    private static final String good = " good ";
    private static final String bad = " bad ";
    private static final String sad = " sad ";

    private static final Map<String, String> mapping = new HashMap<>();

    static {

        // positive emoticons
        mapping.put(":\\)", good);
        mapping.put(":-\\)", good);
        mapping.put("&lt;3", good);
        mapping.put(" ->d", good);
        mapping.put(" ->dd", good);
        mapping.put("8\\)", good);
        mapping.put(" ->-\\)", good);
        mapping.put(" ->\\)", good);
        mapping.put(";\\)", good);
        mapping.put(";-\\)", good);
        mapping.put("\\(- ->", good);
        mapping.put("\\( ->", good);
        mapping.put("\uD83D\uDE03", good); // smiley open mouth
        mapping.put("\uD83D\uDE04", good); // smile
        mapping.put("\uD83D\uDE0A", good); // smiling face with smiling eyes
        mapping.put("\uD83D\uDE00", good); // grin
        mapping.put("\uD83D\uDE17", good); // kiss
        mapping.put("\uD83D\uDE1B", good); // stuck-out-tongue
        mapping.put("\u263A", good); // relaxed
        mapping.put("\u270C", good); // victory
        mapping.put("\u2764", good); // heart
        mapping.put("\u2B50", good); // star
        mapping.put("\uD83D\uDC4D", good); // thumbs up
        mapping.put("\uD83D\uDC4C", good); // ok hand
        mapping.put("\uD83D\uDC4F", good); // clapping hands
        mapping.put("\uD83D\uDE02", good); // tears of joy
        mapping.put("\uD83D\uDE06", good); // laughing
        mapping.put("\uD83D\uDE0B", good); // yum
        mapping.put("\uD83D\uDE0D", good); // heart eyes
        // negative emoticons
        mapping.put("\uD83D\uDE1F", bad); // worried
        mapping.put("\uD83D\uDE26", bad); // frowning
        mapping.put("\uD83D\uDE27", bad); // anguished
        mapping.put("\uD83D\uDE15", bad); // confused
        mapping.put("\uD83D\uDE1E", bad); // disappointed
        mapping.put("\uD83D\uDE20", bad); // angry
        mapping.put("\uD83D\uDE22", sad); // cry
        mapping.put("\uD83D\uDE21", sad); // rage
        mapping.put(" ->/", bad);
        mapping.put(" ->&gt;", sad);
        mapping.put(" ->\\'\\)", sad);
        mapping.put(" ->-\\(", bad);
        mapping.put(" ->\\(", bad);
        mapping.put(" ->S", bad);
        mapping.put(" ->-S", bad);
    }

    public static String normalizeText(String text) {
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            text = text.replaceAll(entry.getKey(), entry.getValue());
        }
        return text;
    }

}
