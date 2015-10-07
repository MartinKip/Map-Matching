package nl.ead.webservice.ApplicationService;

import nl.ead.webservice.Profile;
import nl.ead.webservice.foundation.IFacebookAccess;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public class GetFacebookData implements IGetFacebookData{

    private final IFacebookAccess facebookAccess;

    @Autowired
    public GetFacebookData(IFacebookAccess facebookAccess){
        this.facebookAccess = facebookAccess;
    }

    @Override
    public List<String> getData(Profile user) {
        String facebookAccessToken = this.GetUserFacebookAccessToken(user.getId());
        List<String> userData = this.facebookAccess.getFacebookLikes(facebookAccessToken);
        return userData;
    }

    /**
     * Get hard coded Facebook access tokens for users
     * @param userId
     * @return
     */
    private String GetUserFacebookAccessToken(int userId){
        String accessToken = "";
        switch (userId){
            case 1: accessToken = "CAAM7hapoD4gBAJnvO13rSOxGABWHDZCIo90ajzvKGBSovy1v1g2eqbbXNKKG3A4wr8xvFefSSD7OAMSZBXWscRQSZCW6o1P5EAIbe7ep4TwAJZBIZBJcZA8CPke3n2uZB79GKXiZAKWu7tsrr1E0fyAZCirZBl7aPT1kPOLZC3TzkZADdR80acd6m7n6cfSGaC61DYl1rWniKl1O0geBKZBIZByviBAGdzHpZA6ZApIZD";
                break;
            case 2: accessToken = "CAAM7hapoD4gBAJnvO13rSOxGABWHDZCIo90ajzvKGBSovy1v1g2eqbbXNKKG3A4wr8xvFefSSD7OAMSZBXWscRQSZCW6o1P5EAIbe7ep4TwAJZBIZBJcZA8CPke3n2uZB79GKXiZAKWu7tsrr1E0fyAZCirZBl7aPT1kPOLZC3TzkZADdR80acd6m7n6cfSGaC61DYl1rWniKl1O0geBKZBIZByviBAGdzHpZA6ZApIZD";
                break;
        }
        return accessToken;
    }
}
