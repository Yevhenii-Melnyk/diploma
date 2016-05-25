package ua.nure.sentiment.entity;

import twitter4j.GeoLocation;

public class Country {

    private String code;
    private GeoLocation location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }
}
