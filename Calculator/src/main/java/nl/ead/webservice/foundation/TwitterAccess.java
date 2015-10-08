package nl.ead.webservice.foundation;


import nl.ead.webservice.orchestration.Profile;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vincent on 7-10-2015.
 */
public class TwitterAccess {

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public TwitterAccess() {
        this.consumerKey = "otHm6UTgJ88keqeS95sTqiCqE";
        this.consumerSecret = "7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T";
        this.accessToken = "234690092-9BwU6ZeDcMzN4UGV9BC9WoOy6uUOGexMiGWYV1eE";
        this.accessTokenSecret = "s893vukc7vfDGcmNNkjPvmKOLTeXDZv5sjCzuqgxytqUy";
    }

    public List<String> getData(Profile user) {
        List<String> result = new ArrayList<>();
        Twitter twitter = new TwitterTemplate(this.consumerKey, this.consumerSecret, this.accessToken, this.accessTokenSecret);

        TimelineOperations timelineOperations = twitter.timelineOperations();
        List<Tweet> favorites = timelineOperations.getFavorites();
        List<Tweet> ownTweets = timelineOperations.getUserTimeline(200);

        Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
        for(Tweet tweet : ownTweets) {
            Matcher mat = MY_PATTERN.matcher(tweet.getText());
            while (mat.find()) {
                result.add(mat.group(1));
            }
        }

        for(Tweet tweet : favorites) {
            Matcher mat = MY_PATTERN.matcher(tweet.getText());
            while (mat.find()) {
                result.add(mat.group(1));
            }
        }

        return result;
    }

}
