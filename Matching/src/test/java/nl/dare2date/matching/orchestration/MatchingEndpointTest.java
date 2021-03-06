package nl.dare2date.matching.orchestration;

import nl.dare2date.matching.*;
import nl.dare2date.matching.application.*;
import nl.dare2date.matching.foundation.IFacebookAccess;
import nl.dare2date.matching.foundation.ITwitterAccess;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MatchingEndpointTest {
    private IMatchingEndpoint matchingEndpoint;
    private IGetFacebookData getFacebookData;
    private IGetTwitterData getTwitterData;
    private Mockery context = new Mockery();

    @Before
    public void setup() throws Exception{
        getFacebookData = new GetFacebookDataTemp();
        getTwitterData = new GetTwitterDataTemp();
        matchingEndpoint = new MatchingEndpoint(getFacebookData, getTwitterData);
    }

    @Test
    public void ReturnsFullMatch() throws Exception{
        // Temp stubs return same lists

        MatchInput input = new MatchInput();
        input.setProfileUser(new nl.dare2date.matching.Profile());
        input.setViewUser(new nl.dare2date.matching.Profile());
        MatchRequest request = new MatchRequest();
        request.setInput(input);
        MatchResponse response = matchingEndpoint.match(request);
        assertEquals(100, response.getResult().getValue());
        assertEquals("Perfect", response.getResult().getMessage());
    }

    @Test(expected = NullPointerException.class)
    public void NoProfileInRequestsThrowsExcpetion() throws Exception{
        MatchRequest request = new MatchRequest();
        matchingEndpoint.match(request);
    }

    @Test
    public void getMessageTest() {
        final int hopeloosPercentage = 30;
        final int middelmatigPercentage = 50;
        final int perfectPercentage = 80;
        final String hopeloosMessage = "Hopeloos";
        final String middelmatigMessage = "Middelmatig";
        final String perfectMessage = "Perfect";

        assertEquals(hopeloosMessage, matchingEndpoint.getMessage(hopeloosPercentage));
        assertEquals(middelmatigMessage, matchingEndpoint.getMessage(middelmatigPercentage));
        assertEquals(perfectMessage, matchingEndpoint.getMessage(perfectPercentage));

    }

}
