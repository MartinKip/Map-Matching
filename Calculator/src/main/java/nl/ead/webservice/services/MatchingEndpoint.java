package nl.ead.webservice.services;

import nl.ead.webservice.*;
import nl.ead.webservice.business.Match;
import nl.ead.webservice.orchestration.Profile;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MatchingEndpoint {

    public MatchingEndpoint() {

    }

    @PayloadRoot(localPart = "MatchRequest", namespace = "http://www.dare2date.nl/matching/schemas/messages")
    @ResponsePayload
    public MatchResponse match(@RequestPayload MatchRequest req) {

        int viewUserId = req.getInput().getViewUser().getId();
        int profileUserId = req.getInput().getProfileUser().getId();

        Profile viewUser = new Profile(viewUserId);
        Profile profileUser = new Profile(profileUserId);

        Match matcher = new Match();
        matcher.match(viewUser, profileUser);


        MatchResult result = new MatchResult();
        result.setValue(1);
        result.setMessage("test");
        MatchResponse response = new MatchResponse();
        response.setResult(result);
        return response;
    }
}
