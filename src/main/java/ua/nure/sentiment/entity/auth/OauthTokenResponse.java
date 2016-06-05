package ua.nure.sentiment.entity.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OauthTokenResponse {

    @JsonProperty("oauth_token")
    private String token;

    @JsonProperty("oauth_token_secret")
    private String tokenSecret;

    @JsonProperty("oauth_callback_confirmed")
    private Boolean oauthCallbackConfirmed;

    public OauthTokenResponse(String token, String tokenSecret, Boolean oauthCallbackConfirmed) {
        this.token = token;
        this.tokenSecret = tokenSecret;
        this.oauthCallbackConfirmed = oauthCallbackConfirmed;
    }

    public Boolean getOauthCallbackConfirmed() {
        return oauthCallbackConfirmed;
    }

    public void setOauthCallbackConfirmed(Boolean oauthCallbackConfirmed) {
        this.oauthCallbackConfirmed = oauthCallbackConfirmed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}