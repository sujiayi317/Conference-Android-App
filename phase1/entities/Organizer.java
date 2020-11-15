package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The entities.Organizer class, this creates instances of an entities.Organizer to organize a conference.
 *
 */

public class Organizer extends User implements Serializable {

    //private final String userName;
    private ArrayList<String> events;
    private final String userID;

    /**
     * The constructor for an organizer
     * @param userName organizer name
     * @param email organizer email
     * @param password organizer password
     */
    public Organizer(String userName, String email, String password) {
        super(userName, email, password);
        setType("ORGANIZER");
        this.userID = UUID.randomUUID().toString().split("-")[0];
    }

    public Organizer(String userName, String email, String password, String ID) {
        super(userName, email, password);
        setType("ORGANIZER");
        this.userID = ID;
    }

    /**
     * Getter for the organizer user id
     * @return String of the user id.
     */
    public String getUserID() {
        return this.userID;
    }
}
