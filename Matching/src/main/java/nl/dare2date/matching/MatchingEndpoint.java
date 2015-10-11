package nl.dare2date.matching;

import nl.dare2date.matching.application.IGetFacebookData;
import nl.dare2date.matching.application.IGetTwitterData;
import nl.dare2date.matching.orchestration.ProfileInternal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class MatchingEndpoint implements IMatchingEndpoint{

    private final IGetFacebookData getFacebookData;
    private final IGetTwitterData getTwitterData;

    @Autowired
    public MatchingEndpoint(IGetFacebookData getFacebookData, IGetTwitterData getTwitterData) {
        this.getFacebookData = getFacebookData;
        this.getTwitterData = getTwitterData;
    }

    @PayloadRoot(localPart = "MatchRequest", namespace = "http://www.dare2date.nl/matching/schemas/messages")
    @ResponsePayload
    public MatchResponse match(@RequestPayload MatchRequest req) {
        MatchResult result = new MatchResult();

        int viewUserId = req.getInput().getViewUser().getId();
        int profileUserId = req.getInput().getProfileUser().getId();

        ProfileInternal viewUser = new ProfileInternal(viewUserId);
        ProfileInternal profileUser = new ProfileInternal(profileUserId);

        // Get twitter data
        List<String> viewUserTwitterData = getTwitterData.getData(viewUser);
        List<String> profileUserTwitterdata = getTwitterData.getData(profileUser);

        // Get facebook data
        List<String> viewUserLikes = this.getFacebookData.getData(viewUser);
        List<String> profileUserLikes = this.getFacebookData.getData(profileUser);

        int match = (
                (
                        this.matchFacebookData(viewUserLikes, profileUserLikes) +
                        this.matchTwitterData(viewUserTwitterData, profileUserTwitterdata) +
                        this.crossMatchTwitterAndFacebook(viewUserLikes, profileUserTwitterdata) +
                        this.crossMatchTwitterAndFacebook(profileUserLikes, viewUserTwitterData)
                ) / 4);

        result.setValue(match);
        result.setMessage("Total lovers");
        MatchResponse response = new MatchResponse();
        response.setResult(result);
        return response;
    }

    private int matchTwitterData(List<String> viewUserTwitterData, List<String> profileUserTwitterData) {
        viewUserTwitterData.retainAll(profileUserTwitterData);
        return viewUserTwitterData.size();
    }

    private int matchFacebookData(List<String> viewUserFacebookData, List<String> profileUserFacebookData) {
        viewUserFacebookData.retainAll(profileUserFacebookData);
        return viewUserFacebookData.size();
    }

    private int crossMatchTwitterAndFacebook(List<String> facebookData, List<String> twitterData) {
        facebookData.retainAll(twitterData);
        return facebookData.size();
    }

}
