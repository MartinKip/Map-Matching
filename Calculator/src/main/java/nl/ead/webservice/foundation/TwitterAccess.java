package nl.ead.webservice.foundation;


import nl.ead.webservice.orchestration.Profile;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vincent on 7-10-2015.
 */
public class TwitterAccess implements ITwitterAccess {

    @Override
    public List<String> getData(Profile user) {
        List<String> result = new ArrayList<>();

//        Check if all necessary values of the user profile are set
        if(user.getTwitterConsumerKey() != null && user.getTwitterConsumerSecret() != null && user.getTwitterAccessToken() != null && user.getTwitterAccessTokenSecret() != null) {
            Twitter twitter = new TwitterTemplate(user.getTwitterConsumerKey(), user.getTwitterConsumerSecret(), user.getTwitterAccessToken(), user.getTwitterAccessTokenSecret());

            TimelineOperations timelineOperations = twitter.timelineOperations();
            List<Tweet> favorites = timelineOperations.getFavorites();
            List<Tweet> ownTweets = timelineOperations.getUserTimeline(200);

            Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
            for(Tweet tweet : ownTweets) {
                Matcher mat = MY_PATTERN.matcher(tweet.getText());
                while (mat.find()) {
                    result.add(mat.group(1).replace("#", ""));
                }
            }

            for(Tweet tweet : favorites) {
                Matcher mat = MY_PATTERN.matcher(tweet.getText());
                while (mat.find()) {
                    result.add(mat.group(1));
                }
            }
        }
        return result;
    }

}
