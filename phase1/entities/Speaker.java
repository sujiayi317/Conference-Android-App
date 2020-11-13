package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * The entities.Speaker class, this creates instances of an entities.Speaker for a conference.
 *
 */

public class Speaker extends User implements Serializable {
    private final String userID;
    public Speaker(String userName, String email, String password) {
        super(userName, email, password);
        setType("SPEAKER");
        this.userID = UUID.randomUUID().toString().split("-")[0];
    }

    public Speaker(String userName, String email, String password, String ID) {
        super(userName, email, password);
        setType("SPEAKER");
        this.userID = ID;
    }

    public String getUserID() {
        return this.userID;
    }

}