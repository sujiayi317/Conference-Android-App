package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * The entities.Attendee class, this creates instances of entities.Attendee to attend a conference.
 *
 */
public class Attendee extends User  implements Serializable {

    private final String userID;

    public Attendee(String userName, String email, String password) {
        super(userName, email, password);
        setType("ATTENDEE");
        userID = UUID.randomUUID().toString().split("-")[0];
    }

    public Attendee(String userName, String email, String password, String ID) {
        super(userName, email, password);
        setType("ATTENDEE");
        userID = ID;
    }

    public String getUserID() {
        return this.userID;
    }
}
