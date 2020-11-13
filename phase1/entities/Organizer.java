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

    public String getUserID() {
        return this.userID;
    }
}
