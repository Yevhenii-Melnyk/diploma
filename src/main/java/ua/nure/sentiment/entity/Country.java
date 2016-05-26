package ua.nure.sentiment.entity;

import twitter4j.GeoLocation;

public class Country {

    private String code;
    private String name;
    private GeoLocation location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object obj) {
        return code.equals(((Country) obj).getCode());
    }
}
