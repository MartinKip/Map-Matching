package nl.ead.webservice.services;

import nl.ead.webservice.*;
import nl.ead.webservice.ApplicationService.IGetFacebookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class MatchingEndpoint {

    private final IGetFacebookData getFacebookData;

    @Autowired
    public MatchingEndpoint(IGetFacebookData getFacebookData) {
        this.getFacebookData = getFacebookData;
    }

    @PayloadRoot(localPart = "MatchRequest", namespace = "http://www.dare2date.nl/matching/schemas/messages")
    @ResponsePayload
    public MatchResponse match(@RequestPayload MatchRequest req) {
        // Get facebook data
        MatchResult result = new MatchResult();
        List<String> viewUserLikes = this.getFacebookData.getData(req.getInput().getViewUser());
        List<String> profileUserLikes = this.getFacebookData.getData(req.getInput().getProfileUser());
        int match = this.tempCountSameLikes(viewUserLikes, profileUserLikes);
        result.setValue(match);
        result.setMessage("Total lovers");
        MatchResponse response = new MatchResponse();
        response.setResult(result);
        return response;
    }

    private int tempCountSameLikes(List<String> likes1, List<String> likes2){
        likes1.retainAll(likes2);
        return likes1.size();
    }
}
