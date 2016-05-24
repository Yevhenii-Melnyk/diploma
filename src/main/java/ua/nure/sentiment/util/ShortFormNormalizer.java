package ua.nure.sentiment.util;

import java.util.HashMap;
import java.util.Map;

public class ShortFormNormalizer {

    private static final Map<String, String> mapping = new HashMap<>();

    static {

        mapping.put("\\byou're\\b", " you are ");
        mapping.put("\\bu\\b", " you ");
        mapping.put("\\bhaha\\b", " ha ");
        mapping.put("\\bhahaha\\b", " ha");
        mapping.put("\\bdon't\\b", " do not ");
        mapping.put("\\bdoesn't\\b", " does not ");
        mapping.put("\\bdidn't\\b", " did not ");
        mapping.put("\\bhasn't\\b", " has not ");
        mapping.put("\\bhaven't\\b", " have not ");
        mapping.put("\\bhadn't\\b", " had not ");
        mapping.put("\\bwon't\\b", " will not ");
        mapping.put("\\bwouldn't\\b", " would not ");
        mapping.put("\\bcan't\\b", " can not ");
        mapping.put("\\bcannot\\b", " can not ");
    }


    public static String normalizeText(String text) {
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            text = text.replaceAll(entry.getKey(), entry.getValue());
        }
        return text;
    }

}
