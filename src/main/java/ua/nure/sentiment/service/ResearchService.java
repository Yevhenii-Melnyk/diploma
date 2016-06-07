package ua.nure.sentiment.service;

import ua.nure.sentiment.entity.Research;

import java.util.List;

public interface ResearchService {

    void doResearch(List<String> tags, String id, String userName, Boolean isPublic);

    Research getTweets(String id);

    List<Research> getPublicResearches();

    List<Research> getResearchByUserName(String name);

}
