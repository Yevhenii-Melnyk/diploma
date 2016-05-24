package ua.nure.sentiment.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class AfinnDictionary {

    private Map<String, Integer> dictionary = new HashMap<>();

    public AfinnDictionary() throws IOException {
        new BufferedReader(new InputStreamReader(AfinnDictionary.class.getClassLoader().getResourceAsStream("AFINN.txt"))).lines()
                .forEach(line -> {
                    String[] split = line.split("\\s+");
                    if (split.length == 2)
                        dictionary.put(split[0].trim(), Integer.valueOf(split[1].trim()));
                });
    }

    public Map<String, Integer> getDictionary() {
        return dictionary;
    }

}
