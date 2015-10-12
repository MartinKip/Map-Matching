package nl.dare2date.matching.orchestration;

/**
 * Created by Martin on 11-10-2015.
 */
public interface IProfileInternal {
    void view(int userId);

    String getUserFacebookAccessToken();

    void setUserFacebookAccessToken(String token);

    void setTwitterConsumerKey(String consumerKey);

    void setTwitterConsumerSecret(String consumerSecret);

    void setTwitterAccessToken(String accessToken);

    void setTwitterAccessTokenSecret(String accessTokenSecret);

    String getTwitterConsumerKey();

    String getTwitterConsumerSecret();

    String getTwitterAccessToken();

    String getTwitterAccessTokenSecret();

    int getId();
}
