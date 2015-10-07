package nl.ead.webservice.ApplicationService;

import nl.ead.webservice.Profile;

import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public interface IGetFacebookData {
    List<String> getData(Profile user);
}
