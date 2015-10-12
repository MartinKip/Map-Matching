package nl.dare2date.matching.application;

import nl.dare2date.matching.foundation.FacebookAccessStub;
import nl.dare2date.matching.foundation.IFacebookAccess;
import nl.dare2date.matching.orchestration.ProfileInternal;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Martin on 11-10-2015.
 */
public class GetFacebookDataTest {
    private IGetFacebookData getFacebookData;
    private IFacebookAccess facebookAccessStub;

    @Before
    public void Setup() throws Exception{
        facebookAccessStub = new FacebookAccessStub();
        getFacebookData = new GetFacebookData(facebookAccessStub);
    }

    @Test
    public void FacebookGetsProfileAccessToken() throws Exception {
        final String token = "TestToken";
        ProfileInternal profile = new ProfileInternal();
        profile.setUserFacebookAccessToken(token);
        List<String> result = getFacebookData.getData(profile);
        assertEquals(token, result.get(0));
    }

}
