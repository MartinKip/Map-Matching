package nl.han.dare2date.matchservice;

import java.util.ArrayList;

/**
 * Created by Vincent on 21-11-2015.
 */
public class MatchMaker {

    private ArrayList<Profile> profiles;

    public MatchMaker() {
        this.profiles = new ArrayList<Profile>();
    }

    public void addToProfile(int profileID, ArrayList<String> words) {
        for(Profile profile : profiles) {
            if(profile.getId() == profileID) {
                profile.addWords(words);
            }
        }
    }


    public void addProfile(int id) {
        for(Profile profile : profiles) {
            if(profile.getId() == id) {
                return;
            }
        }
        this.profiles.add(new Profile(id));
    }

    public int calculateMatch() {
        int match = 0;

        ArrayList<String> categories = new ArrayList<String>();
        categories.add("SPORT");
        categories.add("PROGRAMMING");

        for(Profile profile : profiles) {
            profile.processWordList();
        }

        for(String categorie : categories) {
            int matches = 0;
            for(Profile profile : profiles) {
                matches += profile.getMatches(categorie);
            }
            match += Math.ceil(matches / 2);
        }

        return match;
    }
}
