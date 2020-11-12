package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The entities.Organizer class, this creates instances of an entities.Organizer to organize a conference.
 *
 */

public class Organizer extends User implements Serializable {

    //private final String userName;
    private ArrayList<String> events;

    public Organizer(String userName, String email, String password) {
        super(userName, email, password);
        //this.userName = "@" + getFirstName() + "_" + getLastName() + "_"
                //+ getUserID().substring(0, 3);
    }

}
