package nl.ead.webservice.orchestration;

/**
 * Created by Vincent on 7-10-2015.
 */
public class Profile {
    private int id;

    public Profile() {

    }

    public Profile(int id) {
        this.id = id;
    }

    public void view(int userId) {
        System.out.println("Entered user id is : "+ userId);
    }
}
