package nl.dare2date.matching.application;

import nl.dare2date.matching.orchestration.Profile;

import java.util.List;

/**
 * Created by Martin on 7-10-2015.
 */
public interface IGetFacebookData {
    List<String> getData(Profile user);
}
