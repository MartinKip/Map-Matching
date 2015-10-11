package nl.dare2date.matching.application;

import nl.dare2date.matching.orchestration.IProfileInternal;
import nl.dare2date.matching.orchestration.ProfileInternal;
import nl.dare2date.matching.foundation.IFacebookAccess;
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
    public List<String> getData(IProfileInternal user) {
        List<String> userData = this.facebookAccess.getFacebookLikes(user.getUserFacebookAccessToken());
        return userData;
    }
}
