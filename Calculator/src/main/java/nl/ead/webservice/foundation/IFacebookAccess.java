package nl.ead.webservice.foundation;

import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public interface IFacebookAccess {
    List<String> getFacebookLikes(String accessToken);
}
