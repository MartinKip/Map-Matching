package nl.ead.webservice.business;

import nl.ead.webservice.application.GetTwitterData;
import nl.ead.webservice.orchestration.Profile;

import java.util.List;

/**
 * Created by Vincent on 7-10-2015.
 */
public class Match {

    public void match(Profile viewUser, Profile profileUser) {
        GetTwitterData twitterRetriever = new GetTwitterData();
        List<String> viewUserTwitterData = twitterRetriever.getData(viewUser);
        List<String> profileUserTwitterdata = twitterRetriever.getData(profileUser);
        System.out.println("num of hashtags for viewUser: " + viewUserTwitterData.size());
        System.out.println("num of hashtags for profileUser: " + profileUserTwitterdata.size());

        for(String key : viewUserTwitterData) {
            System.out.println(key);
        }

    }

    public void matchUsers() {

    }
}
