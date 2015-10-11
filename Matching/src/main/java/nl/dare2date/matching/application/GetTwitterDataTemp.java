package nl.dare2date.matching.application;

import nl.dare2date.matching.orchestration.IProfileInternal;
import nl.dare2date.matching.orchestration.ProfileInternal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 11-10-2015.
 */
public class GetTwitterDataTemp implements IGetTwitterData {
    @Override
    public List<String> getData(IProfileInternal user) {
        List<String> result = new ArrayList<>();
        result.add("han");
        return result;
    }
}
