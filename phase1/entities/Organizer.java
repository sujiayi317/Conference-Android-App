package entities;

import java.io.Serializable;
import java.util.UUID;

/**
 * The entities.Organizer class, this creates instances of an entities.Organizer to organize a conference.
 */
public class Organizer extends User implements Serializable {

    private final String userID;

    /**
     * Constructor No.1 for the Organizer
     *
     * @param userName the userName of this Organizer
     * @param email the email of this Organizer
     * @param password the password of this Organizer
     */
    public Organizer(String userName, String email, String password) {
        super(userName, email, password);
        setType("ORGANIZER");
        this.userID = UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * Constructor No.2 for the Organizer, adding a parameter: ID of this Organizer
     *
     * @param userName the userName of this Organizer
     * @param email the email of this Organizer
     * @param password the password of this Organizer
     * @param ID the user ID of this Organizer
     */
    public Organizer(String userName, String email, String password, String ID) {
        super(userName, email, password);
        setType("ORGANIZER");
        this.userID = ID;
    }

    /**
     * Getter method to access this Organizer's userID
     *
     * @return userID of this Organizer
     */
    public String getUserID() {
        return this.userID;
    }
}
