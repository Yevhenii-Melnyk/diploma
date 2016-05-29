package ua.nure.sentiment.entity;

import java.io.Serializable;
import java.util.List;

public class GeoSearchDto implements Serializable {

    private List<String> tags;

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
