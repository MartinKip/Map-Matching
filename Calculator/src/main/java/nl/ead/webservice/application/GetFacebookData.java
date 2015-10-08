package nl.ead.webservice.application;

import nl.ead.webservice.foundation.IFacebookAccess;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public class GetFacebookData implements IGetFacebookData {

    private final IFacebookAccess facebookAccess;

    @Autowired
    public GetFacebookData(IFacebookAccess facebookAccess){
        this.facebookAccess = facebookAccess;
    }

    @Override
    public List<String> getData(nl.ead.webservice.orchestration.Profile user) {
        List<String> userData = this.facebookAccess.getFacebookLikes(user.getUserFacebookAccessToken());
        return userData;
    }
}
