import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Organizer class, this creates instances of an Organizer to organize a conference.
 *
 */

public class Organizer extends User implements Serializable {

    private final String userName;
    private ArrayList<String> events;

    public Organizer(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        this.userName = "@" + getFirstName() + "_" + getLastName() + "_"
                + getUserID().substring(0, 3);
    }

}
