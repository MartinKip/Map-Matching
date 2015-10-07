package nl.ead.webservice.foundation;

import facebook4j.*;
import facebook4j.api.LikeMethods;
import facebook4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public class FacebookAccess implements IFacebookAccess {
    @Override
    public List<String> getFacebookLikes(String accessToken) {
        Facebook facebook = this.getFacebookObject(accessToken);
        ResponseList<Like> likes = this.getUserLikeList(facebook);
        List<String> likeListString = this.transformListToString(likes);
        return likeListString;
    }

    private List<String> transformListToString(ResponseList<Like> likes) {
        List<String> stringLikes = new ArrayList<>();
        for(int i = 0; i < likes.size(); i++){
            String likeName = likes.get(i).getName();
            stringLikes.add(likeName);
        }
        return stringLikes;
    }

    private ResponseList<Like> getUserLikeList(Facebook facebook) {
        ResponseList<Like> likes = null;
        try {
            likes = facebook.likes().getUserLikes();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return likes;
    }

    private Facebook getFacebookObject(String accessToken){
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("909870205767560", "88e8ee990ee1e5a85d4a0b0591a1a140");
        facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
        return facebook;
    }
}
