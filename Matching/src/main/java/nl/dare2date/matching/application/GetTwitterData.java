package nl.dare2date.matching.application;

import nl.dare2date.matching.foundation.ITwitterAccess;
import nl.dare2date.matching.orchestration.Profile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Vincent on 7-10-2015.
 */
public class GetTwitterData implements IGetTwitterData {
    private String consumerKey;
    private String consumerSecret;
    private ITwitterAccess twitterAccess;

    @Autowired
    public GetTwitterData(ITwitterAccess twitterAcces) {
        this.twitterAccess = twitterAcces;
    }

    public List<String> getData(Profile user) {
        return this.twitterAccess.getData(user);
    }
}
