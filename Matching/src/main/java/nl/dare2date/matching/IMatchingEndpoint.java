package nl.dare2date.matching;

import nl.dare2date.matching.MatchRequest;
import nl.dare2date.matching.MatchResponse;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * Created by Martin on 11-10-2015.
 */
public interface IMatchingEndpoint {
    MatchResponse match(@RequestPayload MatchRequest req);
    String getMessage(int percentage);
}
