import java.io.Serializable;

/**
 * The Speaker class, this creates instances of an Speaker for a conference.
 *
 */

public class Speaker extends User implements Serializable {

    public Speaker(String userName, String email, String password) {
        super(userName, email, password);
    }
}