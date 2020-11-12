import java.io.Serializable;

/**
 * The Attendee class, this creates instances of Attendee to attend a conference.
 *
 */
public class Attendee extends User  implements Serializable {

    public Attendee(String userName, String email, String password) {
        super(userName, email, password);
    }


}
