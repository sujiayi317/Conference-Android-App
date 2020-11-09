import java.io.Serializable;

/**
 * The Speaker class, this creates instances of an Speaker for a conference.
 *
 */

public class Speaker extends User implements Serializable {

    public Speaker(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }
}