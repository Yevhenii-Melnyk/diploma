package ua.nure.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.sentiment.entity.Research;
import ua.nure.sentiment.service.ResearchService;

import java.util.List;

@RestController
@CrossOrigin
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    @RequestMapping(value = "research", method = RequestMethod.GET)
    public Research searchTweets(@RequestParam("researchId") String researchId) {
        return researchService.getTweets(researchId);
    }

    @RequestMapping(value = "startResearch", method = RequestMethod.GET)
    public Research startResearch(@RequestParam(name = "tags") List<String> tags,
                                  @RequestParam("researchId") String researchId) {
        researchService.doResearch(tags, researchId);
        return researchService.getTweets(researchId);
    }

}
