package nl.ead.webservice.services;

import nl.ead.webservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MatchingEndpoint {

    public MatchingEndpoint() {

    }

    @PayloadRoot(localPart = "MatchRequest", namespace = "http://www.dare2date.nl/matching/schemas/messages")
    @ResponsePayload
    public MatchResponse match(@RequestPayload MatchRequest req) {
        MatchResult result = new MatchResult();
        result.setValue(1);
        result.setMessage("test");
        MatchResponse response = new MatchResponse();
        response.setResult(result);
        return response;
    }
}
