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
                this.twitterConsumerKey = "otHm6UTgJ88keqeS95sTqiCqE";
                this.twitterConsumerSecret = "7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T";
                this.twitterAccessToken = "234690092-9BwU6ZeDcMzN4UGV9BC9WoOy6uUOGexMiGWYV1eE";
                this.twitterAccessTokenSecret = "s893vukc7vfDGcmNNkjPvmKOLTeXDZv5sjCzuqgxytqUy";
                facebookAccessToken = "CAAM7hapoD4gBAJnvO13rSOxGABWHDZCIo90ajzvKGBSovy1v1g2eqbbXNKKG3A4wr8xvFefSSD7OAMSZBXWscRQSZCW6o1P5EAIbe7ep4TwAJZBIZBJcZA8CPke3n2uZB79GKXiZAKWu7tsrr1E0fyAZCirZBl7aPT1kPOLZC3TzkZADdR80acd6m7n6cfSGaC61DYl1rWniKl1O0geBKZBIZByviBAGdzHpZA6ZApIZD";
            case 2:
                this.twitterConsumerKey = "otHm6UTgJ88keqeS95sTqiCqE";
                this.twitterConsumerSecret = "7vM6EDM5VqNYvs0pFhym09KxqdtqZ0nvl3WkWuOQ18MHvmrR9T";
                this.twitterAccessToken = "234690092-9BwU6ZeDcMzN4UGV9BC9WoOy6uUOGexMiGWYV1eE";
                this.twitterAccessTokenSecret = "s893vukc7vfDGcmNNkjPvmKOLTeXDZv5sjCzuqgxytqUy";
                facebookAccessToken = "CAAM7hapoD4gBAJnvO13rSOxGABWHDZCIo90ajzvKGBSovy1v1g2eqbbXNKKG3A4wr8xvFefSSD7OAMSZBXWscRQSZCW6o1P5EAIbe7ep4TwAJZBIZBJcZA8CPke3n2uZB79GKXiZAKWu7tsrr1E0fyAZCirZBl7aPT1kPOLZC3TzkZADdR80acd6m7n6cfSGaC61DYl1rWniKl1O0geBKZBIZByviBAGdzHpZA6ZApIZD";
        }
    }

    /**
     * Get hard coded Facebook access tokens for users
     * @return
     */
    public String getUserFacebookAccessToken(){
        return this.facebookAccessToken;
    }

    @Override
    public void setUserFacebookAccessToken(String token) {
        this.facebookAccessToken = token;
    }

    public String getTwitterConsumerKey() {
        return this.twitterConsumerKey;
    }

    public String getTwitterConsumerSecret() {
        return this.twitterConsumerSecret;
    }

    public String getTwitterAccessToken() {
        return this.twitterAccessToken;
    }

    public String getTwitterAccessTokenSecret() {
        return this.twitterAccessTokenSecret;
    }

    @Override
    public int getId() {
        return id;
    }

}
