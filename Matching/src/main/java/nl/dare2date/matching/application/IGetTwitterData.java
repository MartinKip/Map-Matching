package nl.dare2date.matching.application;

import nl.dare2date.matching.orchestration.IProfileInternal;
import nl.dare2date.matching.orchestration.ProfileInternal;

import java.util.List;

/**
 * Created by Vincent on 8-10-2015.
 */
public interface IGetTwitterData {
    List<String> getData(IProfileInternal user);
}
