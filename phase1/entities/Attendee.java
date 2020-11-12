package entities;

import java.io.Serializable;

/**
 * The entities.Attendee class, this creates instances of entities.Attendee to attend a conference.
 *
 */
public class Attendee extends User  implements Serializable {

    public Attendee(String userName, String email, String password) {
        super(userName, email, password);
        setType("ATTENDEE");
    }


}
