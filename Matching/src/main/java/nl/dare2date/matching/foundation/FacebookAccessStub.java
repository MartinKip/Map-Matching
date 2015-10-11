package nl.dare2date.matching.foundation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 11-10-2015.
 */
public class FacebookAccessStub implements IFacebookAccess {
    @Override
    public List<String> getFacebookLikes(String accessToken) {
        List<String> stubReturnString = new ArrayList<>();
        stubReturnString.add(accessToken);
        return stubReturnString;
    }
}
