import java.io.Serializable;

/**
 * The Attendee class, this creates instances of Attendee to attend a conference.
 *
 */
public class Attendee extends User  implements Serializable {

    public Attendee(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }


}
