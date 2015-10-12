package nl.dare2date.matching;

import nl.dare2date.matching.application.IGetFacebookData;
import nl.dare2date.matching.application.IGetTwitterData;
import nl.dare2date.matching.orchestration.ProfileInternal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
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
        List<String> profileUserTwitterData = getTwitterData.getData(profileUser);

        // Get facebook data
        List<String> viewUserLikes = this.getFacebookData.getData(viewUser);
        List<String> profileUserLikes = this.getFacebookData.getData(profileUser);

        List<List<String>> allLists = new ArrayList<>();
        allLists.add(viewUserTwitterData);
        allLists.add(viewUserLikes);
        allLists.add(profileUserTwitterData);
        allLists.add(profileUserLikes);

        int totalResults = getTotalResults(allLists);
        int matches = getMatches(viewUserTwitterData, profileUserTwitterData, viewUserLikes, profileUserLikes);

        int percentage = (int) getPercentage(totalResults, matches);
        result.setValue(percentage);
        String message = getMessage(percentage);
        result.setMessage(message);
        MatchResponse response = new MatchResponse();
        response.setResult(result);
        return response;
    }

    public String getMessage(int percentage) {
        String returnMessage = "";
        if(percentage < 33) {
            returnMessage  = "Hopeloos";
        }
        else if(percentage >= 33 && percentage < 66) {
            returnMessage  = "Middelmatig";
        }
        else if(percentage >= 66 && percentage <= 100) {
            returnMessage  = "Perfect";
        }
        return returnMessage ;
    }

    private double getPercentage(double totalResults, double matches) {
        double returnValue;
        if(totalResults == 0) {
            returnValue = 0;
        }
        else {
            returnValue  = (matches / totalResults) * 100;
        }
        return  returnValue;
    }

    private int getMatches(List<String> viewUserTwitterData, List<String> profileUserTwitterdata, List<String> viewUserLikes, List<String> profileUserLikes) {
        return (
                    (
                            this.matchFacebookData(viewUserLikes, profileUserLikes) +
                            this.matchTwitterData(viewUserTwitterData, profileUserTwitterdata) +
                            this.crossMatchTwitterAndFacebook(viewUserLikes, profileUserTwitterdata) +
                            this.crossMatchTwitterAndFacebook(profileUserLikes, viewUserTwitterData)
                    )
                );
    }

    private int getTotalResults(List<List<String>> allLists) {
        int returnResult = 0;
        for(List subList : allLists) {
            returnResult += subList.size();
        }
        return returnResult;
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
