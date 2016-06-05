package ua.nure.sentiment.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

    private String redirectUri;

    @JsonProperty("oauth_token")
    private String oauthToken;

    @JsonProperty("oauth_verifier")
    private String oauthVerifier;

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthVerifier() {
        return oauthVerifier;
    }

    public void setOauthVerifier(String oauthVerifier) {
        this.oauthVerifier = oauthVerifier;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "oauthToken='" + oauthToken + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", oauthVerifier='" + oauthVerifier + '\'' +
                '}';
    }

}
