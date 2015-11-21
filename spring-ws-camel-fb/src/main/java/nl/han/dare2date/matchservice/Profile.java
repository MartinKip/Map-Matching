package nl.han.dare2date.matchservice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vincent on 21-11-2015.
 */
public class Profile {
    private int id;
    private ArrayList<String> words;

    private HashMap<String, Integer> categoryMatches;
    private HashMap<String, ArrayList<String>> categories;


    public Profile(int id) {
        this.id = id;
        this.words = new ArrayList<String>();

        this.categoryMatches= new HashMap<>();
        this.categoryMatches.put("PROGRAMMING", 0);
        this.categoryMatches.put("SPORT", 0);


        ArrayList<String> programming = new ArrayList<>();
        programming.add("PHP");
        programming.add("Java");
        programming.add("OO");
        programming.add("JavaScript");
        programming.add("HTML");


        ArrayList<String> sports = new ArrayList<>();
        sports.add("Voetbal");
        sports.add("Ajax");
        sports.add("Barcelona");
        sports.add("Real Madrid");
        sports.add("Formule 1");

        // TODO: maybe put this in a database?
        this.categories = new HashMap<>();
        this.categories.put("PROGRAMMING", programming);
        this.categories.put("SPORT", sports);
    }

    public void addWords(ArrayList<String> words) {
        for(String word : words) {
            this.words.add(word);
        }
    }

    public int getId() {
        return id;
    }

    public int getMatches(String key) {
        return this.categoryMatches.get(key);
    }

    public void processWordList() {
        for(String word : words) {
            for(String key : this.categories.keySet()) {
                ArrayList<String> categoryList = this.categories.get(key);
                if(categoryList.contains(word)) {
                    int newScore = this.categoryMatches.get(key) + 1;
                    this.categoryMatches.put(key, newScore);
                }
            }
        }
    }
}
