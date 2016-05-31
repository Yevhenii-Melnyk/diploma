package ua.nure.sentiment.service;

import ua.nure.sentiment.entity.Research;

import java.util.List;

public interface ResearchService {

    void doResearch(List<String> tags, String id);

    Research getTweets(String id);
}
