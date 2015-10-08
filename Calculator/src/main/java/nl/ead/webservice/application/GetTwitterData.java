package nl.ead.webservice.application;

import nl.ead.webservice.foundation.TwitterAccess;
import nl.ead.webservice.orchestration.Profile;

import java.util.List;

/**
 * Created by Vincent on 7-10-2015.
 */
public class GetTwitterData  {
    private String consumerKey;
    private String consumerSecret;
    private TwitterAccess twitterAccess;

    public GetTwitterData() {
        this.consumerKey = "otHm6UTgJ88keqeS95sTqiCqE";
        this.consumerSecret = "7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T";
        this.setTwitterAccess(new TwitterAccess());
    }

    public void setTwitterAccess(TwitterAccess twitterAccess) {
        this.twitterAccess = twitterAccess;
    }

    public List<String> getData(Profile user) {
        return this.twitterAccess.getData(user);
    }
}
