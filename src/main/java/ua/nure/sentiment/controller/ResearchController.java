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
                                  @RequestParam("researchId") String researchId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam(value = "isPublic", required = false, defaultValue = "true") Boolean isPublic) {
        researchService.doResearch(tags, researchId, userName, isPublic);
        return researchService.getTweets(researchId);
    }

    @RequestMapping(value = "publicResearches", method = RequestMethod.GET)
    public List<Research> getPublicResearches() {
        return researchService.getPublicResearches();
    }

    @RequestMapping(value = "researchByUsername", method = RequestMethod.GET)
    public List<Research> researchByUsername(@RequestParam(name = "username") String userName) {
        return researchService.getResearchByUserName(userName);
    }

}
