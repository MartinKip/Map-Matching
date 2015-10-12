package nl.dare2date.matching.orchestration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vincent on 12-10-2015.
 */
public class ProfileInternalTest {
    private IProfileInternal profileInternal;

    @Before
    public void setup() throws Exception{
        profileInternal = new ProfileInternal();
    }


    @Test
    public void getTwitterConsumerKeyTest() {
        final String consumerKey = "otHm6UTgJ88keqeS95sTqiCqE";
        profileInternal.setTwitterConsumerKey(consumerKey);
        assertEquals(consumerKey, profileInternal.getTwitterConsumerKey());
    }

    @Test
    public void getTwitterConsumerSecretTest() {
        final String consumerSecret = "7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T";
        profileInternal.setTwitterConsumerSecret(consumerSecret);
        assertEquals(consumerSecret, profileInternal.getTwitterConsumerSecret());
    }

    @Test
    public void getTwitterAccessTokenTest() {
        final String accessToken = "234690092-9BwU6ZeDcMzN4UGV9BC9WoOy6uUOGexMiGWYV1eE";
        profileInternal.setTwitterAccessToken(accessToken);
        assertEquals(accessToken, profileInternal.getTwitterAccessToken());
    }

    @Test
    public void getTwitterAccessTokenSecretTest() {
        final String accessTokenSecret = "s893vukc7vfDGcmNNkjPvmKOLTeXDZv5sjCzuqgxytqUy";
        profileInternal.setTwitterAccessTokenSecret(accessTokenSecret);
        assertEquals(accessTokenSecret, profileInternal.getTwitterAccessTokenSecret());
    }




}
