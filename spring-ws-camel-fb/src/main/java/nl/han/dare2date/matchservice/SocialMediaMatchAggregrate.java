package nl.han.dare2date.matchservice;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import facebook4j.User;
import nl.han.dare2date.matchservice.model.MatchResponse;
import nl.han.dare2date.matchservice.model.MatchResult;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import twitter4j.Status;

import java.math.BigInteger;
import java.util.ArrayList;

public class SocialMediaMatchAggregrate implements AggregationStrategy {
    private int match = 0;

    private MatchMaker matchMaker = new MatchMaker();

    /**
     * This method is called every time a to(...) returns a response. This response (whether it's from
     * twitter or facebook) is sent in the body of the newExchange.
     *
     * @param oldExchange
     * @param newExchange
     * @return
     */
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        /**
         * Build a Response using the results of twitter & facebook. This is no useful
         * matchalgorithm but you can do better don't you?
         */
        increateMatchValueForFacebookUser(oldExchange, newExchange);
        increaseMatchForTwitterStatus(oldExchange, newExchange);

        this.match = this.matchMaker.calculateMatch();

        MatchResult matchResult = new MatchResult();
        matchResult.setNumber(BigInteger.valueOf(match));
        MatchResponse matchResponse = new MatchResponse();
        matchResponse.setMatchResult(matchResult);
        oldExchange.getIn().setBody(matchResponse);
        return oldExchange;
    }

    public int getMatch(){
        return this.match;
    }

    private void increaseMatchForTwitterStatus(Exchange oldExchange, Exchange newExchange) {
        Message inputMessage = newExchange.getIn();
        ArrayList<Status> statuses = inputMessage.getBody(ArrayList.class);

        if (statuses != null) {
            Object profileID = oldExchange.getIn().getHeader("profileID");
            ArrayList<String> profileWords = new ArrayList<String>();
            profileWords.add("Formule 1");
            profileWords.add("Barcelona");
            profileWords.add("OO");
            profileWords.add("PHP");
            profileWords.add("JavaScript");

            this.matchMaker.addProfile(2);
            for (Status status : statuses) {
                // A match is bigger when a user has a lots of tweets favoured
                profileWords.add(status.getText());
            }
            this.matchMaker.addToProfile(Integer.parseInt(profileID.toString()), profileWords);
        }
    }

    private void increateMatchValueForFacebookUser(Exchange oldExchange, Exchange newExchange) {
        Message inputMessage = newExchange.getIn();
        User user = inputMessage.getBody(User.class);

        if (user != null) {
            Object profileID = oldExchange.getIn().getHeader("profileID");
            ArrayList<String> profileWords = new ArrayList<String>();
            profileWords.add("Voetbal");
            profileWords.add("Ajax");
            profileWords.add("HTML");
            profileWords.add("PHP");
            profileWords.add("Java");

            for(String interest : user.getInterestedIn()) {
                profileWords.add(interest);
            }

            this.matchMaker.addProfile(Integer.parseInt(profileID.toString()));
            this.matchMaker.addToProfile(Integer.parseInt(profileID.toString()), profileWords);
        }
    }
}
