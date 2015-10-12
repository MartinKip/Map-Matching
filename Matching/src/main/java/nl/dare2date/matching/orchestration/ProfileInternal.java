package nl.dare2date.matching.orchestration;

/**
 * Created by Vincent on 7-10-2015.
 */
public class ProfileInternal implements IProfileInternal {
    private int id;
    private String twitterConsumerKey;
    private String twitterConsumerSecret;
    private String twitterAccessToken;
    private String twitterAccessTokenSecret;
    private String facebookAccessToken;

    public ProfileInternal() {
        this.setData();
    }

    public ProfileInternal(int id) {
        this.id = id;
        this.setData();
    }

    @Override
    public void view(int userId) {
        System.out.println("Entered user id is : "+ userId);
    }

    private void setData() {
        switch(this.id) {
            case 1:
//                Vincent
                this.setTwitterConsumerKey("otHm6UTgJ88keqeS95sTqiCqE");
                this.setTwitterConsumerSecret("7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T");
                this.setTwitterAccessToken("234690092-9BwU6ZeDcMzN4UGV9BC9WoOy6uUOGexMiGWYV1eE");
                this.setTwitterAccessTokenSecret("s893vukc7vfDGcmNNkjPvmKOLTeXDZv5sjCzuqgxytqUy");
                this.setUserFacebookAccessToken("CAACEdEose0cBAHYEjayn7ZBqLOuuu02j3GdLrJPgdHGq5gmX4eIH8m1KY4FBZBlwb0NCH5oPU6rHiqRD7bbiYpLOLzn9Ja7rFOmFBA1uZCNE1VVNGVwLEOUIRCsJMrAbCYRxMnFBdZC20NMf8izpPlc0yUm0gR61LH7NHFrZA3TgI7pqmoiJA5TlJ8HZCvs034ohVDIUq4IQZDZD");
            case 2:
//                Martin

//                this.setTwitterConsumerKey();
//                this.setTwitterConsumerSecret();
//                this.setTwitterAccessToken();
//                this.setTwitterAccessTokenSecret();
               this.setUserFacebookAccessToken("CAAM7hapoD4gBAKRZCm4bYxola2oKbM1gGvF9uLudZAr9sRiBs6ZAP33bMizk05iicry6UIrAZAUysgQWQ7PQdKg8zAMYH41ttj16ZCDg7mi9M81B835rczwbJslZCRSEIbwJ27HXC9AYcRWyYSFuNkrKGVzFxadZB94mPeavCNMXaZCPSOLGYvhZC7u8avZBs7Xp00UHsjj7VQTwZDZD");
        }
    }

    /**
     * Get hard coded Facebook access tokens for users
     * @return
     */
    @Override
    public String getUserFacebookAccessToken(){
        return this.facebookAccessToken;
    }

    @Override
    public void setUserFacebookAccessToken(String token) {
        this.facebookAccessToken = token;
    }

    @Override
    public void setTwitterConsumerKey(String consumerKey) {
        this.twitterConsumerKey = consumerKey;
    }

    @Override
    public void setTwitterConsumerSecret(String consumerSecret) {
        this.twitterConsumerSecret = consumerSecret;
    }

    @Override
    public void setTwitterAccessToken(String accessToken) {
        this.twitterAccessToken = accessToken;
    }

    @Override
    public void setTwitterAccessTokenSecret(String accessTokenSecret) {
        this.twitterAccessTokenSecret = accessTokenSecret;
    }

    @Override
    public String getTwitterConsumerKey() {
        return this.twitterConsumerKey;
    }

    @Override
    public String getTwitterConsumerSecret() {
        return this.twitterConsumerSecret;
    }

    @Override
    public String getTwitterAccessToken() {
        return this.twitterAccessToken;
    }

    @Override
    public String getTwitterAccessTokenSecret() {
        return this.twitterAccessTokenSecret;
    }

    @Override
    public int getId() {
        return id;
    }

}
