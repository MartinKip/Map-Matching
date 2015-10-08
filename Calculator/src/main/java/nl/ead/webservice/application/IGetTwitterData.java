package nl.ead.webservice.application;

import nl.ead.webservice.orchestration.Profile;

import java.util.List;

/**
 * Created by Vincent on 8-10-2015.
 */
public interface IGetTwitterData {
    List<String> getData(Profile user);
}
