package ua.nure.sentiment.controller;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.sentiment.entity.auth.OauthTokenResponse;
import ua.nure.sentiment.entity.auth.Payload;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class SocialController {

    private Map<String, Object> tokens = new ConcurrentHashMap<>();

    @Autowired
    private ServiceBuilder serviceBuilder;

    @RequestMapping(value = "/twitter", method = RequestMethod.POST)
    public Object twitter(@RequestBody Payload payload) {
        System.out.println(payload);
        if (payload.getOauthToken() == null) {
            OAuth10aService twitterOauthService = serviceBuilder.callback(payload.getRedirectUri())
                    .build(TwitterApi.Authenticate.instance());
            OAuth1RequestToken requestToken = twitterOauthService.getRequestToken();
            System.out.println(requestToken);
            return new OauthTokenResponse(requestToken.getToken(), requestToken.getTokenSecret(), requestToken.isOauthCallbackConfirmed());
        }

        OAuth1RequestToken requestToken = new OAuth1RequestToken(payload.getOauthToken(), payload.getOauthVerifier());
        OAuth10aService service = serviceBuilder.build(TwitterApi.Authenticate.instance());
        OAuth1AccessToken accessToken = service.getAccessToken(requestToken, payload.getOauthVerifier());

        final OAuthRequest request =
                new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        tokens.put("Bearer " + accessToken.getToken(), response.getBody());

        return accessToken;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public Object me(@RequestHeader("Authorization") String authCookie) {
        return tokens.get(authCookie);
    }


}
